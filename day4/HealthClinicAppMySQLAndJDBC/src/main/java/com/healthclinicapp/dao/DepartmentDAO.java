package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the {@code department} table.
 *
 * SQL concepts: basic CRUD, FK update (head_doctor), JOIN reports.
 */
public class DepartmentDAO {

    public int insert(Department dept) throws SQLException {
        String sql = "INSERT INTO department(name,description) VALUES(?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dept.getName());
            ps.setString(2, dept.getDescription());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public Department findById(int id) throws SQLException {
        String sql = "SELECT * FROM department WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Department> findAll() throws SQLException {
        String sql = "SELECT * FROM department ORDER BY name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapList(rs);
        }
    }

    public boolean update(Department dept) throws SQLException {
        String sql = "UPDATE department SET name=?, description=? WHERE department_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dept.getName());
            ps.setString(2, dept.getDescription());
            ps.setInt   (3, dept.getDepartmentId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean setHeadDoctor(int deptId, int doctorId) throws SQLException {
        String sql = "UPDATE department SET head_doctor_id = ? WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, deptId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM department WHERE department_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    /** Full stats report using the view. */
    public ResultSet getStatistics(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM v_department_statistics ORDER BY num_doctors DESC").executeQuery();
    }

    /** Doctor count per department (GROUP BY). */
    public ResultSet getDoctorCountByDepartment(Connection conn) throws SQLException {
        String sql = """
            SELECT dep.name AS department,
                   COUNT(d.doctor_id)   AS doctor_count,
                   COUNT(DISTINCT r.room_id) AS room_count
            FROM department dep
            LEFT JOIN doctor d ON dep.department_id = d.department_id AND d.is_active = TRUE
            LEFT JOIN room   r ON dep.department_id = r.department_id
            GROUP BY dep.department_id, dep.name
            ORDER BY doctor_count DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    private List<Department> mapList(ResultSet rs) throws SQLException {
        List<Department> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Department map(ResultSet rs) throws SQLException {
        Department d = new Department();
        d.setDepartmentId (rs.getInt      ("department_id"));
        d.setName         (rs.getString   ("name"));
        d.setDescription  (rs.getString   ("description"));
        int hd = rs.getInt("head_doctor_id");
        d.setHeadDoctorId (rs.wasNull() ? null : hd);
        d.setCreatedAt    (rs.getTimestamp("created_at"));
        d.setUpdatedAt    (rs.getTimestamp("updated_at"));
        return d;
    }
}
