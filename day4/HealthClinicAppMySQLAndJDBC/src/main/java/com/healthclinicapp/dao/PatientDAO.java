package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Patient;
import com.healthclinicapp.util.DateUtil;
import com.healthclinicapp.util.ValidationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-access object for the {@code patient} table.
 *
 * SQL concepts demonstrated here:
 *   PreparedStatement CRUD, RETURN_GENERATED_KEYS,
 *   Pagination (LIMIT/OFFSET), LIKE search, BETWEEN dates,
 *   IN clause, IS NULL/IS NOT NULL, Transactions + Savepoints,
 *   Batch insert/update, Subquery in DELETE, Aggregate functions.
 */
public class PatientDAO {

    // ── CREATE ────────────────────────────────────────────────────────────────

    /**
     * Inserts a new patient and returns the generated patient_id.
     * Demonstrates: PreparedStatement + RETURN_GENERATED_KEYS.
     */
    public int insert(Patient p) throws SQLException {
        String sql = """
            INSERT INTO patient
                (first_name, last_name, date_of_birth, gender,
                 blood_group, phone, email, address, city)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setDate  (3, p.getDateOfBirth());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getBloodGroup());
            ps.setString(6, p.getPhone());
            ps.setString(7, p.getEmail());
            ps.setString(8, p.getAddress());
            ps.setString(9, p.getCity());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    /** Get a single patient by ID. */
    public Patient findById(int patientId) throws SQLException {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    /** Get a patient by phone number. */
    public Patient findByPhone(String phone) throws SQLException {
        String sql = "SELECT * FROM patient WHERE phone = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    /**
     * Get all patients with pagination.
     * Demonstrates: LIMIT / OFFSET.
     */
    public List<Patient> findAll(int page, int pageSize) throws SQLException {
        String sql = """
            SELECT * FROM patient
            WHERE is_active = TRUE
            ORDER BY last_name, first_name
            LIMIT ? OFFSET ?
            """;
        return queryList(sql, pageSize, (page - 1) * pageSize);
    }

    /** Count total active patients (for pagination). */
    public int countAll() throws SQLException {
        String sql = "SELECT COUNT(*) FROM patient WHERE is_active = TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    /**
     * Full-text search by name or phone.
     * Demonstrates: LIKE with sanitized wildcard.
     */
    public List<Patient> search(String term) throws SQLException {
        String like = "%" + ValidationUtil.sanitizeLike(term) + "%";
        String sql = """
            SELECT * FROM patient
            WHERE (first_name LIKE ? OR last_name LIKE ?
                   OR phone LIKE ? OR email LIKE ?)
            AND is_active = TRUE
            ORDER BY last_name, first_name
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);
            ps.setString(4, like);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /**
     * Filter patients by gender.
     * Demonstrates: IN clause.
     */
    public List<Patient> findByGender(String gender) throws SQLException {
        String sql = "SELECT * FROM patient WHERE gender = ? AND is_active = TRUE ORDER BY first_name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, gender);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /**
     * Find patients registered between two dates.
     * Demonstrates: BETWEEN.
     */
    public List<Patient> findByRegistrationDateRange(String fromDate, String toDate) throws SQLException {
        String sql = """
            SELECT * FROM patient
            WHERE DATE(registered_at) BETWEEN ? AND ?
            ORDER BY registered_at DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, DateUtil.toSqlDate(fromDate));
            ps.setDate(2, DateUtil.toSqlDate(toDate));
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /**
     * Find patients who have never had an appointment.
     * Demonstrates: IS NULL / NOT EXISTS.
     */
    public List<Patient> findPatientsWithNoAppointments() throws SQLException {
        String sql = """
            SELECT * FROM patient p
            WHERE NOT EXISTS (
                SELECT 1 FROM appointment a WHERE a.patient_id = p.patient_id
            )
            ORDER BY p.registered_at DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapList(rs);
        }
    }

