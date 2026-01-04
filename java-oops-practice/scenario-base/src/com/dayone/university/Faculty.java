package com.dayone.university;

public class Faculty {
    private String facultyId;
    private String name;

    public Faculty(String facultyId, String name) {
        this.facultyId = facultyId;
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("Faculty: " + name + " (" + facultyId + ")");
    }
}
