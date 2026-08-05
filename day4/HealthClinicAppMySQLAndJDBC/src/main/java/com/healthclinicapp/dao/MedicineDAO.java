package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Medicine;
import com.healthclinicapp.util.ValidationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the {@code medicine} table.
 * SQL concepts: LIKE search, category filtering, low-stock view,
 *               stored function (IsMedicineAvailable), batch insert.
 */
public class MedicineDAO {

    public int insert(Medicine m) throws SQLException {
        String sql = """
            INSERT INTO medicine(name,generic_name,category,unit,
                                 unit_price,stock_quantity,min_stock_level,description)
            VALUES(?,?,?,?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getName());
            ps.setString(2, m.getGenericName());
            ps.setString(3, m.getCategory());
            ps.setString(4, m.getUnit());
            ps.setDouble(5, m.getUnitPrice());
            ps.setInt   (6, m.getStockQuantity());
            ps.setInt   (7, m.getMinStockLevel());
            ps.setString(8, m.getDescription());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public Medicine findById(int id) throws SQLException {
        String sql = "SELECT * FROM medicine WHERE medicine_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Medicine> findAll() throws SQLException {
        String sql = "SELECT * FROM medicine WHERE is_active=TRUE ORDER BY category,name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapList(rs);
        }
    }

    public List<Medicine> search(String term) throws SQLException {
        String like = "%" + ValidationUtil.sanitizeLike(term) + "%";
        String sql  = "SELECT * FROM medicine WHERE (name LIKE ? OR generic_name LIKE ? OR category LIKE ?) AND is_active=TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    public List<Medicine> findByCategory(String category) throws SQLException {
        String sql = "SELECT * FROM medicine WHERE category=? AND is_active=TRUE ORDER BY name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    /** Low-stock medicines using the view. */
    public ResultSet getLowStock(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM v_low_stock ORDER BY stock_quantity").executeQuery();
    }

    /** Full inventory view. */
    public ResultSet getInventoryView(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM v_medicine_inventory ORDER BY category, name").executeQuery();
    }

    public boolean update(Medicine m) throws SQLException {
        String sql = """
            UPDATE medicine SET name=?,generic_name=?,category=?,unit=?,
                unit_price=?,min_stock_level=?,description=?
            WHERE medicine_id=?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getName());  ps.setString(2, m.getGenericName());
            ps.setString(3, m.getCategory()); ps.setString(4, m.getUnit());
            ps.setDouble(5, m.getUnitPrice()); ps.setInt(6, m.getMinStockLevel());
            ps.setString(7, m.getDescription()); ps.setInt(8, m.getMedicineId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateStock(int medicineId, int qty) throws SQLException {
        String sql = "UPDATE medicine SET stock_quantity = stock_quantity + ? WHERE medicine_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, qty); ps.setInt(2, medicineId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deactivate(int id) throws SQLException {
        String sql = "UPDATE medicine SET is_active=FALSE WHERE medicine_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id); return ps.executeUpdate() > 0;
        }
    }

    /** Checks availability using stored function IsMedicineAvailable. */
    public boolean isAvailable(int medicineId, int qty) throws SQLException {
        String sql = "SELECT IsMedicineAvailable(?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId); ps.setInt(2, qty);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getBoolean(1);
            }
        }
    }

    /** Distinct categories. */
    public ResultSet getCategories(Connection conn) throws SQLException {
        return conn.prepareStatement(
            "SELECT DISTINCT category, COUNT(*) AS cnt FROM medicine WHERE is_active=TRUE GROUP BY category ORDER BY category"
        ).executeQuery();
    }

    /** Most prescribed medicines. */
    public ResultSet getMostPrescribed(Connection conn, int limit) throws SQLException {
        String sql = """
            SELECT m.name, m.category,
                   COUNT(pi.item_id) AS prescription_count,
                   SUM(pi.quantity)  AS total_qty_prescribed
            FROM prescription_item pi
            JOIN medicine m ON pi.medicine_id = m.medicine_id
            GROUP BY m.medicine_id, m.name, m.category
            ORDER BY prescription_count DESC
            LIMIT ?
            """;
        PreparedStatement ps = conn.prepareStatement(sql); ps.setInt(1, limit);
        return ps.executeQuery();
    }

    /** Batch insert medicines. */
    public int[] batchInsert(List<Medicine> medicines) throws SQLException {
        String sql = "INSERT INTO medicine(name,generic_name,category,unit,unit_price,stock_quantity) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Medicine m : medicines) {
                ps.setString(1,m.getName()); ps.setString(2,m.getGenericName());
                ps.setString(3,m.getCategory()); ps.setString(4,m.getUnit());
                ps.setDouble(5,m.getUnitPrice()); ps.setInt(6,m.getStockQuantity());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }

    private List<Medicine> mapList(ResultSet rs) throws SQLException {
        List<Medicine> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Medicine map(ResultSet rs) throws SQLException {
        Medicine m = new Medicine();
        m.setMedicineId   (rs.getInt    ("medicine_id"));
        m.setName         (rs.getString ("name"));
        m.setGenericName  (rs.getString ("generic_name"));
        m.setCategory     (rs.getString ("category"));
        m.setUnit         (rs.getString ("unit"));
        m.setUnitPrice    (rs.getDouble ("unit_price"));
        m.setStockQuantity(rs.getInt    ("stock_quantity"));
        m.setMinStockLevel(rs.getInt    ("min_stock_level"));
        m.setDescription  (rs.getString ("description"));
        m.setActive       (rs.getBoolean("is_active"));
        return m;
    }
}
