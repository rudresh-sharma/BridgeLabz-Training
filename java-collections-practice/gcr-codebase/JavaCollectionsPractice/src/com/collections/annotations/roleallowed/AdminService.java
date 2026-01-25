package com.collections.annotations.roleallowed;

public class AdminService {

    @RoleAllowed("ADMIN")
    public void deleteAllUsers() {
        System.out.println("All users deleted successfully!");
    }

    public void viewUsers() {
        System.out.println("Viewing users...");
    }
}
