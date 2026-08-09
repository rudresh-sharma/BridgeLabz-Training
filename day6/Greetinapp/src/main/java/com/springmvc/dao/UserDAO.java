package com.springmvc.dao;

import com.springmvc.model.User;
import com.springmvc.util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public class UserDAO {

    private static final Logger log = LoggerFactory.getLogger(UserDAO.class);

    private final DBConnection dbConnection;

   
    @Autowired
    public UserDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // =====================================================================
    // SQL CONSTANTS
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

    private User mapRowToUser(ResultSet rs) throws SQLException {
        return new User(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
