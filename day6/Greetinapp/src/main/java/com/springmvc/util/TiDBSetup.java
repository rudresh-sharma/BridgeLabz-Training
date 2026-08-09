package com.springmvc.util;

import java.sql.*;

public class TiDBSetup {

    public static void main(String[] args) throws Exception {

        System.out.println("=== GreetingApp — TiDB Cloud Setup ===\n");


        // Load environment variables
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        String driver = System.getenv("DB_DRIVER");


        if (url == null || username == null || password == null) {
            System.err.println("ERROR: Database environment variables are missing!");
            System.err.println("Required:");
            System.err.println("DB_URL");
            System.err.println("DB_USERNAME");
            System.err.println("DB_PASSWORD");
            return;
        }


        System.out.println("Connecting to: " + url);
        System.out.println("Username      : " + username);
        System.out.println();


        // Load MySQL Driver
        Class.forName(driver);


        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            System.out.println("✓ Connected to TiDB Cloud successfully!\n");

            DatabaseMetaData metaData = conn.getMetaData();

            System.out.println("  Database : " + metaData.getDatabaseProductName());
            System.out.println("  Version  : " + metaData.getDatabaseProductVersion());
            System.out.println("  Catalog  : " + conn.getCatalog());
            System.out.println();


            // Create users table
            String createTable = """
                    CREATE TABLE IF NOT EXISTS users (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL
                    )
                    """;


            try (Statement st = conn.createStatement()) {

                st.execute(createTable);

                System.out.println("✓ Table 'users' created or already exists.\n");
            }



            // Insert test user
            String insertQuery =
                    """
                    INSERT IGNORE INTO users(name,email,password)
                    VALUES(?,?,?)
                    """;


            try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {

                ps.setString(1, "Rudresh");
                ps.setString(2, "rudresh@example.com");
                ps.setString(3, "password123");


                int rows = ps.executeUpdate();

                System.out.println("✓ Test user inserted: " + rows + " row(s).\n");
            }



            // Fetch users
            String selectQuery = "SELECT id,name,email FROM users";


            try (
                PreparedStatement ps = conn.prepareStatement(selectQuery);
                ResultSet rs = ps.executeQuery()
            ) {

                System.out.println("--- Users in Database ---");

                while (rs.next()) {

                    System.out.printf(
                            "id=%-5d name=%-15s email=%s%n",
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }

                System.out.println();
            }


            System.out.println("=== All checks passed! TiDB Cloud is ready. ===");


        } catch (SQLException e) {

            System.err.println("Database connection failed!");
            e.printStackTrace();

        }
    }
}