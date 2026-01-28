package com.day1.markmate.model;

import java.util.Map;

public class Student {

    private String studentId;
    private String name;
    private Map<String, Integer> subjectMarks;
    private int total;
    private double average;
    private String grade;

    public Student(String studentId, String name, Map<String, Integer> subjectMarks) {
        this.studentId = studentId;
        this.name = name;
        this.subjectMarks = subjectMarks;
    }

    // Getters & Setters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public Map<String, Integer> getSubjectMarks() { return subjectMarks; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public double getAverage() { return average; }
    public void setAverage(double average) { this.average = average; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}
