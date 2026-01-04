package com.dayone.university;

import java.util.*;

public abstract class Student {
    protected String studentId;
    protected String name;
    private double gpa; // Encapsulation

    protected List<Course> enrolledCourses = new ArrayList<>();

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
    }
    
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getGpa() { return gpa; }

    protected void updateGpa(double gpa) { this.gpa = gpa; }

    public void displayTranscript() {
        System.out.println("\nTranscript for " + name);
        for (Course c : enrolledCourses) {
            c.displayCourse();
        }
        System.out.println("GPA: " + gpa);
    }

    public abstract void displayInfo(); // Polymorphism
}
