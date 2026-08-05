package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;

import java.sql.*;

/**
 * Reports DAO — pure SQL showcase.
 *
 * Every method demonstrates a different advanced SQL concept:
 *
 *   Window Functions  : ROW_NUMBER, RANK, DENSE_RANK, LEAD, LAG, PARTITION BY
 *   CTEs              : WITH clause (non-recursive and recursive)
 *   Recursive CTE     : Hierarchical category rollup
 *   Derived Tables    : Inline subquery as table alias
 *   EXISTS / NOT EXISTS: Correlated subqueries
 *   ANY / ALL         : Quantified comparisons
 *   UNION / UNION ALL : Combining result sets
 *   Temporary Tables  : CREATE TEMPORARY TABLE, populate, query
 *   String Functions  : CONCAT, UPPER, LOWER, LENGTH, SUBSTRING, TRIM
 *   Date Functions    : DATE_ADD, DATEDIFF, TIMESTAMPDIFF, MONTHNAME, DAYNAME
 *   Numeric Functions : ROUND, FLOOR, CEIL, ABS, MOD
 *   NULL Functions    : IFNULL, COALESCE, NULLIF, IS NULL
 *   CASE expression   : Simple and searched CASE
 *   Stored Procedures : Via CallableStatement
 *   Stored Functions  : Scalar function calls in SELECT
 */
public class ReportsDAO {

    // ── Window Functions ──────────────────────────────────────────────────────

