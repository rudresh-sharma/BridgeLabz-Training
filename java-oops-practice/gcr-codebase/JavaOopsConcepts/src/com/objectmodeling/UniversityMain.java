package com.objectmodeling;

public class UniversityMain {
    public static void main(String[] args) {

        // Independent Faculty objects
        Faculty f1 = new Faculty("Dr. Sharma");
        Faculty f2 = new Faculty("Dr. Mehta");

        // Create University
        University uni = new University("Global University");

        // Add Departments (Composition)
        uni.addDepartment("Computer Science");
        uni.addDepartment("Mechanical");

        // Add Faculty (Aggregation)
        uni.addFaculty(f1);
        uni.addFaculty(f2);

        // Display
        uni.showUniversity();

        System.out.println("\nDeleting University...");
        uni = null;   // Departments are destroyed (composition)

        System.out.println("\nFaculty still exist:");
        f1.showFaculty();
        f2.showFaculty();
    }
}
