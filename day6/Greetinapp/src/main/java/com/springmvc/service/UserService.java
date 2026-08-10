package com.springmvc.service;

import com.springmvc.dao.UserDAO;
import com.springmvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // =====================================================================
    // SIGNUP
    // =====================================================================
    
    @Override
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
    
    @Override
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
    
    
 // =====================================================================
    // READ (by id)
    // =====================================================================
    @Override
    public Optional<User> getUserById(Long id) {
        log.debug("Fetching user by id: {}", id);
        return userDAO.findById(id);
    }

    // =====================================================================
    // UPDATE
    // =====================================================================
    @Override
    public boolean updateUser(User user) {
        log.info("Attempting to update user id: {}", user.getId());

        // If the email was changed, make sure it doesn't collide with
        // another user's email (but allow keeping the same email).
        Optional<User> existing = userDAO.findByEmail(user.getEmail());
        if (existing.isPresent() && !existing.get().getId().equals(user.getId())) {
            log.warn("Update failed: email already in use → {}", user.getEmail());
            return false;
        }

        boolean updated = userDAO.update(user);
        if (updated) {
            log.info("User updated successfully: id {}", user.getId());
        } else {
            log.warn("Update failed: no user found with id {}", user.getId());
        }
        return updated;
    }

    // =====================================================================
    // DELETE
    // =====================================================================
    @Override
    public boolean deleteUser(Long id) {
        log.info("Attempting to delete user id: {}", id);
        boolean deleted = userDAO.deleteById(id);
        if (deleted) {
            log.info("User deleted successfully: id {}", id);
        } else {
            log.warn("Delete failed: no user found with id {}", id);
        }
        return deleted;
    }
    
 // =====================================================================
    // CHECK EXISTENCE (used by login to give a specific error message)
    // =====================================================================
    @Override
    public boolean userExists(String email) {
        return userDAO.existsByEmail(email);
    }
}
