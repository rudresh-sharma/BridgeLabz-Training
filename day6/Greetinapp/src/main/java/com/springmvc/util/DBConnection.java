package com.springmvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DBConnection — utility class to obtain JDBC Connection objects.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * In raw JDBC applications you call DriverManager.getConnection(url, user, pw)
 * directly. That approach hard-codes credentials and creates a new TCP
 * connection to the database on every request — very slow.
 *
 * This utility class instead wraps Spring's DataSource bean:
 *  - DataSource is configured once in WebConfig (with credentials from
 *    db.properties).
 *  - DAOs call DBConnection.getConnection() which internally delegates
 *    to dataSource.getConnection().
 *  - This gives us a single, testable abstraction. In future we can
 *    swap DriverManagerDataSource for HikariCP (connection pool) without
 *    changing any DAO code.
 *
 * =====================================================================
 * WHY @Component?
 * =====================================================================
 * @Component marks this class as a Spring-managed bean.
 * Spring will create ONE instance and inject it wherever it is needed.
 * This is the "Spring Way" — never instantiate utility classes with
 * new DBConnection() in production code.
 *
 * =====================================================================
 * WHY CONSTRUCTOR INJECTION (not @Autowired on field)?
 * =====================================================================
 * Constructor injection is the recommended approach because:
 *  1. Dependencies are explicitly visible in the constructor signature.
 *  2. The class is immutable — dataSource cannot be reassigned.
 *  3. The class is easily testable — pass a mock DataSource in tests.
 *  4. Spring's own documentation recommends constructor injection.
 *
 * Field injection (@Autowired private DataSource dataSource) hides
 * dependencies and makes unit testing harder.
 */
@Component
public class DBConnection {

    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);

    // Spring injects the DataSource configured in WebConfig.
    // The field is final — cannot be accidentally reassigned.
    private final DataSource dataSource;

    /**
     * Constructor injection.
     *
     * @Autowired is optional on single-constructor classes in Spring 4.3+
     * but added here for clarity.
     */
    @Autowired
    public DBConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Obtain a JDBC Connection from the DataSource.
     *
     * WHY return a Connection instead of wrapping queries here?
     * Keeping this method simple lets DAOs manage their own transactions
     * and use PreparedStatement correctly with try-with-resources.
     * The caller is responsible for closing the connection.
     *
     * @return a live JDBC Connection
     * @throws RuntimeException if the connection cannot be established
     */
    public Connection getConnection() {
        try {
            Connection conn = dataSource.getConnection();
            log.debug("Database connection obtained: {}", conn);
            return conn;
        } catch (SQLException e) {
            log.error("Failed to obtain database connection", e);
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
