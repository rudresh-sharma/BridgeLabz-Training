package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Insurance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for the insurance table. */
public class InsuranceDAO {

    public int insert(Insurance ins) throws SQLException {
        String sql = "INSERT INTO insurance(patient_id,provider_name,policy_number,coverage_amount,valid_from,valid_to) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1,ins.getPatientId()); ps.setString(2,ins.getProviderName());
            ps.setString(3,ins.getPolicyNumber()); ps.setDouble(4,ins.getCoverageAmount());
            ps.setDate  (5,ins.getValidFrom()); ps.setDate(6,ins.getValidTo());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public List<Insurance> findByPatient(int patientId) throws SQLException {
        String sql = "SELECT * FROM insurance WHERE patient_id=? ORDER BY valid_to DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,patientId);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    public List<Insurance> findActive() throws SQLException {
        String sql = "SELECT * FROM insurance WHERE is_active=TRUE AND valid_to >= CURDATE() ORDER BY valid_to";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { return mapList(rs); }
    }

    /** Expiring within N days. */
    public List<Insurance> findExpiringSoon(int days) throws SQLException {
        String sql = "SELECT * FROM insurance WHERE valid_to BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY) AND is_active=TRUE ORDER BY valid_to";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,days);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    /** Insurance summary view. */
    public ResultSet getSummaryView(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM v_insurance_summary ORDER BY days_remaining").executeQuery();
    }

    public boolean deactivate(int id) throws SQLException {
        String sql = "UPDATE insurance SET is_active=FALSE WHERE insurance_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id); return ps.executeUpdate()>0;
        }
    }

    private List<Insurance> mapList(ResultSet rs) throws SQLException {
        List<Insurance> list = new ArrayList<>();
        while (rs.next()) {
            Insurance i = new Insurance();
            i.setInsuranceId   (rs.getInt    ("insurance_id"));
            i.setPatientId     (rs.getInt    ("patient_id"));
            i.setProviderName  (rs.getString ("provider_name"));
            i.setPolicyNumber  (rs.getString ("policy_number"));
            i.setCoverageAmount(rs.getDouble ("coverage_amount"));
            i.setValidFrom     (rs.getDate   ("valid_from"));
            i.setValidTo       (rs.getDate   ("valid_to"));
            i.setActive        (rs.getBoolean("is_active"));
            list.add(i);
        }
        return list;
    }
}
