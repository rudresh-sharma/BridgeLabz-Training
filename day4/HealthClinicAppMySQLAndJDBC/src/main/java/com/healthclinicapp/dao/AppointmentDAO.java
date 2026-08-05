package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Appointment;
import com.healthclinicapp.util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the {@code appointment} table.
 *
 * SQL concepts demonstrated:
 *   CallableStatement (stored procedures),
 *   Date functions (CURDATE, BETWEEN, MONTH/YEAR),
 *   Status filtering, Pagination, Sorting.
 */
public class AppointmentDAO {

    // ── CREATE via Stored Procedure ───────────────────────────────────────────

    /**
     * Schedules an appointment by calling the {@code ScheduleAppointment} procedure.
     * Demonstrates: CallableStatement with IN and OUT parameters.
     */
    public int scheduleViaProcedure(int patientId, int doctorId,
                                     String date, String time, String reason) throws SQLException {
        String sql = "{CALL ScheduleAppointment(?,?,?,?,?,?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt   (1, patientId);
            cs.setInt   (2, doctorId);
            cs.setDate  (3, DateUtil.toSqlDate(date));
            cs.setString(4, time + ":00");  // MySQL TIME needs HH:MM:SS
            cs.setString(5, reason);
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            return cs.getInt(6);
        }
    }

    // ── CREATE direct PreparedStatement ───────────────────────────────────────

    public int insert(Appointment a) throws SQLException {
        String sql = """
            INSERT INTO appointment
                (patient_id,doctor_id,appointment_date,appointment_time,reason,notes)
            VALUES (?,?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt   (1, a.getPatientId());
            ps.setInt   (2, a.getDoctorId());
            ps.setDate  (3, a.getAppointmentDate());
            ps.setString(4, a.getAppointmentTime());
            ps.setString(5, a.getReason());
            ps.setString(6, a.getNotes());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    public Appointment findById(int id) throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.appointment_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Appointment> findAll(int page, int size) throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            ORDER BY a.appointment_date DESC, a.appointment_time DESC
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

    /** Today's appointments (DATE function). */
    public List<Appointment> findToday() throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.appointment_date = CURDATE()
            ORDER BY a.appointment_time
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapList(rs);
        }
    }

    /** This month's appointments (MONTH + YEAR). */
    public List<Appointment> findThisMonth() throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE MONTH(a.appointment_date) = MONTH(CURDATE())
              AND YEAR(a.appointment_date)  = YEAR(CURDATE())
            ORDER BY a.appointment_date, a.appointment_time
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapList(rs);
        }
    }

    /** Appointments by status. */
    public List<Appointment> findByStatus(String status) throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.status = ?
            ORDER BY a.appointment_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /** Appointments for a specific patient. */
    public List<Appointment> findByPatient(int patientId) throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.patient_id = ?
            ORDER BY a.appointment_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /** Appointments for a specific doctor. */
    public List<Appointment> findByDoctor(int doctorId) throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.doctor_id = ?
            ORDER BY a.appointment_date DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /** Appointments in a date range (BETWEEN). */
    public List<Appointment> findByDateRange(String from, String to) throws SQLException {
        String sql = """
            SELECT a.*, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            WHERE a.appointment_date BETWEEN ? AND ?
            ORDER BY a.appointment_date, a.appointment_time
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, DateUtil.toSqlDate(from));
            ps.setDate(2, DateUtil.toSqlDate(to));
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(*) FROM appointment";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE appointment SET status=? WHERE appointment_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt   (2, id);
            return ps.executeUpdate() > 0;
        }
    }

    /** Cancel via stored procedure. */
    public void cancelViaProcedure(int appointmentId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL CancelAppointment(?)}")) {
            cs.setInt(1, appointmentId);
            cs.execute();
        }
    }

    /** Batch-cancel a list of appointments. */
    public int[] batchCancel(List<Integer> ids) throws SQLException {
        String sql = "UPDATE appointment SET status='Cancelled' WHERE appointment_id=? AND status='Scheduled'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int id : ids) { ps.setInt(1, id); ps.addBatch(); }
            return ps.executeBatch();
        }
    }

    // ── STATISTICS ────────────────────────────────────────────────────────────

    /** Appointment status summary (GROUP BY). */
    public ResultSet getStatusSummary(Connection conn) throws SQLException {
        String sql = """
            SELECT status, COUNT(*) AS count,
                   ROUND(COUNT(*)*100.0/(SELECT COUNT(*) FROM appointment),2) AS pct
            FROM appointment
            GROUP BY status ORDER BY count DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── HELPERS ───────────────────────────────────────────────────────────────

    private List<Appointment> mapList(ResultSet rs) throws SQLException {
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Appointment map(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setAppointmentId  (rs.getInt      ("appointment_id"));
        a.setPatientId      (rs.getInt      ("patient_id"));
        a.setDoctorId       (rs.getInt      ("doctor_id"));
        a.setAppointmentDate(rs.getDate     ("appointment_date"));
        a.setAppointmentTime(rs.getString   ("appointment_time"));
        a.setStatus         (rs.getString   ("status"));
        a.setReason         (rs.getString   ("reason"));
        a.setNotes          (rs.getString   ("notes"));
        a.setCreatedAt      (rs.getTimestamp("created_at"));
        a.setUpdatedAt      (rs.getTimestamp("updated_at"));
        try { a.setPatientName(rs.getString("patient_name")); } catch (SQLException ignored) {}
        try { a.setDoctorName (rs.getString("doctor_name"));  } catch (SQLException ignored) {}
        return a;
    }
}
