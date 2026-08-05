package com.healthclinicapp.dao;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.model.AuditLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** DAO for audit_log and activity_log tables. */
public class AuditDAO {

    public List<AuditLog> findAll(int limit) throws SQLException {
        String sql = "SELECT * FROM audit_log ORDER BY created_at DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    public List<AuditLog> findByTable(String tableName, int limit) throws SQLException {
        String sql = "SELECT * FROM audit_log WHERE table_name=? ORDER BY created_at DESC LIMIT ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,tableName); ps.setInt(2,limit);
            try (ResultSet rs = ps.executeQuery()) { return mapList(rs); }
        }
    }

    /** Calls stored procedure AuditLogReport. */
    public ResultSet getAuditReportViaProcedure(Connection conn, String table, int limit) throws SQLException {
        CallableStatement cs = conn.prepareCall("{CALL AuditLogReport(?,?)}");
        cs.setString(1,table); cs.setInt(2,limit);
        return cs.executeQuery();
    }

    public ResultSet getActivityLog(Connection conn, int limit) throws SQLException {
        String sql = "SELECT * FROM activity_log ORDER BY created_at DESC LIMIT ?";
        PreparedStatement ps = conn.prepareStatement(sql); ps.setInt(1,limit);
        return ps.executeQuery();
    }

    /** Audit log summary grouped by table and operation. */
    public ResultSet getAuditSummary(Connection conn) throws SQLException {
        String sql = """
            SELECT table_name, operation, COUNT(*) AS event_count,
                   MIN(created_at) AS first_event, MAX(created_at) AS last_event
            FROM audit_log
            GROUP BY table_name, operation
            ORDER BY table_name, event_count DESC
            """;
        return conn.prepareStatement(sql).executeQuery();
    }

    /** Delete audit logs older than N days. */
    public int deleteOlderThan(int days) throws SQLException {
        String sql = "DELETE FROM audit_log WHERE created_at < DATE_SUB(NOW(), INTERVAL ? DAY)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,days); return ps.executeUpdate();
        }
    }

    private List<AuditLog> mapList(ResultSet rs) throws SQLException {
        List<AuditLog> list = new ArrayList<>();
        while (rs.next()) {
            AuditLog a = new AuditLog();
            a.setLogId     (rs.getInt      ("log_id"));
            a.setTableName (rs.getString   ("table_name"));
            a.setOperation (rs.getString   ("operation"));
            int rid = rs.getInt("record_id"); a.setRecordId(rs.wasNull()?null:rid);
            a.setDescription(rs.getString  ("description"));
            a.setCreatedAt (rs.getTimestamp("created_at"));
            list.add(a);
        }
        return list;
    }
}
