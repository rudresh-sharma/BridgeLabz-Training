package com.collections.annotations.maxlength;

public class MaxLengthDemo {

    public static void main(String[] args) {

        // Valid case
        try {
            User u1 = new User("Rudresh");
            System.out.println("User created: " + u1.getUsername());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Invalid case (length > 10)
        try {
            User u2 = new User("VeryLongUsername123");
            System.out.println("User created: " + u2.getUsername());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
