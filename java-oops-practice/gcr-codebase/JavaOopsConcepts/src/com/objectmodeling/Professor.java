package com.objectmodeling;

import java.util.ArrayList;

public class Professor {
    private String name;
    private ArrayList<Course> courses;

    public Professor(String name) {
        this.name = name;
        courses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // Communication
    public void assignProfessor(Course course) {
        courses.add(course);
        course.setProfessor(this);
        System.out.println(name + " is assigned to " + course.getCourseName());
    }
}

