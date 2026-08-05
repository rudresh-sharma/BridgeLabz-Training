package com.healthclinicapp.menu;

import com.healthclinicapp.dao.AuditDAO;
import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.database.DatabaseInitializer;
import com.healthclinicapp.util.*;

import java.sql.*;
import java.util.List;

/**
 * Database Administration Menu.
 * Covers audit logs, views, connection info, schema inspection, and maintenance.
 */
public class DatabaseMenu {

    private final AuditDAO auditDao = new AuditDAO();

    public void show() {
        while (true) {
            PrintUtil.subHeader("DATABASE MANAGEMENT");
            System.out.println(ColorUtil.CYAN +
                "  ── Connection & Info ──\n" +
                "  1. Connection Info\n" +
                "  2. Show All Views\n" +
                "  3. Show All Stored Procedures\n" +
                "  4. Show All Stored Functions\n" +
                "  5. Show All Triggers\n" +
                "  6. Show All Events\n" +
                "  7. Show Table Row Counts\n" +
                "  8. Show Database Size\n" +
                "  ── Audit & Logs ──\n" +
                "  9.  View Recent Audit Log\n" +
                "  10. Audit Log by Table\n" +
                "  11. Audit Log Summary\n" +
                "  12. View Activity Log\n" +
                "  13. Clear Old Audit Logs (DELETE demo)\n" +
                "  ── Views ──\n" +
                "  14. View: v_patient_summary\n" +
                "  15. View: v_doctor_summary\n" +
                "  16. View: v_appointment_details\n" +
                "  17. View: v_billing_details\n" +
                "  18. View: v_revenue_report\n" +
                "  19. View: v_medicine_inventory\n" +
                "  20. View: v_patient_history (complex multi-join view)\n" +
                "  ── Maintenance ──\n" +
                "  21. Re-run Database Initializer\n" +
                "  22. Mark past 'Scheduled' as No-Show (BulkUpdate Procedure)\n" +
                "  23. Inactive Patients Report (Procedure)\n" +
                "  24. Yearly Revenue Report (Procedure)\n" +
                "  0. Back" + ColorUtil.RESET);

            int choice = InputUtil.readInt("  Choice: ", 0, 24);
            switch (choice) {
                case 1  -> connectionInfo();
                case 2  -> showObjects("FULL TABLES IN " + DatabaseConnection.DB_NAME + " WHERE Table_type='VIEW'", "Views");
                case 3  -> showObjects("PROCEDURE STATUS WHERE Db='" + DatabaseConnection.DB_NAME + "'", "Stored Procedures");
                case 4  -> showObjects("FUNCTION STATUS WHERE Db='" + DatabaseConnection.DB_NAME + "'", "Stored Functions");
                case 5  -> showObjects("TRIGGERS", "Triggers");
                case 6  -> showObjects("EVENTS", "Events");
                case 7  -> rowCounts();
                case 8  -> dbSize();
                case 9  -> recentAudit();
                case 10 -> auditByTable();
                case 11 -> auditSummary();
                case 12 -> activityLog();
                case 13 -> clearOldLogs();
                case 14 -> queryView("v_patient_summary");
                case 15 -> queryView("v_doctor_summary");
                case 16 -> queryView("v_appointment_details");
                case 17 -> queryView("v_billing_details");
                case 18 -> queryView("v_revenue_report");
                case 19 -> queryView("v_medicine_inventory");
                case 20 -> queryView("v_patient_history");
                case 21 -> reinit();
                case 22 -> bulkNoShow();
                case 23 -> inactivePatients();
                case 24 -> yearlyRevenue();
                case 0  -> { return; }
            }
        }
    }

