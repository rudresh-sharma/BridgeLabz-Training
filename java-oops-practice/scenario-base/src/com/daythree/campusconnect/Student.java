package com.daythree.campusconnect;

import java.util.HashMap;

public class Student extends Person {

    private HashMap<Course, Integer> grades = new HashMap<>();

    public Student(String name, String email, String id) {
        super(name, email, id);
    }

    public void addGrade(Course c, int marks) {
        grades.put(c, marks);
    }

    public double calculateGPA() {
        int total = 0;
        for (int g : grades.values()) {
            total += g;
        }
        return grades.size() == 0 ? 0 : (double) total / grades.size();
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("GPA: " + calculateGPA());
    }
}
