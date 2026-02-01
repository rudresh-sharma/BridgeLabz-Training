package com.collectors.studentgrouping;

public class Student {
    private String name;
    private String grade;  // A, B, C, D, F

    // constructor
    public Student(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    // good string representation
    @Override
    public String toString() {
        return name + " (" + grade + ")";
    }
}