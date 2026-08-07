package com.springmvc.service;

import com.springmvc.dao.UserDAO;
import com.springmvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserService — Business logic layer for user operations.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * The Service layer sits between Controllers and DAOs.
 *
 *   Controller  →  Service  →  DAO  →  Database
 *
 * Reasons for this separation:
 *  1. Business rules (e.g., "email must be unique") belong in the
 *     service, NOT in controllers (which should only handle HTTP) or
 *     DAOs (which should only know SQL).
 *  2. Multiple controllers can call the same service methods.
 *  3. Service methods are easily testable in isolation (mock the DAO).
 *
 * =====================================================================
 * WHY @Service?
 * =====================================================================
 * @Service is a specialisation of @Component that:
 *  - Tells @ComponentScan to pick up this class as a Spring bean.
 *  - Communicates intent: "this bean contains business logic".
 *  - Future: @Service enables Spring's @Transactional support.
 *
 * =====================================================================
 * ABOUT PASSWORD HASHING
 * =====================================================================
 * In this demo we store passwords as plain text for simplicity.
 *
 * In production, ALWAYS hash passwords using BCrypt:
 *
 *   // Add to pom.xml:
 *   // spring-security-crypto (no need for full Spring Security)
 *
 *   BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
 *   String hashed = encoder.encode(user.getPassword());
 *   user.setPassword(hashed);
 *
 *   // During login:
 *   boolean matches = encoder.matches(rawPassword, storedHash);
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDAO userDAO;

    /**
     * Constructor injection.
     * Spring automatically provides the UserDAO bean created
     * via @Repository + @ComponentScan.
     */
    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // =====================================================================
    // SIGNUP
    // =====================================================================

    /**
     * Registers a new user.
     *
     * Business rules enforced here:
     *  1. Email must not already be registered.
     *     → Returns false if duplicate found.
     *  2. (Production) Password should be hashed before storing.
     *
     * @param user the user submitted from the signup form
     * @return true if signup was successful, false if email is duplicate
     */
    public boolean registerUser(User user) {
        log.info("Attempting to register user: {}", user.getEmail());

        // Rule 1: Check for duplicate email
        if (userDAO.existsByEmail(user.getEmail())) {
            log.warn("Registration failed: email already exists → {}", user.getEmail());
            return false; // Duplicate email
        }

        // Production TODO: hash password before saving
        // user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userDAO.save(user);
        log.info("User registered successfully: {}", user.getEmail());
        return true;
    }

    // =====================================================================
    // LOGIN
    // =====================================================================

    /**
     * Authenticates a user by email and password.
     *
     * WHY Optional<User>?
     * The method either returns a valid authenticated User or empty.
     * Using Optional forces the controller to handle both cases
     * explicitly — no silent null pointer bugs.
     *
     * HOW authentication works:
     *  1. Find the user by email in the DB.
     *  2. If not found → return empty.
     *  3. Compare the submitted password with the stored password.
     *     (In production: BCrypt encoder.matches(raw, hashed))
     *  4. If match → return the User.
     *  5. If no match → return empty.
     *
     * @param email    submitted email
     * @param password submitted plain-text password
     * @return Optional<User> — present if credentials are valid
     */
    public Optional<User> authenticate(String email, String password) {
        log.info("Authentication attempt for email: {}", email);

        Optional<User> userOpt = userDAO.findByEmail(email);

        if (userOpt.isEmpty()) {
            log.warn("Authentication failed: no user found with email {}", email);
            return Optional.empty();
        }

        User user = userOpt.get();

        // Plain text comparison (replace with encoder.matches() in production)
        if (user.getPassword().equals(password)) {
            log.info("Authentication successful for: {}", email);
            return Optional.of(user);
        } else {
            log.warn("Authentication failed: wrong password for {}", email);
            return Optional.empty();
        }
    }
}
