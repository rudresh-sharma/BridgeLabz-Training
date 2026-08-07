package com.springmvc.dao;

import com.springmvc.model.User;
import com.springmvc.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

/**
 * UserDAO — Data Access Object for the "users" table.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * The DAO pattern separates database access logic from business logic.
 * UserDAO is the ONLY class that knows SQL.  The service layer calls
 * DAO methods without knowing how data is stored (MySQL, PostgreSQL, etc.)
 *
 * =====================================================================
 * WHY @Repository?
 * =====================================================================
 * @Repository is a specialisation of @Component.  It does two things:
 *  1. Makes Spring detect this class during @ComponentScan.
 *  2. Enables Spring's exception translation — JDBC's checked
 *     SQLException is automatically translated into Spring's unchecked
 *     DataAccessException hierarchy.  This means callers don't need
 *     to catch SQLException everywhere.
 *
 * =====================================================================
 * WHY PreparedStatement (NEVER string concatenation)?
 * =====================================================================
 * SQL injection is the #1 web vulnerability.
 *
 * UNSAFE (DO NOT USE):
 *   String sql = "SELECT * FROM users WHERE email = '" + email + "'";
 *   If email = "' OR '1'='1" the attacker bypasses authentication.
 *
 * SAFE (our approach):
 *   String sql = "SELECT * FROM users WHERE email = ?";
 *   ps.setString(1, email);
 *   The ? is a placeholder — the JDBC driver sends the SQL and the
 *   parameter value separately to MySQL. The database NEVER interprets
 *   the parameter as SQL code.
 *
 * =====================================================================
 * WHY try-with-resources?
 * =====================================================================
 * Connection, PreparedStatement, and ResultSet all implement
 * AutoCloseable.  try-with-resources guarantees they are closed even
 * if an exception is thrown — preventing connection leaks.
 */
@Repository
public class UserDAO {

    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    private final DBConnection dbConnection;

    /**
     * Constructor injection — DBConnection is provided by Spring.
     * No field injection (@Autowired on field) as per best practices.
     */
    @Autowired
    public UserDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // =====================================================================
    // SQL CONSTANTS
    // Never write SQL inline in methods — makes it easy to find & modify.
    // =====================================================================

    private static final String SQL_INSERT_USER =
        "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

    private static final String SQL_FIND_BY_EMAIL =
        "SELECT id, name, email, password FROM users WHERE email = ?";

    private static final String SQL_EXISTS_BY_EMAIL =
        "SELECT COUNT(*) FROM users WHERE email = ?";

    // =====================================================================
    // PUBLIC METHODS
    // =====================================================================

    /**
     * Saves a new user to the database.
     *
     * Uses Statement.RETURN_GENERATED_KEYS to retrieve the auto-generated
     * id so we can set it back on the User object.
     *
     * WHY return void and not User?
     * The caller (service layer) already has the User object; returning
     * it would be redundant. We update the id field in-place.
     *
     * @param user the user to save (id field will be set after save)
     */
    public void save(User user) {
        log.info("Saving user: {}", user.getEmail());

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters — index is 1-based
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rowsAffected = ps.executeUpdate();
            log.debug("Rows inserted: {}", rowsAffected);

            // Retrieve the auto-generated id and set it on the User object
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                    log.info("User saved with id: {}", user.getId());
                }
            }

        } catch (SQLException e) {
            log.error("Error saving user: {}", user.getEmail(), e);
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    /**
     * Finds a user by their email address.
     *
     * Returns Optional<User> instead of null to force the caller
     * to handle the "not found" case explicitly.
     * This is safer than returning null which can cause NullPointerException.
     *
     * @param email the email to search for
     * @return Optional containing the User, or Optional.empty() if not found
     */
    public Optional<User> findByEmail(String email) {
        log.debug("Looking up user by email: {}", email);

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_EMAIL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Map ResultSet columns to User object fields
                    User user = mapRowToUser(rs);
                    log.debug("User found: {}", user.getEmail());
                    return Optional.of(user);
                }
            }

        } catch (SQLException e) {
            log.error("Error finding user by email: {}", email, e);
            throw new RuntimeException("Error finding user: " + e.getMessage(), e);
        }

        log.debug("No user found with email: {}", email);
        return Optional.empty();
    }

    /**
     * Checks if a user with the given email already exists.
     *
     * WHY a separate method instead of findByEmail().isPresent()?
     * Using COUNT(*) is more efficient — the database doesn't need
     * to read all columns, only check existence.
     *
     * @param email the email to check
     * @return true if the email is already registered
     */
    public boolean existsByEmail(String email) {
        log.debug("Checking existence of email: {}", email);

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_EXISTS_BY_EMAIL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    boolean exists = count > 0;
                    log.debug("Email {} exists: {}", email, exists);
                    return exists;
                }
            }

        } catch (SQLException e) {
            log.error("Error checking email existence: {}", email, e);
            throw new RuntimeException("Error checking email: " + e.getMessage(), e);
        }

        return false;
    }

    // =====================================================================
    // PRIVATE HELPER
    // =====================================================================

    /**
     * Maps a single ResultSet row to a User object.
     *
     * WHY extract to a separate method?
     * If we have multiple queries that return user rows, we centralise
     * the mapping logic here. Adding a new column only requires changing
     * one place.
     *
     * @param rs a ResultSet positioned at a valid row
     * @return the mapped User object
     */
    private User mapRowToUser(ResultSet rs) throws SQLException {
        return new User(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
