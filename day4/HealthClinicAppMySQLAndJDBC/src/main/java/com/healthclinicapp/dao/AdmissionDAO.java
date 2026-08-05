package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Admission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for the admission table. Uses CallableStatement for discharge. */
public class AdmissionDAO {

    public int insert(Admission a) throws SQLException {
        String sql = "INSERT INTO admission(patient_id,room_id,doctor_id,admission_date,reason,status) VALUES(?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1,a.getPatientId()); ps.setInt(2,a.getRoomId()); ps.setInt(3,a.getDoctorId());
            ps.setDate  (4,a.getAdmissionDate()); ps.setString(5,a.getReason()); ps.setString(6,a.getStatus());
            ps.executeUpdate();
            // Mark room as occupied
            try (PreparedStatement ps2 = conn.prepareStatement("UPDATE room SET is_available=FALSE WHERE room_id=?")) {
                ps2.setInt(1,a.getRoomId()); ps2.executeUpdate();
            }
            try (ResultSet keys = ps.getGeneratedKeys()) { return keys.next()?keys.getInt(1):-1; }
        }
    }

    public Admission findById(int id) throws SQLException {
        String sql = """
            SELECT a.*,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   r.room_number,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM admission a
            JOIN patient p ON a.patient_id=p.patient_id
            JOIN room    r ON a.room_id   =r.room_id
            JOIN doctor  d ON a.doctor_id =d.doctor_id
            WHERE a.admission_id=?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) { return rs.next()?map(rs):null; }
        }
    }

    public List<Admission> findActive() throws SQLException {
        String sql = """
            SELECT a.*,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   r.room_number,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM admission a
            JOIN patient p ON a.patient_id=p.patient_id
            JOIN room    r ON a.room_id   =r.room_id
            JOIN doctor  d ON a.doctor_id =d.doctor_id
            WHERE a.status='Active' ORDER BY a.admission_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { return mapList(rs); }
    }

    public List<Admission> findByPatient(int patientId) throws SQLException {
        String sql = """
            SELECT a.*,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   r.room_number,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM admission a
            JOIN patient p ON a.patient_id=p.patient_id
            JOIN room    r ON a.room_id   =r.room_id
            JOIN doctor  d ON a.doctor_id =d.doctor_id
            WHERE a.patient_id=? ORDER BY a.admission_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,patientId);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    /** Discharge via stored procedure. */
    public void dischargeViaProcedure(int admissionId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL DischargePatient(?)}")) {
            cs.setInt(1,admissionId); cs.execute();
        }
    }

    public ResultSet getAdmissionStats(Connection conn) throws SQLException {
        String sql = """
            SELECT status, COUNT(*) AS count,
                   AVG(DATEDIFF(IFNULL(discharge_date, CURDATE()), admission_date)) AS avg_stay_days
            FROM admission GROUP BY status
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    private List<Admission> mapList(ResultSet rs) throws SQLException {
        List<Admission> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Admission map(ResultSet rs) throws SQLException {
        Admission a = new Admission();
        a.setAdmissionId  (rs.getInt   ("admission_id"));
        a.setPatientId    (rs.getInt   ("patient_id"));
        a.setRoomId       (rs.getInt   ("room_id"));
        a.setDoctorId     (rs.getInt   ("doctor_id"));
        a.setAdmissionDate(rs.getDate  ("admission_date"));
        a.setDischargeDate(rs.getDate  ("discharge_date"));
        a.setReason       (rs.getString("reason"));
        a.setStatus       (rs.getString("status"));
        try { a.setPatientName(rs.getString("patient_name")); } catch (SQLException ignored) {}
        try { a.setRoomNumber (rs.getString("room_number"));  } catch (SQLException ignored) {}
        try { a.setDoctorName (rs.getString("doctor_name"));  } catch (SQLException ignored) {}
        return a;
    }
}
