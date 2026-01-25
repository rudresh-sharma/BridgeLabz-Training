package com.collections.annotations.roleallowed;

public class SecurityContext {

    // Simulated current user role
    private static String currentRole = "USER";  // Change to "ADMIN" to allow

    public static String getCurrentRole() {
        return currentRole;
    }

    public static void setCurrentRole(String role) {
        currentRole = role;
    }
}