    private void connectionInfo() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            PrintUtil.subHeader("Database Connection Info");
            PrintUtil.kv("Product",       meta.getDatabaseProductName());
            PrintUtil.kv("Version",       meta.getDatabaseProductVersion());
            PrintUtil.kv("Driver",        meta.getDriverName() + " " + meta.getDriverVersion());
            PrintUtil.kv("URL",           meta.getURL());
            PrintUtil.kv("User",          meta.getUserName());
            PrintUtil.kv("Max Conn",      String.valueOf(meta.getMaxConnections()));
            PrintUtil.kv("Auto Commit",   String.valueOf(conn.getAutoCommit()));
            PrintUtil.kv("Isolation",     isolationName(conn.getTransactionIsolation()));
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void showObjects(String showSuffix, String title) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW " + showSuffix)) {
            PrintUtil.subHeader(title);
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void rowCounts() {
        String sql = """
            SELECT table_name,
                   table_rows AS approx_rows,
                   ROUND((data_length + index_length) / 1024, 2) AS size_kb
            FROM information_schema.TABLES
            WHERE table_schema = ?
            ORDER BY approx_rows DESC
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, DatabaseConnection.DB_NAME);
            PrintUtil.subHeader("Table Row Counts (information_schema)");
            try (ResultSet rs = ps.executeQuery()) { PrintUtil.resultSet(rs); }
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void dbSize() {
        String sql = """
            SELECT table_schema AS db_name,
                   ROUND(SUM(data_length + index_length) / 1024 / 1024, 2) AS size_mb,
                   COUNT(table_name) AS table_count
            FROM information_schema.TABLES
            WHERE table_schema = ?
            GROUP BY table_schema
            """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, DatabaseConnection.DB_NAME);
            PrintUtil.subHeader("Database Size (information_schema)");
            try (ResultSet rs = ps.executeQuery()) { PrintUtil.resultSet(rs); }
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void recentAudit() {
        int limit = InputUtil.readInt("  How many recent entries? ", 1, 200);
        try {
            var logs = auditDao.findAll(limit);
            PrintUtil.subHeader("Recent Audit Log (" + logs.size() + " entries)");
            String[] headers = {"Log ID","Table","Op","Record ID","Description","Created At"};
            String[][] rows = new String[logs.size()][6];
            for (int i=0;i<logs.size();i++) {
                var l = logs.get(i);
                rows[i] = new String[]{String.valueOf(l.getLogId()),l.getTableName(),l.getOperation(),l.getRecordId()!=null?String.valueOf(l.getRecordId()):"",l.getDescription(),String.valueOf(l.getCreatedAt())};
            }
            PrintUtil.table(headers,rows);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void auditByTable() {
        String table = InputUtil.readString("  Table name (e.g. patient, billing): ");
        int limit = InputUtil.readInt("  Limit: ", 1, 200);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = auditDao.getAuditReportViaProcedure(conn, table, limit)) {
            PrintUtil.subHeader("Audit Log — " + table + " (via AuditLogReport procedure)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void auditSummary() {
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = auditDao.getAuditSummary(conn)) {
            PrintUtil.subHeader("Audit Log Summary (GROUP BY table + operation)");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void activityLog() {
        int limit = InputUtil.readInt("  How many entries? ", 1, 200);
        try (Connection conn = DatabaseConnection.getConnection();
             ResultSet rs = auditDao.getActivityLog(conn, limit)) {
            PrintUtil.subHeader("Activity Log");
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void clearOldLogs() {
        int days = InputUtil.readInt("  Delete logs older than N days: ", 1, 3650);
        if (!InputUtil.confirm("  This will DELETE audit logs older than " + days + " days. Confirm?")) return;
        try {
            int deleted = auditDao.deleteOlderThan(days);
            PrintUtil.success("Deleted " + deleted + " audit log entries older than " + days + " days.");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void queryView(String viewName) {
        PrintUtil.subHeader("View: " + viewName);
        String sql = "SELECT * FROM " + viewName + " LIMIT 20";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            PrintUtil.resultSet(rs);
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void reinit() {
        if (!InputUtil.confirm("  Re-run DatabaseInitializer? (Safe — uses IF NOT EXISTS everywhere)")) return;
        DatabaseInitializer.initialize();
        PrintUtil.success("Re-initialization complete.");
    }

    private void bulkNoShow() {
        String date = InputUtil.readDate("  Mark as No-Show if scheduled before (yyyy-MM-dd): ");
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL BulkUpdateNoShow(?)}")) {
            cs.setDate(1, DateUtil.toSqlDate(date));
            cs.execute();
            PrintUtil.success("No-Show update complete (cursor-based loop procedure).");
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void inactivePatients() {
        int days = InputUtil.readInt("  Mark inactive if no appointment in N days: ", 1, 3650);
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL InactivePatientsReport(?)}")) {
            cs.setInt(1, days);
            PrintUtil.subHeader("Inactive Patients Report (DATEDIFF / HAVING)");
            try (ResultSet rs = cs.executeQuery()) { PrintUtil.resultSet(rs); }
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private void yearlyRevenue() {
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall("{CALL YearlyRevenueReport()}")) {
            PrintUtil.subHeader("Yearly Revenue Report (GROUP BY YEAR)");
            try (ResultSet rs = cs.executeQuery()) { PrintUtil.resultSet(rs); }
        } catch (SQLException e) { PrintUtil.error(e.getMessage()); }
    }

    private String isolationName(int level) {
        return switch (level) {
            case Connection.TRANSACTION_READ_COMMITTED  -> "READ COMMITTED";
            case Connection.TRANSACTION_READ_UNCOMMITTED-> "READ UNCOMMITTED";
            case Connection.TRANSACTION_REPEATABLE_READ -> "REPEATABLE READ";
            case Connection.TRANSACTION_SERIALIZABLE    -> "SERIALIZABLE";
            default -> "UNKNOWN (" + level + ")";
        };
    }
}
