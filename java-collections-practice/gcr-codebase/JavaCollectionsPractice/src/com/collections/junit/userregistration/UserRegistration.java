package com.collections.junit.userregistration;

public class UserRegistration {

    public void registerUser(String username, String email, String password) {
        if (username == null || username.length() < 5) {
            throw new IllegalArgumentException();
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException();
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException();
        }
    }
}