package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.LabReport;
import com.healthclinicapp.model.LabTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for lab_test and lab_report tables. */
public class LabDAO {

    // ── Lab Tests ─────────────────────────────────────────────────────────────

    public int insertTest(LabTest t) throws SQLException {
        String sql = "INSERT INTO lab_test(test_name,description,normal_range,unit,price) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,t.getTestName()); ps.setString(2,t.getDescription());
            ps.setString(3,t.getNormalRange()); ps.setString(4,t.getUnit()); ps.setDouble(5,t.getPrice());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public List<LabTest> findAllTests() throws SQLException {
        String sql = "SELECT * FROM lab_test ORDER BY test_name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<LabTest> list = new ArrayList<>();
            while (rs.next()) {
                LabTest t = new LabTest();
                t.setTestId(rs.getInt("test_id")); t.setTestName(rs.getString("test_name"));
                t.setDescription(rs.getString("description")); t.setNormalRange(rs.getString("normal_range"));
                t.setUnit(rs.getString("unit")); t.setPrice(rs.getDouble("price"));
                list.add(t);
            }
            return list;
        }
    }

    // ── Lab Reports ───────────────────────────────────────────────────────────

    public int insertReport(LabReport r) throws SQLException {
        String sql = "INSERT INTO lab_report(visit_id,test_id,test_date,result,is_normal,remarks) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1,r.getVisitId()); ps.setInt(2,r.getTestId());
            ps.setDate  (3,r.getTestDate()); ps.setString(4,r.getResult());
            if (r.getIsNormal()!=null) ps.setBoolean(5,r.getIsNormal()); else ps.setNull(5,Types.BOOLEAN);
            ps.setString(6,r.getRemarks());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public List<LabReport> findByVisit(int visitId) throws SQLException {
        String sql = """
            SELECT lr.*, lt.test_name
            FROM lab_report lr JOIN lab_test lt ON lr.test_id = lt.test_id
            WHERE lr.visit_id = ? ORDER BY lr.test_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, visitId);
            try (ResultSet rs = ps.executeQuery()) { return mapReportList(rs); }
        }
    }

    public List<LabReport> findAbnormalReports() throws SQLException {
        String sql = """
            SELECT lr.*, lt.test_name
            FROM lab_report lr JOIN lab_test lt ON lr.test_id = lt.test_id
            WHERE lr.is_normal = FALSE ORDER BY lr.created_at DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { return mapReportList(rs); }
    }

    public ResultSet getLabSummary(Connection conn) throws SQLException {
        String sql = """
            SELECT lt.test_name, COUNT(lr.report_id) AS total_conducted,
                   SUM(lr.is_normal=TRUE) AS normal_count,
                   SUM(lr.is_normal=FALSE) AS abnormal_count,
                   lt.price
            FROM lab_test lt
            LEFT JOIN lab_report lr ON lt.test_id = lr.test_id
            GROUP BY lt.test_id, lt.test_name, lt.price
            ORDER BY total_conducted DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    private List<LabReport> mapReportList(ResultSet rs) throws SQLException {
        List<LabReport> list = new ArrayList<>();
        while (rs.next()) {
            LabReport r = new LabReport();
            r.setReportId(rs.getInt("report_id")); r.setVisitId(rs.getInt("visit_id"));
            r.setTestId(rs.getInt("test_id")); r.setTestDate(rs.getDate("test_date"));
            r.setResult(rs.getString("result")); r.setIsNormal(rs.getBoolean("is_normal"));
            r.setRemarks(rs.getString("remarks")); r.setCreatedAt(rs.getTimestamp("created_at"));
            try { r.setTestName(rs.getString("test_name")); } catch (SQLException ignored) {}
            list.add(r);
        }
        return list;
    }
}
