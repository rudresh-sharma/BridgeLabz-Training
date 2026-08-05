package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Inventory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for the inventory table — medicine purchase/stock records. */
public class InventoryDAO {

    public int insert(Inventory inv) throws SQLException {
        String sql = "INSERT INTO inventory(medicine_id,supplier_id,quantity,purchase_date,unit_cost,expiry_date,batch_number) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1,inv.getMedicineId());
            if (inv.getSupplierId()!=null) ps.setInt(2,inv.getSupplierId()); else ps.setNull(2,Types.INTEGER);
            ps.setInt   (3,inv.getQuantity());
            ps.setDate  (4,inv.getPurchaseDate());
            ps.setDouble(5,inv.getUnitCost());
            if (inv.getExpiryDate()!=null) ps.setDate(6,inv.getExpiryDate()); else ps.setNull(6,Types.DATE);
            ps.setString(7,inv.getBatchNumber());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public List<Inventory> findAll() throws SQLException {
        String sql = """
            SELECT i.*, m.name AS medicine_name, s.name AS supplier_name
            FROM inventory i
            JOIN medicine m ON i.medicine_id=m.medicine_id
            LEFT JOIN supplier s ON i.supplier_id=s.supplier_id
            ORDER BY i.purchase_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { return mapList(rs); }
    }

    public List<Inventory> findByMedicine(int medicineId) throws SQLException {
        String sql = """
            SELECT i.*, m.name AS medicine_name, s.name AS supplier_name
            FROM inventory i
            JOIN medicine m ON i.medicine_id=m.medicine_id
            LEFT JOIN supplier s ON i.supplier_id=s.supplier_id
            WHERE i.medicine_id=? ORDER BY i.purchase_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,medicineId);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    /** Expiring within N days (BETWEEN / DATE_ADD). */
    public ResultSet getExpiringSoon(Connection conn, int days) throws SQLException {
        String sql = """
            SELECT i.batch_number, m.name AS medicine, i.quantity,
                   i.expiry_date,
                   DATEDIFF(i.expiry_date, CURDATE()) AS days_to_expiry
            FROM inventory i
            JOIN medicine m ON i.medicine_id=m.medicine_id
            WHERE i.expiry_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY)
            ORDER BY i.expiry_date
            """;
        PreparedStatement ps = conn.prepareStatement(sql); ps.setInt(1,days);
        return ps.executeQuery();
    }

    /** Batch insert inventory records. */
    public int[] batchInsert(List<Inventory> records) throws SQLException {
        String sql = "INSERT INTO inventory(medicine_id,supplier_id,quantity,purchase_date,unit_cost,batch_number) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Inventory i : records) {
                ps.setInt(1,i.getMedicineId());
                if (i.getSupplierId()!=null) ps.setInt(2,i.getSupplierId()); else ps.setNull(2,Types.INTEGER);
                ps.setInt(3,i.getQuantity()); ps.setDate(4,i.getPurchaseDate());
                ps.setDouble(5,i.getUnitCost()); ps.setString(6,i.getBatchNumber());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }

    public ResultSet getPurchaseSummary(Connection conn) throws SQLException {
        String sql = """
            SELECT m.name AS medicine, s.name AS supplier,
                   SUM(i.quantity) AS total_purchased,
                   SUM(i.quantity * i.unit_cost) AS total_cost,
                   MAX(i.purchase_date) AS last_purchase
            FROM inventory i
            JOIN medicine m ON i.medicine_id=m.medicine_id
            LEFT JOIN supplier s ON i.supplier_id=s.supplier_id
            GROUP BY m.medicine_id, m.name, s.name
            ORDER BY total_cost DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    private List<Inventory> mapList(ResultSet rs) throws SQLException {
        List<Inventory> list = new ArrayList<>();
        while (rs.next()) {
            Inventory inv = new Inventory();
            inv.setInventoryId (rs.getInt   ("inventory_id"));
            inv.setMedicineId  (rs.getInt   ("medicine_id"));
            int sid = rs.getInt("supplier_id"); inv.setSupplierId(rs.wasNull()?null:sid);
            inv.setQuantity    (rs.getInt   ("quantity"));
            inv.setPurchaseDate(rs.getDate  ("purchase_date"));
            inv.setUnitCost    (rs.getDouble("unit_cost"));
            inv.setExpiryDate  (rs.getDate  ("expiry_date"));
            inv.setBatchNumber (rs.getString("batch_number"));
            try { inv.setMedicineName(rs.getString("medicine_name")); } catch (SQLException ignored) {}
            try { inv.setSupplierName(rs.getString("supplier_name")); } catch (SQLException ignored) {}
            list.add(inv);
        }
        return list;
    }
}
