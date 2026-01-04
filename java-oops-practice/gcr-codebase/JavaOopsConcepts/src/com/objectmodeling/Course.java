package com.objectmodeling;


import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<Student> students;
    private Professor professor;   // ADD this

    public Course(String courseName) {
        this.courseName = courseName;
        students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    // ADD this
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    // ADD this
    public Professor getProfessor() {
        return professor;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void showStudents() {
        System.out.println("\nStudents in " + courseName + ":");
        for (Student s : students) {
            System.out.println("- " + s.getName());
        }
    }
}
