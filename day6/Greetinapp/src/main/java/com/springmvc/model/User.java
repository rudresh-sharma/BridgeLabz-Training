package com.springmvc.model;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    // Matches: id BIGINT AUTO_INCREMENT PRIMARY KEY
    private Long id;

    // Matches: name VARCHAR(100)
    @NotBlank
    private String name;

    // Matches: email VARCHAR(100) UNIQUE
    @NotBlank
    @Email
    private String email;

    // Matches: password VARCHAR(255).
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    // =====================================================================
    // CONSTRUCTORS
    // =====================================================================

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // =====================================================================
    // GETTERS & SETTERS
    // =====================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // =====================================================================
    // UTILITY METHODS
    // =====================================================================

    /**
     * Returns a safe string representation without exposing the password.
     * Important for logging — never log passwords.
     */
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", password='[PROTECTED]'" +
               '}';
    }
}
