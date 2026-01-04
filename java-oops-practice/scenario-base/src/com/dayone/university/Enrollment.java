package com.dayone.university;

import java.util.*;

public class Enrollment implements Graded {
    private Student student;
    private Course course;
    private String grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        student.enrollCourse(course); // link student to course
    }

    // Getters and Setters
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getGrade() { return grade; }

    public void setStudent(Student student) { this.student = student; }
    public void setCourse(Course course) { this.course = course; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public void assignGrade(String grade) {
        this.grade = grade;
        // Minimal GPA calculation: A=4, B=3, C=2, D=1, F=0
        double point = switch (grade.toUpperCase()) {
            case "A" -> 4;
            case "B" -> 3;
            case "C" -> 2;
            case "D" -> 1;
            default -> 0;
        };
        // simple GPA update (average)
        double newGpa = (student.getGpa() * student.enrolledCourses.size() + point) / student.enrolledCourses.size();
        student.updateGpa(newGpa);
    }

    // Use getters instead of direct field access
    public void displayEnrollment() {
        System.out.println(student.getStudentId() + " enrolled in " + course.getCourseName() + " - Grade: " + grade);
    }
}
