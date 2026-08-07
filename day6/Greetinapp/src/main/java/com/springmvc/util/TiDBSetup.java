/**
 * TiDB Connection + Schema Test
 * Run with: mvn exec:java -Dexec.mainClass="com.springmvc.util.TiDBSetup"
 *
 * This one-time utility:
 *  1. Loads db.properties from classpath
 *  2. Connects to TiDB Cloud
 *  3. Creates the 'users' table if it doesn't exist
 *  4. Inserts a test user
 *  5. Reads it back to confirm everything works
 */
package com.springmvc.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class TiDBSetup {

    public static void main(String[] args) throws Exception {
        System.out.println("=== GreetingApp — TiDB Cloud Setup ===\n");

        // Load db.properties
        Properties props = new Properties();
        try (InputStream is = TiDBSetup.class.getClassLoader()
                                             .getResourceAsStream("db.properties")) {
            if (is == null) {
                System.err.println("ERROR: db.properties not found on classpath!");
                return;
            }
            props.load(is);
        }

        String url      = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        System.out.println("Connecting to: " + url.substring(0, url.indexOf('?')));
        System.out.println("Username      : " + username);
        System.out.println();

        // Load driver
        Class.forName(props.getProperty("db.driver"));

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("✓ Connected to TiDB Cloud successfully!\n");
            System.out.println("  TiDB version : " + conn.getMetaData().getDatabaseProductVersion());
            System.out.println("  Catalog      : " + conn.getCatalog());
            System.out.println();

            // Create table
            String createTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id       BIGINT        AUTO_INCREMENT PRIMARY KEY,
                    name     VARCHAR(100)  NOT NULL,
                    email    VARCHAR(100)  NOT NULL UNIQUE,
                    password VARCHAR(255)  NOT NULL
                )
                """;
            try (Statement st = conn.createStatement()) {
                st.execute(createTable);
                System.out.println("✓ Table 'users' created (or already exists).\n");
            }

            // Insert test user
            String insert = "INSERT IGNORE INTO users (name, email, password) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insert)) {
                ps.setString(1, "Rudresh");
                ps.setString(2, "rudresh@example.com");
                ps.setString(3, "password123");
                int rows = ps.executeUpdate();
                System.out.println("✓ Test user inserted: " + rows + " row(s).\n");
            }

            // Read back
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
                 ResultSet rs = ps.executeQuery()) {
                System.out.println("  --- Users in DB ---");
                while (rs.next()) {
                    System.out.printf("  id=%-4d  name=%-15s  email=%s%n",
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"));
                }
                System.out.println();
            }

            System.out.println("=== All checks passed! TiDB Cloud is ready. ===");
        }
    }
}
