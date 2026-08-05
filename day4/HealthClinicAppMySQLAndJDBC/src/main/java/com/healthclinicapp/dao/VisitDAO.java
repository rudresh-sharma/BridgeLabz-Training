package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Visit;
import com.healthclinicapp.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the {@code visit} table.
 * SQL concepts: JOIN (appointment→patient→doctor), aggregate vitals.
 */
public class VisitDAO {

    public int insert(Visit v) throws SQLException {
        String sql = """
            INSERT INTO visit
                (appointment_id, visit_date, symptoms, diagnosis, treatment,
                 follow_up_date, weight, blood_pressure, temperature, notes)
            VALUES (?,?,?,?,?,?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1,  v.getAppointmentId());
            ps.setDate  (2,  v.getVisitDate());
            ps.setString(3,  v.getSymptoms());
            ps.setString(4,  v.getDiagnosis());
            ps.setString(5,  v.getTreatment());
            if (v.getFollowUpDate() != null) ps.setDate(6, v.getFollowUpDate());
            else ps.setNull(6, Types.DATE);
            ps.setDouble(7,  v.getWeight());
            ps.setString(8,  v.getBloodPressure());
            ps.setDouble(9,  v.getTemperature());
            ps.setString(10, v.getNotes());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public Visit findById(int visitId) throws SQLException {
        String sql = """
            SELECT v.*,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM visit v
            JOIN appointment a ON v.appointment_id = a.appointment_id
            JOIN patient p     ON a.patient_id     = p.patient_id
            JOIN doctor  d     ON a.doctor_id      = d.doctor_id
            WHERE v.visit_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, visitId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public Visit findByAppointmentId(int appointmentId) throws SQLException {
        String sql = "SELECT * FROM visit WHERE appointment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Visit> findByPatient(int patientId) throws SQLException {
        String sql = """
            SELECT v.*,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM visit v
            JOIN appointment a ON v.appointment_id = a.appointment_id
            JOIN patient p     ON a.patient_id     = p.patient_id
            JOIN doctor  d     ON a.doctor_id      = d.doctor_id
            WHERE a.patient_id = ?
            ORDER BY v.visit_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    public List<Visit> findAll(int page, int size) throws SQLException {
        String sql = """
            SELECT v.*,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM visit v
            JOIN appointment a ON v.appointment_id = a.appointment_id
            JOIN patient p     ON a.patient_id     = p.patient_id
            JOIN doctor  d     ON a.doctor_id      = d.doctor_id
            ORDER BY v.visit_date DESC
            LIMIT ? OFFSET ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page-1)*size);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    public boolean update(Visit v) throws SQLException {
        String sql = """
            UPDATE visit SET
                symptoms=?, diagnosis=?, treatment=?,
                follow_up_date=?, weight=?, blood_pressure=?,
                temperature=?, notes=?
            WHERE visit_id=?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getSymptoms());
            ps.setString(2, v.getDiagnosis());
            ps.setString(3, v.getTreatment());
            if (v.getFollowUpDate() != null) ps.setDate(4, v.getFollowUpDate());
            else ps.setNull(4, Types.DATE);
            ps.setDouble(5, v.getWeight());
            ps.setString(6, v.getBloodPressure());
            ps.setDouble(7, v.getTemperature());
            ps.setString(8, v.getNotes());
            ps.setInt   (9, v.getVisitId());
            return ps.executeUpdate() > 0;
        }
    }

    /** Visits with follow-up due today or overdue. */
    public ResultSet getFollowUpsOverdue(Connection conn) throws SQLException {
        String sql = """
            SELECT v.visit_id, v.visit_date, v.follow_up_date,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                   DATEDIFF(CURDATE(), v.follow_up_date) AS days_overdue
            FROM visit v
            JOIN appointment a ON v.appointment_id = a.appointment_id
            JOIN patient p     ON a.patient_id = p.patient_id
            JOIN doctor  d     ON a.doctor_id  = d.doctor_id
            WHERE v.follow_up_date IS NOT NULL AND v.follow_up_date <= CURDATE()
            ORDER BY v.follow_up_date
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /** Visit summary by date. */
    public ResultSet getDailySummary(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM v_daily_visits LIMIT 30").executeQuery();
    }

    private List<Visit> mapList(ResultSet rs) throws SQLException {
        List<Visit> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Visit map(ResultSet rs) throws SQLException {
        Visit v = new Visit();
        v.setVisitId      (rs.getInt      ("visit_id"));
        v.setAppointmentId(rs.getInt      ("appointment_id"));
        v.setVisitDate    (rs.getDate     ("visit_date"));
        v.setSymptoms     (rs.getString   ("symptoms"));
        v.setDiagnosis    (rs.getString   ("diagnosis"));
        v.setTreatment    (rs.getString   ("treatment"));
        v.setFollowUpDate (rs.getDate     ("follow_up_date"));
        v.setWeight       (rs.getDouble   ("weight"));
        v.setBloodPressure(rs.getString   ("blood_pressure"));
        v.setTemperature  (rs.getDouble   ("temperature"));
        v.setNotes        (rs.getString   ("notes"));
        v.setCreatedAt    (rs.getTimestamp("created_at"));
        try { v.setPatientName(rs.getString("patient_name")); } catch (SQLException ignored) {}
        try { v.setDoctorName (rs.getString("doctor_name"));  } catch (SQLException ignored) {}
        return v;
    }
}
