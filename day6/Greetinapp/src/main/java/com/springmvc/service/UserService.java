package com.springmvc.service;

import com.springmvc.dao.UserDAO;
import com.springmvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // =====================================================================
    // SIGNUP
    // =====================================================================

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
