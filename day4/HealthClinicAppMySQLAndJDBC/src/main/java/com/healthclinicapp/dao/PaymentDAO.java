package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for the payment table. */
public class PaymentDAO {

    public int insert(Payment p) throws SQLException {
        String sql = "INSERT INTO payment(bill_id,amount,payment_date,payment_method,reference_number) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1,p.getBillId()); ps.setDouble(2,p.getAmount());
            ps.setDate  (3,p.getPaymentDate()); ps.setString(4,p.getPaymentMethod());
            ps.setString(5,p.getReferenceNumber());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public List<Payment> findByBill(int billId) throws SQLException {
        String sql = "SELECT * FROM payment WHERE bill_id=? ORDER BY payment_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,billId);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    public ResultSet getPaymentMethodSummary(Connection conn) throws SQLException {
        String sql = """
            SELECT payment_method,
                   COUNT(*) AS transactions,
                   SUM(amount) AS total_amount,
                   ROUND(AVG(amount),2) AS avg_amount
            FROM payment
            GROUP BY payment_method ORDER BY total_amount DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    public ResultSet getPaymentsByDateRange(Connection conn, String from, String to) throws SQLException {
        String sql = "SELECT * FROM payment WHERE payment_date BETWEEN ? AND ? ORDER BY payment_date DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,from); ps.setString(2,to);
        return ps.executeQuery();
    }

    private List<Payment> mapList(ResultSet rs) throws SQLException {
        List<Payment> list = new ArrayList<>();
        while (rs.next()) {
            Payment p = new Payment();
            p.setPaymentId      (rs.getInt   ("payment_id"));
            p.setBillId         (rs.getInt   ("bill_id"));
            p.setAmount         (rs.getDouble("amount"));
            p.setPaymentDate    (rs.getDate  ("payment_date"));
            p.setPaymentMethod  (rs.getString("payment_method"));
            p.setReferenceNumber(rs.getString("reference_number"));
            list.add(p);
        }
        return list;
    }
}