    /**
     * Find patients by blood group (IN clause demo).
     */
    public List<Patient> findByBloodGroups(List<String> bloodGroups) throws SQLException {
        if (bloodGroups.isEmpty()) return new ArrayList<>();
        String placeholders = "?,".repeat(bloodGroups.size());
        placeholders = placeholders.substring(0, placeholders.length() - 1);
        String sql = "SELECT * FROM patient WHERE blood_group IN (" + placeholders + ") AND is_active=TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < bloodGroups.size(); i++) ps.setString(i + 1, bloodGroups.get(i));
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /**
     * Find patients by age range.
     * Demonstrates: BETWEEN with computed age.
     */
    public List<Patient> findByAgeRange(int minAge, int maxAge) throws SQLException {
        String sql = """
            SELECT * FROM patient
            WHERE TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) BETWEEN ? AND ?
            AND is_active = TRUE
            ORDER BY date_of_birth
            """;
        return queryList(sql, minAge, maxAge);
    }

    /**
     * Find patients from a specific city.
     * Demonstrates: LIKE pattern matching.
     */
    public List<Patient> findByCity(String city) throws SQLException {
        String sql = "SELECT * FROM patient WHERE city LIKE ? AND is_active = TRUE ORDER BY first_name";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + ValidationUtil.sanitizeLike(city) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    /** Update all editable fields for a patient. */
    public boolean update(Patient p) throws SQLException {
        String sql = """
            UPDATE patient SET
                first_name    = ?, last_name  = ?, date_of_birth = ?,
                gender        = ?, blood_group= ?, phone         = ?,
                email         = ?, address    = ?, city          = ?
            WHERE patient_id  = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setDate  (3, p.getDateOfBirth());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getBloodGroup());
            ps.setString(6, p.getPhone());
            ps.setString(7, p.getEmail());
            ps.setString(8, p.getAddress());
            ps.setString(9, p.getCity());
            ps.setInt   (10, p.getPatientId());
            return ps.executeUpdate() > 0;
        }
    }

    /** Soft-delete a patient (set is_active = FALSE). */
    public boolean deactivate(int patientId) throws SQLException {
        String sql = "UPDATE patient SET is_active = FALSE WHERE patient_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            return ps.executeUpdate() > 0;
        }
    }

    // ── DELETE ────────────────────────────────────────────────────────────────

    /**
     * Hard delete a patient who has no appointments.
     * Demonstrates: DELETE with subquery.
     */
    public boolean deleteWithNoAppointments(int patientId) throws SQLException {
        String sql = """
            DELETE FROM patient
            WHERE patient_id = ?
            AND patient_id NOT IN (SELECT DISTINCT patient_id FROM appointment)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            return ps.executeUpdate() > 0;
        }
    }

    // ── TRANSACTIONS + SAVEPOINTS ─────────────────────────────────────────────

    /**
     * Inserts a patient with an optional emergency contact — wrapped in a
     * transaction with a savepoint.
     *
     * Demonstrates: BEGIN, SAVEPOINT, ROLLBACK TO, COMMIT.
     */
    public int insertWithEmergencyContact(
            Patient patient, String contactName,
            String relationship, String contactPhone) throws SQLException {

        Connection conn = DatabaseConnection.getFreshConnection();
        conn.setAutoCommit(false);
        Savepoint sp = null;
        int newId = -1;
        try {
            // Step 1 – Insert patient
            String sql1 = """
                INSERT INTO patient(first_name,last_name,date_of_birth,gender,
                                    blood_group,phone,email,address,city)
                VALUES(?,?,?,?,?,?,?,?,?)
                """;
            try (PreparedStatement ps = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, patient.getFirstName());
                ps.setString(2, patient.getLastName());
                ps.setDate  (3, patient.getDateOfBirth());
                ps.setString(4, patient.getGender());
                ps.setString(5, patient.getBloodGroup());
                ps.setString(6, patient.getPhone());
                ps.setString(7, patient.getEmail());
                ps.setString(8, patient.getAddress());
                ps.setString(9, patient.getCity());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) newId = keys.getInt(1);
                }
            }

            // Savepoint after patient insert
            sp = conn.setSavepoint("after_patient");

