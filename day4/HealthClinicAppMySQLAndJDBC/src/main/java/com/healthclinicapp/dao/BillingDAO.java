package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Billing;
import com.healthclinicapp.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the {@code billing} table.
 *
 * SQL concepts demonstrated:
 *   Transactions (BEGIN/COMMIT/ROLLBACK), Savepoints,
 *   CASE expression in SELECT, COALESCE, IFNULL,
 *   Batch updates, CallableStatement (GenerateBill, PayBill procedures),
 *   Aggregate SUM/AVG/MAX/MIN with GROUP BY.
 */
public class BillingDAO {

    // ── CREATE via Stored Procedure ───────────────────────────────────────────

    /**
     * Generate a bill using the {@code GenerateBill} procedure.
     * Demonstrates: CallableStatement with IN + OUT params.
     */
    public int generateViaProcedure(int patientId, int visitId,
                                     double amount, double discount, double tax) throws SQLException {
        String sql = "{CALL GenerateBill(?,?,?,?,?,?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt   (1, patientId);
            cs.setInt   (2, visitId);
            cs.setDouble(3, amount);
            cs.setDouble(4, discount);
            cs.setDouble(5, tax);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            return cs.getInt(6);
        }
    }

    public int insert(Billing b) throws SQLException {
        String sql = """
            INSERT INTO billing(patient_id,visit_id,bill_date,total_amount,
                                paid_amount,discount,tax,status,notes)
            VALUES(?,?,?,?,?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1, b.getPatientId());
            if (b.getVisitId() != null) ps.setInt(2, b.getVisitId()); else ps.setNull(2, Types.INTEGER);
            ps.setDate  (3, b.getBillDate());
            ps.setDouble(4, b.getTotalAmount());
            ps.setDouble(5, b.getPaidAmount());
            ps.setDouble(6, b.getDiscount());
            ps.setDouble(7, b.getTax());
            ps.setString(8, b.getStatus());
            ps.setString(9, b.getNotes());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public Billing findById(int billId) throws SQLException {
        String sql = """
            SELECT b.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name
            FROM billing b JOIN patient p ON b.patient_id = p.patient_id
            WHERE b.bill_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Billing> findByPatient(int patientId) throws SQLException {
        String sql = """
            SELECT b.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name
            FROM billing b JOIN patient p ON b.patient_id = p.patient_id
            WHERE b.patient_id = ?
            ORDER BY b.bill_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    public List<Billing> findByStatus(String status) throws SQLException {
        String sql = """
            SELECT b.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name
            FROM billing b JOIN patient p ON b.patient_id = p.patient_id
            WHERE b.status = ?
            ORDER BY b.bill_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    public List<Billing> findAll(int page, int size) throws SQLException {
        String sql = """
            SELECT b.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name
            FROM billing b JOIN patient p ON b.patient_id = p.patient_id
            ORDER BY b.bill_date DESC LIMIT ? OFFSET ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, size); ps.setInt(2, (page-1)*size);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    /**
     * Apply a payment with full transaction control.
     * Demonstrates: Transaction + Savepoint + COMMIT + ROLLBACK.
     */
    public boolean applyPayment(int billId, double amount,
                                 String method, String ref) throws SQLException {
        Connection conn = DatabaseConnection.getFreshConnection();
        conn.setAutoCommit(false);
        Savepoint sp = null;
        try {
            sp = conn.setSavepoint("before_payment");

            // Insert payment row — trigger will update billing automatically
            String sql = "INSERT INTO payment(bill_id,amount,payment_date,payment_method,reference_number) VALUES(?,?,CURDATE(),?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt   (1, billId);
                ps.setDouble(2, amount);
                ps.setString(3, method);
                ps.setString(4, ref);
                ps.executeUpdate();
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            if (sp != null) conn.rollback(sp);
            conn.commit();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    /**
     * Batch update billing status based on computed payment coverage.
     * Demonstrates: Batch UPDATE.
     */
    public int[] batchRecalculateStatus(List<Integer> billIds) throws SQLException {
        String sql = """
            UPDATE billing
            SET status = CASE
                WHEN paid_amount >= total_amount THEN 'Paid'
                WHEN paid_amount > 0             THEN 'Partial'
                ELSE 'Pending'
            END
            WHERE bill_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int id : billIds) { ps.setInt(1, id); ps.addBatch(); }
            return ps.executeBatch();
        }
    }

    public boolean cancel(int billId) throws SQLException {
        String sql = "UPDATE billing SET status='Cancelled' WHERE bill_id=? AND status='Pending'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, billId);
            return ps.executeUpdate() > 0;
        }
    }

    // ── REPORTS ───────────────────────────────────────────────────────────────

    /**
     * Revenue summary using CASE, COALESCE, SUM.
     */
    public ResultSet getRevenueSummary(Connection conn) throws SQLException {
        String sql = """
            SELECT
                COUNT(*)                                      AS total_bills,
                COALESCE(SUM(total_amount),0)                 AS gross,
                COALESCE(SUM(paid_amount),0)                  AS collected,
                COALESCE(SUM(total_amount - paid_amount),0)   AS outstanding,
                COALESCE(AVG(total_amount),0)                  AS avg_bill,
                MAX(total_amount)                              AS highest_bill,
                MIN(CASE WHEN status!='Cancelled' THEN total_amount END) AS lowest_bill
            FROM billing WHERE status != 'Cancelled'
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Monthly revenue — uses the view.
     */
    public ResultSet getMonthlyRevenue(Connection conn, int year) throws SQLException {
        String sql = "SELECT * FROM v_revenue_report WHERE yr=? ORDER BY mo";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, year);
        return ps.executeQuery();
    }

    /**
     * Outstanding balance per patient.
     */
    public ResultSet getOutstandingByPatient(Connection conn) throws SQLException {
        String sql = """
            SELECT p.patient_id, CONCAT(p.first_name,' ',p.last_name) AS patient,
                   COUNT(b.bill_id) AS bills,
                   SUM(b.total_amount - b.paid_amount) AS outstanding
            FROM billing b JOIN patient p ON b.patient_id = p.patient_id
            WHERE b.status IN ('Pending','Partial')
            GROUP BY p.patient_id, p.first_name, p.last_name
            HAVING outstanding > 0 ORDER BY outstanding DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Calls stored function GetOutstandingBalance.
     */
    public double getOutstandingBalance(int patientId) throws SQLException {
        String sql = "SELECT GetOutstandingBalance(?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0;
            }
        }
    }

    // ── PRIVATE HELPERS ───────────────────────────────────────────────────────

    private List<Billing> mapList(ResultSet rs) throws SQLException {
        List<Billing> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Billing map(ResultSet rs) throws SQLException {
        Billing b = new Billing();
        b.setBillId     (rs.getInt      ("bill_id"));
        b.setPatientId  (rs.getInt      ("patient_id"));
        int vid = rs.getInt("visit_id"); b.setVisitId(rs.wasNull() ? null : vid);
        b.setBillDate   (rs.getDate     ("bill_date"));
        b.setTotalAmount(rs.getDouble   ("total_amount"));
        b.setPaidAmount (rs.getDouble   ("paid_amount"));
        b.setDiscount   (rs.getDouble   ("discount"));
        b.setTax        (rs.getDouble   ("tax"));
        b.setStatus     (rs.getString   ("status"));
        b.setNotes      (rs.getString   ("notes"));
        b.setCreatedAt  (rs.getTimestamp("created_at"));
        try { b.setPatientName(rs.getString("patient_name")); } catch (SQLException ignored) {}
        return b;
    }
}
