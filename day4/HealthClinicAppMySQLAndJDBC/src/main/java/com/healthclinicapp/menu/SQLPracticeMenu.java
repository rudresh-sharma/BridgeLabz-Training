package com.healthclinicapp.menu;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.util.*;

import java.sql.*;

/**
 * SQL Practice Menu — an interactive SQL concept explorer.
 * Each option executes a hand-crafted query that demonstrates one core concept,
 * prints the result, then prints a brief explanation.
 */
public class SQLPracticeMenu {

    public void show() {
        while (true) {
            PrintUtil.subHeader("SQL PRACTICE MENU");
            System.out.println(ColorUtil.CYAN +
                "  ── DDL (Data Definition Language) ──\n" +
                "  1.  SHOW all tables in database\n" +
                "  2.  DESCRIBE a table structure\n" +
                "  3.  Show table CREATE statement\n" +
                "  ── DML (Data Manipulation Language) ──\n" +
                "  4.  INSERT with SELECT (INSERT INTO ... SELECT)\n" +
                "  5.  UPDATE with JOIN\n" +
                "  6.  DELETE with subquery\n" +
                "  ── SELECT Clauses ──\n" +
                "  7.  WHERE with multiple conditions (AND/OR/NOT)\n" +
                "  8.  ORDER BY multiple columns\n" +
                "  9.  LIMIT and OFFSET (Pagination)\n" +
                "  10. DISTINCT values\n" +
                "  ── JOINs ──\n" +
                "  11. INNER JOIN (Appointments with Patient+Doctor)\n" +
                "  12. LEFT JOIN  (All patients, with or without bills)\n" +
                "  13. RIGHT JOIN (All doctors, with or without appointments)\n" +
                "  14. SELF JOIN  (Doctors in same department)\n" +
                "  15. Multi-table JOIN (5 tables)\n" +
                "  ── Aggregate Functions ──\n" +
                "  16. COUNT, SUM, AVG, MIN, MAX — billing stats\n" +
                "  17. GROUP BY department\n" +
                "  18. HAVING clause\n" +
                "  19. ROLLUP (subtotals per department)\n" +
                "  ── Subqueries ──\n" +
                "  20. Subquery in WHERE\n" +
                "  21. Correlated subquery\n" +
                "  22. Subquery in SELECT (scalar)\n" +
                "  23. Subquery in FROM (derived table)\n" +
                "  ── Set Operations ──\n" +
                "  24. UNION — combine patient and doctor names\n" +
                "  25. INTERSECT (emulated)\n" +
                "  ── String / Date / Numeric ──\n" +
                "  26. String functions (CONCAT, UPPER, LENGTH, SUBSTRING)\n" +
                "  27. Date functions (CURDATE, DATEDIFF, DATE_FORMAT)\n" +
                "  28. Numeric functions (ROUND, FLOOR, CEIL, MOD)\n" +
                "  ── Advanced ──\n" +
                "  29. CASE expression\n" +
                "  30. Window Functions (ROW_NUMBER, RANK, SUM OVER)\n" +
                "  31. CTE (WITH clause)\n" +
                "  32. Recursive CTE\n" +
                "  33. Stored function call\n" +
                "  34. Stored procedure call\n" +
                "  35. Execute custom SQL\n" +
                "  0.  Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 35);
            switch (choice) {
                case 1  -> showTables();
                case 2  -> describeTable();
                case 3  -> showCreate();
                case 4  -> insertSelect();
                case 5  -> updateJoin();
                case 6  -> deleteSubquery();
                case 7  -> whereDemo();
                case 8  -> orderBy();
                case 9  -> limitOffset();
                case 10 -> distinct();
                case 11 -> innerJoin();
                case 12 -> leftJoin();
                case 13 -> rightJoin();
                case 14 -> selfJoin();
                case 15 -> multiJoin();
                case 16 -> aggregates();
                case 17 -> groupBy();
                case 18 -> having();
                case 19 -> rollup();
                case 20 -> subqueryWhere();
                case 21 -> correlatedSubquery();
                case 22 -> scalarSubquery();
                case 23 -> derivedTable();
                case 24 -> union();
                case 25 -> intersect();
                case 26 -> stringFunctions();
                case 27 -> dateFunctions();
                case 28 -> numericFunctions();
                case 29 -> caseExpr();
                case 30 -> windowFunctions();
                case 31 -> cte();
                case 32 -> recursiveCte();
                case 33 -> storedFunction();
                case 34 -> storedProcedure();
                case 35 -> customSql();
                case 0  -> { return; }
            }
        }
    }

    // ── DDL ──────────────────────────────────────────────────────────────────

    private void showTables() {
        runQuery("SHOW TABLES",
            "SHOW TABLES lists all tables in the current database.",
            "SHOW TABLES");
    }

    private void describeTable() {
        String table = InputUtil.readString("  Table name: ").trim();
        runQuery("DESCRIBE " + table,
            "DESCRIBE (or DESC) shows the column structure: name, type, null?, key, default.",
            "DESCRIBE " + table);
    }

    private void showCreate() {
        String table = InputUtil.readString("  Table name: ").trim();
        runQuery("SHOW CREATE TABLE " + table,
            "SHOW CREATE TABLE displays the full DDL that created the table.",
            "SHOW CREATE TABLE " + table);
    }

    // ── DML ──────────────────────────────────────────────────────────────────

    private void insertSelect() {
        String sql = """
            INSERT INTO activity_log(activity_type, description)
            SELECT 'SQL_PRACTICE', CONCAT('INSERT INTO ... SELECT demo at ', NOW())
            FROM DUAL
            """;
        exec("INSERT INTO ... SELECT",
            "INSERT INTO ... SELECT inserts rows derived from a SELECT result — no VALUES clause needed.",
            sql);
    }

    private void updateJoin() {
        String sql = """
            UPDATE medicine m
            JOIN prescription_item pi ON m.medicine_id = pi.medicine_id
            SET m.stock_quantity = m.stock_quantity  -- no-op demo
            WHERE pi.quantity > 0
            """;
        exec("UPDATE with JOIN",
            "UPDATE ... JOIN lets you update one table based on conditions from another table.",
            sql);
    }

    private void deleteSubquery() {
        String sql = """
            DELETE FROM activity_log
            WHERE activity_type = 'SQL_PRACTICE'
            AND created_at < DATE_SUB(NOW(), INTERVAL 1 SECOND)
            """;
        exec("DELETE with subquery / date condition",
            "DELETE with a WHERE clause using date functions to clean only practice log entries.",
            sql);
    }

    // ── SELECT ───────────────────────────────────────────────────────────────

    private void whereDemo() {
        runQuery("WHERE with AND/OR/NOT",
            "WHERE filters rows. AND/OR combine conditions. NOT negates them.",
            "SELECT patient_id, CONCAT(first_name,' ',last_name) AS name, gender, city FROM patient WHERE (gender='Male' OR gender='Female') AND is_active=TRUE AND NOT city IS NULL ORDER BY city LIMIT 10");
    }

    private void orderBy() {
        runQuery("ORDER BY multiple columns",
            "ORDER BY sorts results. Multiple columns create a composite sort. DESC reverses order.",
            "SELECT doctor_id, CONCAT(first_name,' ',last_name) AS doctor_name, department_id, salary FROM doctor ORDER BY department_id ASC, salary DESC LIMIT 10");
    }

    private void limitOffset() {
        int page = InputUtil.readInt("  Page number: ", 1, 100);
        int size = InputUtil.readInt("  Page size: ", 5, 50);
        int offset = (page - 1) * size;
        runQuery("LIMIT / OFFSET (Pagination)",
            "LIMIT restricts rows returned. OFFSET skips N rows. Together they implement pagination.",
            "SELECT patient_id, CONCAT(first_name,' ',last_name) AS name FROM patient ORDER BY patient_id LIMIT " + size + " OFFSET " + offset);
    }

    private void distinct() {
        runQuery("DISTINCT",
            "DISTINCT removes duplicate rows from the result set.",
            "SELECT DISTINCT city, gender FROM patient WHERE is_active=TRUE ORDER BY city");
    }

    // ── JOINs ────────────────────────────────────────────────────────────────

    private void innerJoin() {
        runQuery("INNER JOIN",
            "INNER JOIN returns only rows that match in BOTH tables. Non-matching rows are excluded.",
            "SELECT a.appointment_id, CONCAT(p.first_name,' ',p.last_name) AS patient, CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor, a.appointment_date, a.status FROM appointment a INNER JOIN patient p ON a.patient_id=p.patient_id INNER JOIN doctor d ON a.doctor_id=d.doctor_id ORDER BY a.appointment_date DESC LIMIT 10");
    }

    private void leftJoin() {
        runQuery("LEFT JOIN",
            "LEFT JOIN returns ALL rows from the LEFT table + matching rows from the RIGHT. Non-matches produce NULL on the right.",
            "SELECT p.patient_id, CONCAT(p.first_name,' ',p.last_name) AS patient_name, COUNT(b.bill_id) AS total_bills, COALESCE(SUM(b.total_amount),0) AS total_billed FROM patient p LEFT JOIN billing b ON p.patient_id=b.patient_id GROUP BY p.patient_id, p.first_name, p.last_name ORDER BY total_bills DESC LIMIT 10");
    }

    private void rightJoin() {
        runQuery("RIGHT JOIN",
            "RIGHT JOIN returns ALL rows from the RIGHT table + matching rows from the LEFT. Non-matches produce NULL on the left.",
            "SELECT CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor, COUNT(a.appointment_id) AS appointments FROM appointment a RIGHT JOIN doctor d ON a.doctor_id=d.doctor_id WHERE d.is_active=TRUE GROUP BY d.doctor_id, d.first_name, d.last_name ORDER BY appointments DESC LIMIT 10");
    }

    private void selfJoin() {
        runQuery("SELF JOIN",
            "A SELF JOIN joins a table to itself. Used here to find doctors in the same department.",
            "SELECT d1.doctor_id, CONCAT('Dr. ',d1.first_name,' ',d1.last_name) AS doctor, CONCAT('Dr. ',d2.first_name,' ',d2.last_name) AS colleague FROM doctor d1 JOIN doctor d2 ON d1.department_id=d2.department_id AND d1.doctor_id != d2.doctor_id AND d2.is_active=TRUE ORDER BY d1.doctor_id LIMIT 10");
    }

    private void multiJoin() {
        runQuery("Multi-table JOIN (5 tables)",
            "Multiple JOINs chain together to fetch data spread across many tables in one query.",
            "SELECT pr.prescription_id, CONCAT(p.first_name,' ',p.last_name) AS patient, CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor, m.name AS medicine, pi.dosage, pi.quantity FROM prescription pr JOIN visit v ON pr.visit_id=v.visit_id JOIN appointment a ON v.appointment_id=a.appointment_id JOIN patient p ON a.patient_id=p.patient_id JOIN doctor d ON a.doctor_id=d.doctor_id JOIN prescription_item pi ON pr.prescription_id=pi.prescription_id JOIN medicine m ON pi.medicine_id=m.medicine_id LIMIT 10");
    }

    // ── Aggregates ────────────────────────────────────────────────────────────

    private void aggregates() {
        runQuery("Aggregate Functions (COUNT, SUM, AVG, MIN, MAX)",
            "Aggregate functions operate on a SET of rows. COUNT(*) counts all rows; SUM/AVG/MIN/MAX work on numeric columns.",
            "SELECT COUNT(*) AS total_bills, SUM(total_amount) AS total_billed, ROUND(AVG(total_amount),2) AS avg_bill, MIN(total_amount) AS min_bill, MAX(total_amount) AS max_bill FROM billing WHERE status!='Cancelled'");
    }

    private void groupBy() {
        runQuery("GROUP BY",
            "GROUP BY divides rows into groups and applies aggregate functions to each group.",
            "SELECT dep.name AS department, COUNT(DISTINCT d.doctor_id) AS doctors, COUNT(DISTINCT a.appointment_id) AS appointments, COALESCE(SUM(b.total_amount),0) AS revenue FROM department dep LEFT JOIN doctor d ON dep.department_id=d.department_id LEFT JOIN appointment a ON d.doctor_id=a.doctor_id LEFT JOIN billing b ON a.patient_id=b.patient_id GROUP BY dep.department_id, dep.name ORDER BY revenue DESC");
    }

    private void having() {
        runQuery("HAVING clause",
            "HAVING filters GROUPS (like WHERE filters rows). You can only use aggregate functions in HAVING.",
            "SELECT d.doctor_id, CONCAT('Dr. ',d.first_name,' ',d.last_name) AS doctor, COUNT(a.appointment_id) AS total, SUM(a.status='Completed') AS completed, ROUND(SUM(a.status='Completed')*100.0/COUNT(*),2) AS pct FROM doctor d JOIN appointment a ON d.doctor_id=a.doctor_id GROUP BY d.doctor_id, d.first_name, d.last_name HAVING pct >= 50 ORDER BY pct DESC");
    }

    private void rollup() {
        runQuery("GROUP BY ... WITH ROLLUP (subtotals)",
            "WITH ROLLUP adds extra summary rows: subtotals per group and a grand total at the end.",
            "SELECT COALESCE(dep.name,'=== GRAND TOTAL ===') AS department, COALESCE(d.specialization,'ALL') AS specialization, COUNT(doc.doctor_id) AS num_doctors FROM department dep LEFT JOIN doctor doc ON dep.department_id=doc.department_id LEFT JOIN doctor d ON doc.doctor_id=d.doctor_id GROUP BY dep.name, d.specialization WITH ROLLUP LIMIT 20");
    }

    // ── Subqueries ────────────────────────────────────────────────────────────

    private void subqueryWhere() {
        runQuery("Subquery in WHERE",
            "A subquery in WHERE is evaluated first and its result is used to filter the outer query.",
            "SELECT doctor_id, CONCAT(first_name,' ',last_name) AS doctor_name, salary FROM doctor WHERE salary > (SELECT AVG(salary) FROM doctor WHERE is_active=TRUE) ORDER BY salary DESC");
    }

    private void correlatedSubquery() {
        runQuery("Correlated Subquery",
            "A correlated subquery references a column from the outer query. It runs once per outer row.",
            "SELECT p.patient_id, CONCAT(p.first_name,' ',p.last_name) AS patient_name, (SELECT COUNT(*) FROM appointment a WHERE a.patient_id=p.patient_id) AS appt_count, (SELECT MAX(appointment_date) FROM appointment a WHERE a.patient_id=p.patient_id) AS last_appointment FROM patient p WHERE p.is_active=TRUE ORDER BY appt_count DESC LIMIT 10");
    }

    private void scalarSubquery() {
        runQuery("Scalar Subquery in SELECT",
            "A scalar subquery returns exactly one value and can appear anywhere a column reference can.",
            "SELECT department_id, name, (SELECT COUNT(*) FROM doctor d WHERE d.department_id=dep.department_id AND d.is_active=TRUE) AS doctor_count, (SELECT CONCAT(first_name,' ',last_name) FROM doctor d WHERE d.doctor_id=dep.head_doctor_id) AS head_doctor FROM department dep ORDER BY doctor_count DESC");
    }

    private void derivedTable() {
        runQuery("Derived Table (Subquery in FROM)",
            "A derived table is an inline subquery used as a virtual table in the FROM clause.",
            "SELECT dept_stats.department, dept_stats.avg_salary, d.first_name, d.last_name, d.salary FROM doctor d JOIN (SELECT department_id, AVG(salary) AS avg_salary FROM doctor WHERE is_active=TRUE GROUP BY department_id) dept_stats ON d.department_id=dept_stats.department_id JOIN department dep ON d.department_id=dep.department_id WHERE d.salary > dept_stats.avg_salary ORDER BY dep.name");
    }

    // ── Set Operations ────────────────────────────────────────────────────────

    private void union() {
        runQuery("UNION ALL",
            "UNION ALL combines result sets from multiple SELECT statements. UNION removes duplicates; UNION ALL keeps all rows.",
            "SELECT 'Patient' AS type, CONCAT(first_name,' ',last_name) AS name, phone FROM patient WHERE is_active=TRUE UNION ALL SELECT 'Doctor', CONCAT('Dr. ',first_name,' ',last_name), phone FROM doctor WHERE is_active=TRUE ORDER BY type, name LIMIT 15");
    }

    private void intersect() {
        runQuery("INTERSECT (emulated with EXISTS)",
            "MySQL lacks INTERSECT. It can be emulated using INNER JOIN or EXISTS to find values common to both queries.",
            "SELECT DISTINCT p.city FROM patient p WHERE EXISTS (SELECT 1 FROM doctor d WHERE d.department_id IN (SELECT department_id FROM department WHERE name LIKE '%Cardio%')) AND p.is_active=TRUE ORDER BY p.city");
    }

    // ── String / Date / Numeric ───────────────────────────────────────────────

    private void stringFunctions() {
        runQuery("String Functions",
            "MySQL string functions: CONCAT joins strings; UPPER/LOWER change case; LENGTH returns character count; SUBSTRING extracts part; TRIM removes whitespace.",
            "SELECT patient_id, CONCAT(UPPER(LEFT(first_name,1)),LOWER(SUBSTRING(first_name,2))) AS first_name_proper, LENGTH(CONCAT(first_name,' ',last_name)) AS name_length, UPPER(gender) AS gender, SUBSTRING(phone,1,5) AS phone_prefix, IFNULL(email,'N/A') AS email FROM patient WHERE is_active=TRUE LIMIT 10");
    }

    private void dateFunctions() {
        runQuery("Date Functions",
            "Date functions: CURDATE() = today; DATEDIFF = days between; TIMESTAMPDIFF = difference in a unit; DATE_FORMAT = formatting; MONTHNAME/DAYNAME = text labels.",
            "SELECT patient_id, CONCAT(first_name,' ',last_name) AS name, date_of_birth, TIMESTAMPDIFF(YEAR,date_of_birth,CURDATE()) AS age, DATEDIFF(CURDATE(),date_of_birth) AS days_lived, MONTHNAME(date_of_birth) AS birth_month, DAYNAME(date_of_birth) AS birth_day, DATE_FORMAT(registered_at,'%d %b %Y') AS joined FROM patient WHERE is_active=TRUE LIMIT 10");
    }

    private void numericFunctions() {
        runQuery("Numeric Functions",
            "ROUND rounds to N decimals; FLOOR rounds down; CEIL rounds up; ABS removes sign; MOD gives remainder; TRUNCATE drops decimals without rounding.",
            "SELECT bill_id, total_amount, ROUND(total_amount,0) AS rounded, FLOOR(total_amount) AS floored, CEIL(total_amount) AS ceiled, MOD(FLOOR(total_amount),100) AS mod100, ABS(paid_amount-total_amount) AS abs_diff, TRUNCATE(total_amount,0) AS truncated FROM billing WHERE status!='Cancelled' LIMIT 10");
    }

    // ── Advanced ──────────────────────────────────────────────────────────────

    private void caseExpr() {
        runQuery("CASE Expression",
            "CASE is SQL's conditional logic. Simple CASE compares one expression; Searched CASE evaluates Boolean conditions.",
            "SELECT bill_id, total_amount, CASE WHEN total_amount<1000 THEN 'Low' WHEN total_amount<5000 THEN 'Medium' ELSE 'High' END AS category, CASE status WHEN 'Paid' THEN '✔ Paid' WHEN 'Pending' THEN '⏳ Pending' WHEN 'Partial' THEN '◑ Partial' ELSE status END AS status_label FROM billing LIMIT 10");
    }

    private void windowFunctions() {
        runQuery("Window Functions",
            "Window functions compute values ACROSS rows related to the current row. PARTITION BY groups; ORDER BY defines sequence. No GROUP BY needed — original rows are kept.",
            "SELECT CONCAT(p.first_name,' ',p.last_name) AS patient, b.bill_date, b.total_amount, ROW_NUMBER() OVER (PARTITION BY b.patient_id ORDER BY b.bill_date) AS bill_seq, SUM(b.total_amount) OVER (PARTITION BY b.patient_id ORDER BY b.bill_date) AS running_total, RANK() OVER (ORDER BY b.total_amount DESC) AS amount_rank FROM billing b JOIN patient p ON b.patient_id=p.patient_id WHERE b.status!='Cancelled' LIMIT 15");
    }

    private void cte() {
        runQuery("CTE (WITH clause)",
            "A CTE (Common Table Expression) names a subquery so you can reference it multiple times. Makes complex queries readable.",
            "WITH monthly_revenue AS (SELECT YEAR(bill_date) AS yr, MONTH(bill_date) AS mo, MONTHNAME(bill_date) AS mname, SUM(paid_amount) AS revenue FROM billing WHERE status='Paid' GROUP BY yr, mo, mname) SELECT yr, mname, revenue, SUM(revenue) OVER (ORDER BY yr,mo) AS cumulative FROM monthly_revenue ORDER BY yr,mo");
    }

    private void recursiveCte() {
        runQuery("Recursive CTE",
            "Recursive CTEs have a base case (anchor) and a recursive step joined with UNION ALL. Useful for hierarchical data, series generation, and graph traversal.",
            "WITH RECURSIVE num_series AS (SELECT 1 AS n UNION ALL SELECT n+1 FROM num_series WHERE n < 10) SELECT n, n*n AS square, n*n*n AS cube, IF(n%2=0,'Even','Odd') AS parity FROM num_series");
    }

    private void storedFunction() {
        runQuery("Stored Function calls",
            "Stored functions encapsulate reusable SQL logic and return a single value. Called inline in SELECT.",
            "SELECT p.patient_id, CONCAT(p.first_name,' ',p.last_name) AS patient, GetPatientAge(p.patient_id) AS age, GetAppointmentCount(p.patient_id) AS appointments, GetTotalPaid(p.patient_id) AS total_paid, GetOutstandingBalance(p.patient_id) AS outstanding FROM patient p WHERE p.is_active=TRUE ORDER BY outstanding DESC LIMIT 10");
    }

    private void storedProcedure() {
        int days = InputUtil.readInt("  Show appointments in next N days: ", 1, 90);
        runQuery("Stored Procedure call (UpcomingAppointments)",
            "Stored procedures are pre-compiled SQL programs called with CALL. They can have IN/OUT parameters, use cursors, and contain control flow.",
            "CALL UpcomingAppointments(" + days + ")");
    }

    private void customSql() {
        System.out.println(ColorUtil.BOLD_YELLOW + "\n  ── Execute Custom SQL ──" + ColorUtil.RESET);
        System.out.println(ColorUtil.DIM + "  Enter SELECT query (single line). Type EXIT to cancel." + ColorUtil.RESET);
        String sql = InputUtil.readString("  SQL> ");
        if (sql.trim().equalsIgnoreCase("EXIT") || sql.isBlank()) return;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Safety: only allow SELECT for custom queries
            String trimmed = sql.trim().toUpperCase();
            if (!trimmed.startsWith("SELECT") && !trimmed.startsWith("SHOW") && !trimmed.startsWith("DESCRIBE")) {
                PrintUtil.error("Only SELECT / SHOW / DESCRIBE allowed here for safety.");
                return;
            }
            try (ResultSet rs = stmt.executeQuery(sql)) {
                PrintUtil.subHeader("Query Result");
                PrintUtil.resultSet(rs);
            }
        } catch (SQLException e) {
            PrintUtil.error("SQL Error: " + e.getMessage());
        }
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void runQuery(String title, String explanation, String sql) {
        PrintUtil.subHeader(title);
        System.out.println(ColorUtil.DIM + "  Concept: " + explanation + ColorUtil.RESET);
        System.out.println(ColorUtil.BOLD_BLUE + "\n  SQL:");
        System.out.println("  " + sql.replace("\n","\n  ") + ColorUtil.RESET + "\n");
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            if (sql.trim().toUpperCase().startsWith("CALL")) {
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                if (rs != null) PrintUtil.resultSet(rs);
            } else {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    PrintUtil.resultSet(rs);
                }
            }
        } catch (SQLException e) {
            PrintUtil.error("SQL Error: " + e.getMessage());
        }
        InputUtil.pause();
    }

    private void exec(String title, String explanation, String sql) {
        PrintUtil.subHeader(title);
        System.out.println(ColorUtil.DIM + "  Concept: " + explanation + ColorUtil.RESET);
        System.out.println(ColorUtil.BOLD_BLUE + "\n  SQL:\n  " + sql.replace("\n","\n  ") + ColorUtil.RESET);
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            PrintUtil.success("Executed. Rows affected: " + rows);
        } catch (SQLException e) {
            PrintUtil.error("SQL Error: " + e.getMessage());
        }
        InputUtil.pause();
    }
}
