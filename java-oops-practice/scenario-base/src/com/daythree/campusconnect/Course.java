package com.daythree.campusconnect;

import java.util.ArrayList;

public class Course implements ICourseActions {

    private String courseName;
    private Faculty faculty;
    private ArrayList<Student> students = new ArrayList<>();

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void assignFaculty(Faculty f) {
        this.faculty = f;
    }

    public void enrollCourse(Student s) {
        students.add(s);
        System.out.println(s.name + " enrolled in " + courseName);
    }

    public void dropCourse(Student s) {
        students.remove(s);
        System.out.println(s.name + " removed from " + courseName);
    }
}