            // Step 2 – Insert emergency contact
            if (contactName != null && !contactName.isBlank()) {
                String sql2 = "INSERT INTO emergency_contact(patient_id,name,relationship,phone) VALUES(?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql2)) {
                    ps.setInt   (1, newId);
                    ps.setString(2, contactName);
                    ps.setString(3, relationship);
                    ps.setString(4, contactPhone);
                    ps.executeUpdate();
                }
            }

            conn.commit();
            return newId;
        } catch (SQLException e) {
            if (sp != null) { try { conn.rollback(sp); conn.commit(); } catch (SQLException ex) { conn.rollback(); } }
            else { conn.rollback(); }
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    // ── BATCH OPERATIONS ──────────────────────────────────────────────────────

    /**
     * Insert multiple patients in a single batch.
     * Demonstrates: Batch processing.
     */
    public int[] batchInsert(List<Patient> patients) throws SQLException {
        String sql = """
            INSERT INTO patient(first_name,last_name,date_of_birth,gender,phone)
            VALUES(?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Patient p : patients) {
                ps.setString(1, p.getFirstName());
                ps.setString(2, p.getLastName());
                ps.setDate  (3, p.getDateOfBirth());
                ps.setString(4, p.getGender());
                ps.setString(5, p.getPhone());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }

    // ── REPORTS ───────────────────────────────────────────────────────────────

    /**
     * Returns total, male, female, other patient counts.
     * Demonstrates: GROUP BY + aggregate functions.
     */
    public ResultSet getGenderStats(Connection conn) throws SQLException {
        String sql = """
            SELECT gender,
                   COUNT(*)                           AS total,
                   ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (), 2) AS percentage
            FROM patient
            WHERE is_active = TRUE
            GROUP BY gender
            ORDER BY total DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Age distribution of patients.
     * Demonstrates: CASE, GROUP BY.
     */
    public ResultSet getAgeDistribution(Connection conn) throws SQLException {
        String sql = """
            SELECT
                CASE
                    WHEN TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) < 18 THEN 'Under 18'
                    WHEN TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) BETWEEN 18 AND 35 THEN '18-35'
                    WHEN TIMESTAMPDIFF(YEAR, date_of_birth, CURDATE()) BETWEEN 36 AND 55 THEN '36-55'
                    ELSE 'Over 55'
                END AS age_group,
                COUNT(*) AS patient_count
            FROM patient WHERE is_active = TRUE
            GROUP BY age_group
            ORDER BY patient_count DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Patients with highest outstanding balances.
     * Demonstrates: JOIN + ORDER BY + LIMIT.
     */
    public ResultSet getTopDebtors(Connection conn, int limit) throws SQLException {
        String sql = """
            SELECT p.patient_id,
                   CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   p.phone,
                   COUNT(b.bill_id)                      AS bill_count,
                   SUM(b.total_amount - b.paid_amount)   AS outstanding
            FROM patient p
            JOIN billing b ON p.patient_id = b.patient_id
            WHERE b.status IN ('Pending','Partial')
            GROUP BY p.patient_id, p.first_name, p.last_name, p.phone
            ORDER BY outstanding DESC
            LIMIT ?
            """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, limit);
        return ps.executeQuery();
    }

    // ── PRIVATE HELPERS ───────────────────────────────────────────────────────

    private List<Patient> queryList(String sql, Object... params) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                if      (params[i] instanceof Integer) ps.setInt   (i+1,(Integer)params[i]);
                else if (params[i] instanceof String)  ps.setString(i+1,(String) params[i]);
                else if (params[i] instanceof Date)    ps.setDate  (i+1,(Date)   params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    private List<Patient> mapList(ResultSet rs) throws SQLException {
        List<Patient> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Patient map(ResultSet rs) throws SQLException {
        Patient p = new Patient();
        p.setPatientId   (rs.getInt       ("patient_id"));
        p.setFirstName   (rs.getString    ("first_name"));
        p.setLastName    (rs.getString    ("last_name"));
        p.setDateOfBirth (rs.getDate      ("date_of_birth"));
        p.setGender      (rs.getString    ("gender"));
        p.setBloodGroup  (rs.getString    ("blood_group"));
        p.setPhone       (rs.getString    ("phone"));
        p.setEmail       (rs.getString    ("email"));
        p.setAddress     (rs.getString    ("address"));
        p.setCity        (rs.getString    ("city"));
        p.setActive      (rs.getBoolean   ("is_active"));
        p.setRegisteredAt(rs.getTimestamp ("registered_at"));
        p.setUpdatedAt   (rs.getTimestamp ("updated_at"));
        return p;
    }
}
