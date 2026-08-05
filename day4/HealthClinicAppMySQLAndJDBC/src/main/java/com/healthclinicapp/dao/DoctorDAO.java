package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.Doctor;
import com.healthclinicapp.util.ValidationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-access object for the {@code doctor} table.
 *
 * SQL concepts demonstrated:
 *   JOIN (department), GROUP BY + HAVING, ORDER BY with alias,
 *   Self-join (doctor referral / seniority ranking),
 *   Aggregate functions (COUNT, AVG, MAX, MIN),
 *   Subquery, UNION.
 */
public class DoctorDAO {

    // ── CREATE ────────────────────────────────────────────────────────────────

    public int insert(Doctor d) throws SQLException {
        String sql = """
            INSERT INTO doctor
                (first_name,last_name,specialization,department_id,
                 phone,email,salary,join_date)
            VALUES (?,?,?,?,?,?,?,?)
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getSpecialization());
            ps.setInt   (4, d.getDepartmentId());
            ps.setString(5, d.getPhone());
            ps.setString(6, d.getEmail());
            ps.setDouble(7, d.getSalary());
            ps.setDate  (8, d.getJoinDate());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    // ── READ ──────────────────────────────────────────────────────────────────

    /** Get a doctor by ID — includes department name via JOIN. */
    public Doctor findById(int doctorId) throws SQLException {
        String sql = """
            SELECT d.*, dep.name AS department_name
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            WHERE d.doctor_id = ?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    /** Get all active doctors with their department name. */
    public List<Doctor> findAll() throws SQLException {
        String sql = """
            SELECT d.*, dep.name AS department_name
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            WHERE d.is_active = TRUE
            ORDER BY dep.name, d.last_name
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return mapList(rs);
        }
    }

    /** Find doctors by department. */
    public List<Doctor> findByDepartment(int departmentId) throws SQLException {
        String sql = """
            SELECT d.*, dep.name AS department_name
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            WHERE d.department_id = ? AND d.is_active = TRUE
            ORDER BY d.last_name
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    /** Search by name or specialization (LIKE). */
    public List<Doctor> search(String term) throws SQLException {
        String like = "%" + ValidationUtil.sanitizeLike(term) + "%";
        String sql = """
            SELECT d.*, dep.name AS department_name
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            WHERE (d.first_name LIKE ? OR d.last_name LIKE ? OR d.specialization LIKE ?)
            AND d.is_active = TRUE
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, like); ps.setString(2, like); ps.setString(3, like);
            try (ResultSet rs = ps.executeQuery()) {
                return mapList(rs);
            }
        }
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────

    public boolean update(Doctor d) throws SQLException {
        String sql = """
            UPDATE doctor SET
                first_name=?, last_name=?, specialization=?,
                department_id=?, phone=?, email=?, salary=?
            WHERE doctor_id=?
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getSpecialization());
            ps.setInt   (4, d.getDepartmentId());
            ps.setString(5, d.getPhone());
            ps.setString(6, d.getEmail());
            ps.setDouble(7, d.getSalary());
            ps.setInt   (8, d.getDoctorId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deactivate(int doctorId) throws SQLException {
        String sql = "UPDATE doctor SET is_active = FALSE WHERE doctor_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            return ps.executeUpdate() > 0;
        }
    }

    // ── REPORTS / COMPLEX QUERIES ─────────────────────────────────────────────

    /**
     * Doctors with more than N completed appointments.
     * Demonstrates: JOIN + GROUP BY + HAVING.
     */
    public ResultSet getDoctorsWithMinAppointments(Connection conn, int minCount) throws SQLException {
        String sql = """
            SELECT d.doctor_id,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                   d.specialization,
                   dep.name AS department,
                   COUNT(a.appointment_id)  AS total_appointments,
                   SUM(a.status='Completed') AS completed
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            LEFT JOIN appointment a ON d.doctor_id = a.doctor_id
            GROUP BY d.doctor_id, d.first_name, d.last_name, d.specialization, dep.name
            HAVING completed >= ?
            ORDER BY completed DESC
            """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, minCount);
        return ps.executeQuery();
    }

    /**
     * Average salary per department.
     * Demonstrates: GROUP BY + AVG + ORDER BY alias.
     */
    public ResultSet getAvgSalaryByDepartment(Connection conn) throws SQLException {
        String sql = """
            SELECT dep.name AS department,
                   COUNT(d.doctor_id)   AS num_doctors,
                   ROUND(AVG(d.salary),2) AS avg_salary,
                   MAX(d.salary)          AS max_salary,
                   MIN(d.salary)          AS min_salary
            FROM department dep
            LEFT JOIN doctor d ON dep.department_id = d.department_id AND d.is_active = TRUE
            GROUP BY dep.department_id, dep.name
            ORDER BY avg_salary DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Self-join: find doctors in the same department as a given doctor.
     * Demonstrates: SELF JOIN.
     */
    public ResultSet getColleagues(Connection conn, int doctorId) throws SQLException {
        String sql = """
            SELECT d2.doctor_id,
                   CONCAT('Dr. ',d2.first_name,' ',d2.last_name) AS colleague_name,
                   d2.specialization
            FROM doctor d1
            JOIN doctor d2 ON d1.department_id = d2.department_id
                           AND d2.doctor_id != d1.doctor_id
                           AND d2.is_active = TRUE
            WHERE d1.doctor_id = ?
            ORDER BY d2.last_name
            """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, doctorId);
        return ps.executeQuery();
    }

    /**
     * Doctors with ABOVE AVERAGE salary.
     * Demonstrates: Subquery in WHERE.
     */
    public ResultSet getDoctorsAboveAvgSalary(Connection conn) throws SQLException {
        String sql = """
            SELECT d.doctor_id,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                   dep.name AS department,
                   d.salary
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            WHERE d.salary > (SELECT AVG(salary) FROM doctor WHERE is_active = TRUE)
            ORDER BY d.salary DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Active + inactive doctors via UNION.
     * Demonstrates: UNION ALL.
     */
    public ResultSet getAllDoctorsUnion(Connection conn) throws SQLException {
        String sql = """
            SELECT doctor_id, CONCAT(first_name,' ',last_name) AS name,
                   specialization, 'Active' AS doctor_status
            FROM doctor WHERE is_active = TRUE
            UNION ALL
            SELECT doctor_id, CONCAT(first_name,' ',last_name),
                   specialization, 'Inactive'
            FROM doctor WHERE is_active = FALSE
            ORDER BY doctor_status, name
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Doctor experience ranking using subquery.
     * Demonstrates: ORDER BY computed field, TIMESTAMPDIFF.
     */
    public ResultSet getDoctorsByExperience(Connection conn) throws SQLException {
        String sql = """
            SELECT doctor_id,
                   CONCAT('Dr. ', first_name,' ',last_name)  AS doctor_name,
                   specialization,
                   join_date,
                   TIMESTAMPDIFF(YEAR, join_date, CURDATE()) AS years_experience
            FROM doctor
            WHERE is_active = TRUE
            ORDER BY years_experience DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── PRIVATE HELPERS ───────────────────────────────────────────────────────

    private List<Doctor> mapList(ResultSet rs) throws SQLException {
        List<Doctor> list = new ArrayList<>();
        while (rs.next()) list.add(map(rs));
        return list;
    }

    private Doctor map(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setDoctorId      (rs.getInt      ("doctor_id"));
        d.setFirstName     (rs.getString   ("first_name"));
        d.setLastName      (rs.getString   ("last_name"));
        d.setSpecialization(rs.getString   ("specialization"));
        d.setDepartmentId  (rs.getInt      ("department_id"));
        d.setPhone         (rs.getString   ("phone"));
        d.setEmail         (rs.getString   ("email"));
        d.setSalary        (rs.getDouble   ("salary"));
        d.setJoinDate      (rs.getDate     ("join_date"));
        d.setActive        (rs.getBoolean  ("is_active"));
        d.setCreatedAt     (rs.getTimestamp("created_at"));
        d.setUpdatedAt     (rs.getTimestamp("updated_at"));
        try { d.setDepartmentName(rs.getString("department_name")); } catch (SQLException ignored) {}
        return d;
    }
}
