package com.healthclinicapp.database;

import com.healthclinicapp.util.ColorUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton database-connection manager for the Health Clinic system.
 *
 * ┌──────────────────────────────────────────────────────────────────────────┐
 * │  CONFIGURATION  — change the four constants below to match your MySQL   │
 * │  installation before running the application for the first time.        │
 * └──────────────────────────────────────────────────────────────────────────┘
 *
 * Two URL flavours are exposed:
 *   BASE_URL  — no database selected (used by DatabaseInitializer to CREATE the DB)
 *   DB_URL    — points directly at health_clinic_db (used by all DAOs)
 */
public class DatabaseConnection {

    // ── !! Change these if your MySQL setup differs !! ────────────────────────
    private static final String HOST      = "localhost";
    private static final int    PORT      = 3306;
    public  static final String DB_NAME   = "health_clinic_db";
    private static final String USER      = "root";
    private static final String PASSWORD  = "Rudresh@2005";   // ← set your MySQL password
    // ─────────────────────────────────────────────────────────────────────────

    private static final String COMMON_PARAMS =
            "?useSSL=false" +
            "&allowPublicKeyRetrieval=true" +
            "&serverTimezone=Asia/Kolkata" +
            "&characterEncoding=UTF-8" +
            "&autoReconnect=true";

    /** JDBC URL that targets the MySQL server but NO specific database. */
    public static final String BASE_URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + COMMON_PARAMS;

    /** JDBC URL that targets the application database directly. */
    public static final String DB_URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + COMMON_PARAMS;

    // Exposed so DatabaseInitializer can pass them to DriverManager
    public static final String DB_USER     = USER;
    public static final String DB_PASSWORD = PASSWORD;

    // ── Singleton instance ────────────────────────────────────────────────────
    private static Connection connection = null;

    private DatabaseConnection() { /* singleton — do not instantiate */ }

    /**
     * Returns the shared {@link Connection}.
     * Creates a fresh connection on the first call or if the existing one
     * has been closed (e.g. due to a MySQL wait_timeout).
     *
     * @throws SQLException if the connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }
        return connection;
    }

    /**
     * Opens a <em>new</em> connection to the MySQL server root (no database).
     * Used only by {@link DatabaseInitializer} to execute CREATE DATABASE.
     */
    public static Connection getBaseConnection() throws SQLException {
        return DriverManager.getConnection(BASE_URL, USER, PASSWORD);
    }

    /**
     * Opens a fresh connection directly to the application database.
     * Some DAOs use this for transaction blocks that need their own connection.
     */
    public static Connection getFreshConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    /** Closes the shared connection.  Call once at application shutdown. */
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println(ColorUtil.YELLOW + "  Database connection closed." + ColorUtil.RESET);
                }
            } catch (SQLException e) {
                System.err.println("  Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Quick connectivity test.
     * @return true if the server is reachable within 3 seconds
     */
    public static boolean testConnection() {
        try (Connection test = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            return test.isValid(3);
        } catch (SQLException e) {
            return false;
        }
    }
}
