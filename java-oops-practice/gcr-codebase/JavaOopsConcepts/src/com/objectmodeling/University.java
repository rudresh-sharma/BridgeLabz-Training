package com.objectmodeling;

import java.util.ArrayList;

public class University {
    private String universityName;
    private ArrayList<Department> departments;  // Composition
    private ArrayList<Faculty> faculties;       // Aggregation

    public University(String universityName) {
        this.universityName = universityName;
        departments = new ArrayList<>();
        faculties = new ArrayList<>();
    }

    // Composition
    public void addDepartment(String name) {
        departments.add(new Department(name));  // created inside University
    }

    // Aggregation
    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);   // faculty exists independently
    }

    public void showUniversity() {
        System.out.println("\nUniversity: " + universityName);

        System.out.println("Departments:");
        for (Department d : departments) {
            d.showDepartment();
        }

        System.out.println("Faculties:");
        for (Faculty f : faculties) {
            f.showFaculty();
        }
    }
}
