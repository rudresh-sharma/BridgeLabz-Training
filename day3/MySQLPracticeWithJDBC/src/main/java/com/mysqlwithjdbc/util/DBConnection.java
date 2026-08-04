package com.mysqlwithjdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    // Separate server URL from the specific database name
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "mysqlwithjdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Rudresh@2005";

    static {
        initializeDatabase();
    }

    private static void initializeDatabase() {
        // Connect to the MySQL server instance directly
        try (Connection conn = DriverManager.getConnection(SERVER_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {
            
            // Execute creation query if it is missing
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            
        } catch (SQLException e) {
            throw new RuntimeException("Failed to verify or create database: " + DB_NAME, e);
        }
    }

    public static Connection getConnection() {
        try {
            // Connect to the fully qualified database URL
            return DriverManager.getConnection(SERVER_URL + DB_NAME, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to database.", e);
        }
    }
    
    
    
}