    /**
     * Rank doctors by completed appointment count within each department.
     * Demonstrates: RANK() OVER (PARTITION BY ... ORDER BY ...).
     */
    public ResultSet getDoctorRankByDepartment(Connection conn) throws SQLException {
        String sql = """
            SELECT
                dep.name                                        AS department,
                CONCAT('Dr. ',d.first_name,' ',d.last_name)    AS doctor_name,
                COUNT(a.appointment_id)                         AS total_appts,
                SUM(a.status='Completed')                       AS completed,
                RANK() OVER (
                    PARTITION BY d.department_id
                    ORDER BY SUM(a.status='Completed') DESC
                )                                               AS dept_rank
            FROM doctor d
            JOIN department dep  ON d.department_id = dep.department_id
            LEFT JOIN appointment a ON d.doctor_id  = a.doctor_id
            WHERE d.is_active = TRUE
            GROUP BY d.doctor_id, d.first_name, d.last_name,
                     d.department_id, dep.name
            ORDER BY dep.name, dept_rank
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Dense rank patients by total billing (no gaps in rank).
     * Demonstrates: DENSE_RANK() OVER (ORDER BY ...).
     */
    public ResultSet getPatientBillingDenseRank(Connection conn) throws SQLException {
        String sql = """
            SELECT
                CONCAT(p.first_name,' ',p.last_name)    AS patient_name,
                COUNT(b.bill_id)                         AS bill_count,
                COALESCE(SUM(b.total_amount), 0)         AS total_billed,
                DENSE_RANK() OVER (ORDER BY COALESCE(SUM(b.total_amount),0) DESC) AS billing_rank
            FROM patient p
            LEFT JOIN billing b ON p.patient_id = b.patient_id AND b.status != 'Cancelled'
            GROUP BY p.patient_id, p.first_name, p.last_name
            ORDER BY billing_rank
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Row number for appointments partitioned by doctor.
     * Demonstrates: ROW_NUMBER() OVER (PARTITION BY ...).
     */
    public ResultSet getAppointmentRowNumber(Connection conn) throws SQLException {
        String sql = """
            SELECT
                CONCAT('Dr. ',d.first_name,' ',d.last_name)   AS doctor,
                CONCAT(p.first_name,' ',p.last_name)          AS patient,
                a.appointment_date,
                a.status,
                ROW_NUMBER() OVER (
                    PARTITION BY a.doctor_id ORDER BY a.appointment_date
                ) AS visit_sequence
            FROM appointment a
            JOIN doctor  d ON a.doctor_id  = d.doctor_id
            JOIN patient p ON a.patient_id = p.patient_id
            ORDER BY doctor, visit_sequence
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * LEAD / LAG: Show next and previous appointment dates for each patient.
     * Demonstrates: LEAD() and LAG() window functions.
     */
    public ResultSet getAppointmentLeadLag(Connection conn) throws SQLException {
        String sql = """
            SELECT
                CONCAT(p.first_name,' ',p.last_name)  AS patient_name,
                a.appointment_date                     AS current_date_appt,
                LAG (a.appointment_date) OVER (PARTITION BY a.patient_id ORDER BY a.appointment_date) AS prev_appointment,
                LEAD(a.appointment_date) OVER (PARTITION BY a.patient_id ORDER BY a.appointment_date) AS next_appointment,
                a.status
            FROM appointment a
            JOIN patient p ON a.patient_id = p.patient_id
            ORDER BY p.patient_id, a.appointment_date
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Running total of revenue using SUM() OVER (ORDER BY ...).
     * Demonstrates: Cumulative / running window aggregate.
     */
    public ResultSet getRunningRevenue(Connection conn) throws SQLException {
        String sql = """
            SELECT
                bill_date,
                total_amount,
                SUM(total_amount) OVER (ORDER BY bill_date ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW)
                    AS running_total,
                AVG(total_amount) OVER (ORDER BY bill_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW)
                    AS moving_avg_7
            FROM billing
            WHERE status != 'Cancelled'
            ORDER BY bill_date
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Common Table Expressions (CTE) ────────────────────────────────────────

    /**
     * CTE: Find patients with above-average visit count.
     * Demonstrates: WITH clause (non-recursive CTE).
     */
    public ResultSet getPatientsAboveAvgVisits(Connection conn) throws SQLException {
        String sql = """
            WITH patient_visit_counts AS (
                SELECT a.patient_id,
                       COUNT(v.visit_id) AS visit_count
                FROM   appointment a
                LEFT JOIN visit v ON v.appointment_id = a.appointment_id
                GROUP BY a.patient_id
            ),
            avg_visits AS (
                SELECT AVG(visit_count) AS avg_count FROM patient_visit_counts
            )
            SELECT
                CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                pvc.visit_count,
                ROUND(av.avg_count, 2)               AS avg_visits
            FROM patient_visit_counts pvc
            JOIN patient   p  ON pvc.patient_id = p.patient_id
            JOIN avg_visits av ON TRUE
            WHERE pvc.visit_count > av.avg_count
            ORDER BY pvc.visit_count DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Multi-step CTE: Monthly revenue with month-over-month growth.
     * Demonstrates: Multiple CTEs chained together.
     */
    public ResultSet getMonthlyRevenueGrowth(Connection conn) throws SQLException {
        String sql = """
            WITH monthly AS (
                SELECT
                    YEAR(bill_date)        AS yr,
                    MONTH(bill_date)       AS mo,
                    MONTHNAME(bill_date)   AS month_name,
                    SUM(paid_amount)       AS revenue
                FROM billing
                WHERE status != 'Cancelled'
                GROUP BY yr, mo, month_name
            ),
            with_growth AS (
                SELECT *,
                    LAG(revenue) OVER (ORDER BY yr, mo) AS prev_revenue
                FROM monthly
            )
            SELECT yr, month_name, revenue,
                   IFNULL(prev_revenue, 0)                              AS prev_revenue,
                   ROUND(
                       (revenue - IFNULL(prev_revenue,revenue))
                       / NULLIF(IFNULL(prev_revenue,revenue), 0) * 100
                   , 2)                                                  AS growth_pct
            FROM with_growth
            ORDER BY yr DESC, mo DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Recursive CTE: Generate a series of dates (1-31 days from today).
     * Demonstrates: Recursive CTE with UNION ALL.
     */
    public ResultSet getDateSeriesRecursiveCTE(Connection conn) throws SQLException {
        String sql = """
            WITH RECURSIVE date_series AS (
                SELECT CURDATE() AS dt, 1 AS day_num
                UNION ALL
                SELECT DATE_ADD(dt, INTERVAL 1 DAY), day_num + 1
                FROM date_series
                WHERE day_num < 30
            )
            SELECT dt AS calendar_date,
                   DAYNAME(dt)   AS day_of_week,
                   MONTHNAME(dt) AS month_name,
                   DAY(dt)       AS day_num
            FROM date_series
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Derived Tables ────────────────────────────────────────────────────────

    /**
     * Use a derived (inline) table to find departments with multiple top doctors.
     * Demonstrates: Subquery as table in FROM clause.
     */
    public ResultSet getTopDepartments(Connection conn) throws SQLException {
        String sql = """
            SELECT dep.name AS department,
                   doc_stats.avg_completed,
                   doc_stats.total_doctors
            FROM department dep
            JOIN (
                SELECT d.department_id,
                       COUNT(d.doctor_id)                  AS total_doctors,
                       AVG(IFNULL(appt_count.completed,0)) AS avg_completed
                FROM doctor d
                LEFT JOIN (
                    SELECT doctor_id, SUM(status='Completed') AS completed
                    FROM appointment
                    GROUP BY doctor_id
                ) appt_count ON d.doctor_id = appt_count.doctor_id
                GROUP BY d.department_id
            ) doc_stats ON dep.department_id = doc_stats.department_id
            WHERE doc_stats.total_doctors >= 1
            ORDER BY doc_stats.avg_completed DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── EXISTS / NOT EXISTS ───────────────────────────────────────────────────

    /**
     * Patients who HAVE at least one paid bill.
     * Demonstrates: EXISTS correlated subquery.
     */
    public ResultSet getPatientsWithPaidBills(Connection conn) throws SQLException {
        String sql = """
            SELECT p.patient_id, CONCAT(p.first_name,' ',p.last_name) AS patient_name,
                   p.phone
            FROM patient p
            WHERE EXISTS (
                SELECT 1 FROM billing b
                WHERE b.patient_id = p.patient_id AND b.status = 'Paid'
            )
            ORDER BY p.last_name
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Doctors who have NEVER received a rating below 3.
     * Demonstrates: NOT EXISTS correlated subquery.
     */
    public ResultSet getDoctorsWithNoLowRating(Connection conn) throws SQLException {
        String sql = """
            SELECT d.doctor_id,
                   CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                   d.specialization
            FROM doctor d
            WHERE d.is_active = TRUE
            AND NOT EXISTS (
                SELECT 1 FROM feedback f
                WHERE f.doctor_id = d.doctor_id AND f.rating < 3
            )
            ORDER BY d.last_name
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── ANY / ALL ─────────────────────────────────────────────────────────────

    /**
     * Patients whose billing exceeds ANY single payment amount.
     * Demonstrates: ANY operator.
     */
    public ResultSet getBillingGreaterThanAnyPayment(Connection conn) throws SQLException {
        String sql = """
            SELECT b.bill_id,
                   CONCAT(p.first_name,' ',p.last_name) AS patient,
                   b.total_amount,
                   b.status
            FROM billing b
            JOIN patient p ON b.patient_id = p.patient_id
            WHERE b.total_amount > ANY (SELECT amount FROM payment)
            ORDER BY b.total_amount DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Doctors whose salary exceeds ALL doctors in Pediatrics.
     * Demonstrates: ALL operator.
     */
    public ResultSet getDoctorsWithSalaryAboveAllPediatrics(Connection conn) throws SQLException {
        String sql = """
            SELECT CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor_name,
                   dep.name AS department, d.salary
            FROM doctor d
            JOIN department dep ON d.department_id = dep.department_id
            WHERE d.salary > ALL (
                SELECT d2.salary FROM doctor d2
                JOIN department dep2 ON d2.department_id = dep2.department_id
                WHERE dep2.name = 'Pediatrics'
            )
            ORDER BY d.salary DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── UNION ─────────────────────────────────────────────────────────────────

    /**
     * All healthcare providers (doctors + staff) using UNION.
     * Demonstrates: UNION — de-duplicates. UNION ALL keeps all.
     */
    public ResultSet getAllProviders(Connection conn) throws SQLException {
        String sql = """
            SELECT 'Doctor' AS type, doctor_id AS id,
                   CONCAT(first_name,' ',last_name) AS name,
                   specialization AS role
            FROM doctor WHERE is_active = TRUE
            UNION ALL
            SELECT 'Staff', staff_id,
                   CONCAT(first_name,' ',last_name),
                   role
            FROM staff WHERE is_active = TRUE
            ORDER BY type, name
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Temporary Tables ──────────────────────────────────────────────────────

    /**
     * Create a temporary table, populate it with top patients, query it.
     * Demonstrates: CREATE TEMPORARY TABLE, INSERT INTO … SELECT, DROP TEMP TABLE.
     */
    public ResultSet getTopPatientsViaTemp(Connection conn) throws SQLException {
        // Create temp table
        conn.prepareStatement("""
            CREATE TEMPORARY TABLE IF NOT EXISTS tmp_top_patients (
                patient_id   INT,
                patient_name VARCHAR(100),
                visit_count  INT,
                total_billed DECIMAL(10,2)
            )
            """).execute();

        // Populate
        conn.prepareStatement("""
            INSERT INTO tmp_top_patients
            SELECT a.patient_id,
                   CONCAT(p.first_name,' ',p.last_name),
                   COUNT(DISTINCT v.visit_id),
                   COALESCE(SUM(b.total_amount),0)
            FROM patient p
            LEFT JOIN appointment a ON p.patient_id = a.patient_id
            LEFT JOIN visit v       ON a.appointment_id = v.appointment_id
            LEFT JOIN billing b     ON p.patient_id = b.patient_id
            GROUP BY a.patient_id, p.first_name, p.last_name
            ORDER BY visit_count DESC LIMIT 10
            """).execute();

        // Query temp table
        ResultSet rs = conn.prepareStatement(
            "SELECT * FROM tmp_top_patients ORDER BY visit_count DESC"
        ).executeQuery();

        // Drop temp table (caller is responsible for closing the connection)
        return rs;
    }

    // ── String Functions ──────────────────────────────────────────────────────

    /**
     * Patient info with string-function manipulations.
     * Demonstrates: CONCAT, UPPER, LOWER, LENGTH, SUBSTRING, TRIM, REPLACE.
     */
    public ResultSet getPatientStringFunctions(Connection conn) throws SQLException {
        String sql = """
            SELECT
                patient_id,
                CONCAT(UPPER(LEFT(first_name,1)), LOWER(SUBSTRING(first_name,2))) AS formatted_first,
                CONCAT(UPPER(LEFT(last_name,1)),  LOWER(SUBSTRING(last_name,2)))  AS formatted_last,
                LENGTH(CONCAT(first_name,' ',last_name))  AS name_length,
                UPPER(gender)                             AS gender_upper,
                SUBSTRING(phone, 1, 5)                    AS phone_prefix,
                IFNULL(email, 'No email provided')        AS email_display
            FROM patient WHERE is_active = TRUE LIMIT 20
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Date Functions ────────────────────────────────────────────────────────

    /**
     * Comprehensive date function showcase.
     * Demonstrates: YEAR, MONTH, DAY, DAYNAME, MONTHNAME, DATEDIFF, DATE_ADD,
     *               TIMESTAMPDIFF, CURDATE, NOW, DATE_FORMAT.
     */
    public ResultSet getPatientDateFunctions(Connection conn) throws SQLException {
        String sql = """
            SELECT
                patient_id,
                CONCAT(first_name,' ',last_name)           AS patient_name,
                date_of_birth,
                YEAR(date_of_birth)                        AS birth_year,
                MONTHNAME(date_of_birth)                   AS birth_month,
                DAYNAME(date_of_birth)                     AS birth_day,
                TIMESTAMPDIFF(YEAR, date_of_birth, NOW())  AS age_years,
                DATEDIFF(NOW(), date_of_birth)             AS age_in_days,
                DATE_ADD(date_of_birth, INTERVAL 18 YEAR)  AS turned_18_on,
                DATE_FORMAT(registered_at,'%d %M %Y')      AS joined_formatted
            FROM patient WHERE is_active=TRUE LIMIT 20
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Numeric Functions ─────────────────────────────────────────────────────

    /**
     * Billing with numeric function showcase.
     * Demonstrates: ROUND, FLOOR, CEIL, ABS, MOD, TRUNCATE.
     */
    public ResultSet getBillingNumericFunctions(Connection conn) throws SQLException {
        String sql = """
            SELECT
                bill_id,
                total_amount,
                ROUND(total_amount, 0)            AS rounded,
                FLOOR(total_amount)               AS floor_val,
                CEIL(total_amount)                AS ceil_val,
                ABS(total_amount - paid_amount)   AS abs_outstanding,
                MOD(FLOOR(total_amount), 100)     AS mod_100,
                TRUNCATE(total_amount, 0)         AS truncated,
                ROUND(paid_amount/NULLIF(total_amount,0)*100, 2) AS pct_paid
            FROM billing WHERE status!='Cancelled' LIMIT 20
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── NULL Functions ────────────────────────────────────────────────────────

    /**
     * IFNULL, COALESCE, NULLIF demonstration.
     */
    public ResultSet getNullFunctionDemo(Connection conn) throws SQLException {
        String sql = """
            SELECT
                p.patient_id,
                CONCAT(p.first_name,' ',p.last_name)               AS patient,
                IFNULL(p.email, 'NO EMAIL')                        AS email_ifnull,
                COALESCE(p.blood_group, 'Unknown')                 AS blood_group_coalesce,
                NULLIF(p.city, '')                                  AS city_nullif,
                COALESCE(b.total_amount, 0)                        AS bill_or_zero,
                IF(b.bill_id IS NULL, 'No Bill', 'Has Bill')       AS bill_status
            FROM patient p
            LEFT JOIN billing b ON p.patient_id = b.patient_id
            LIMIT 20
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── CASE Expression ───────────────────────────────────────────────────────

    /**
     * Comprehensive CASE expression demo (simple + searched).
     */
    public ResultSet getCaseExpressionDemo(Connection conn) throws SQLException {
        String sql = """
            SELECT
                b.bill_id,
                CONCAT(p.first_name,' ',p.last_name) AS patient,
                b.total_amount,
                b.status,
                -- Searched CASE
                CASE
                    WHEN b.total_amount < 1000  THEN 'Low'
                    WHEN b.total_amount < 5000  THEN 'Medium'
                    WHEN b.total_amount < 10000 THEN 'High'
                    ELSE 'Very High'
                END AS bill_category,
                -- Simple CASE
                CASE b.status
                    WHEN 'Paid'      THEN '✔ Paid'
                    WHEN 'Pending'   THEN '⏳ Pending'
                    WHEN 'Partial'   THEN '◑ Partial'
                    WHEN 'Cancelled' THEN '✘ Cancelled'
                    ELSE b.status
                END AS status_icon
            FROM billing b
            JOIN patient p ON b.patient_id = p.patient_id
            ORDER BY b.total_amount DESC LIMIT 20
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Stored Function calls ─────────────────────────────────────────────────

    /**
     * Call multiple stored functions in one SELECT.
     */
    public ResultSet getStoredFunctionDemo(Connection conn) throws SQLException {
        String sql = """
            SELECT
                p.patient_id,
                CONCAT(p.first_name,' ',p.last_name)    AS patient_name,
                GetPatientAge(p.patient_id)             AS age,
                GetAppointmentCount(p.patient_id)       AS appointment_count,
                GetPatientVisitCount(p.patient_id)      AS visit_count,
                GetTotalPaid(p.patient_id)              AS total_paid,
                GetOutstandingBalance(p.patient_id)     AS outstanding
            FROM patient p
            WHERE p.is_active = TRUE
            ORDER BY outstanding DESC
            LIMIT 10
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    // ── Stored Procedure calls ────────────────────────────────────────────────

    /** Calls TopDoctorsReport procedure. */
    public ResultSet callTopDoctorsReport(Connection conn, int limit) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL TopDoctorsReport(?)}");
        cs.setInt(1, limit);
        return cs.executeQuery();
    }

    /** Calls MonthlyRevenueReport procedure. */
    public ResultSet callMonthlyRevenueReport(Connection conn, int year) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL MonthlyRevenueReport(?)}");
        cs.setInt(1, year);
        return cs.executeQuery();
    }

    /** Calls UpcomingAppointments procedure. */
    public ResultSet callUpcomingAppointments(Connection conn, int days) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL UpcomingAppointments(?)}");
        cs.setInt(1, days);
        return cs.executeQuery();
    }

    /** GetPatientOutstanding — IN/OUT/OUT parameter demo. */
    public void callGetPatientOutstanding(Connection conn, int patientId) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL GetPatientOutstanding(?,?,?)}");
        cs.setInt(1, patientId);
        cs.registerOutParameter(2, Types.DECIMAL);
        cs.registerOutParameter(3, Types.INTEGER);
        cs.execute();
        double balance   = cs.getDouble(2);
        int    billCount = cs.getInt   (3);
        System.out.printf("  Outstanding Balance : ₹%.2f%n", balance);
        System.out.printf("  Bill Count          : %d%n", billCount);
    }

    // ── Subquery report ───────────────────────────────────────────────────────

    /**
     * Most common disease across all patients.
     * Demonstrates: Subquery + GROUP BY + HAVING + ORDER BY + LIMIT.
     */
    public ResultSet getMostCommonDiseases(Connection conn) throws SQLException {
        String sql = """
            SELECT d.name AS disease, d.category,
                   COUNT(pd.patient_id) AS patient_count
            FROM disease d
            JOIN patient_disease pd ON d.disease_id = pd.disease_id
            GROUP BY d.disease_id, d.name, d.category
            ORDER BY patient_count DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /**
     * Pagination demo — page N of size P, with total count.
     * Demonstrates: LIMIT + OFFSET.
     */
    public ResultSet getPaginatedPatients(Connection conn, int page, int size) throws SQLException {
        String sql = "SELECT *, COUNT(*) OVER() AS total_count FROM patient WHERE is_active=TRUE ORDER BY last_name LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,size); ps.setInt(2,(page-1)*size);
        return ps.executeQuery();
    }

    /**
     * Doctor performance using GROUP BY + HAVING.
     * Demonstrates: HAVING clause with aggregate.
     */
    public ResultSet getDoctorsWithHighCompletion(Connection conn, int minPct) throws SQLException {
        String sql = """
            SELECT
                CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor,
                COUNT(a.appointment_id)                      AS total,
                SUM(a.status='Completed')                    AS completed,
                ROUND(SUM(a.status='Completed')*100.0/COUNT(*),2) AS completion_pct
            FROM doctor d
            JOIN appointment a ON d.doctor_id = a.doctor_id
            GROUP BY d.doctor_id, d.first_name, d.last_name
            HAVING completion_pct >= ?
            ORDER BY completion_pct DESC
            """;
        PreparedStatement ps = conn.prepareStatement(sql); ps.setInt(1,minPct);
        return ps.executeQuery();
    }
}
