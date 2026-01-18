package com.generics.universitycoursemanagementsystem;

public class Course<T extends CourseType> {

    private String courseName;
    private String department;
    private T courseType;

    public Course(String courseName, String department, T courseType) {
        this.courseName = courseName;
        this.department = department;
        this.courseType = courseType;
    }

    public T getCourseType() {
        return courseType;
    }

    @Override
    public String toString() {
        return "Course Name: " + courseName +
               ", Department: " + department +
               ", Type: " + courseType.getEvaluationType() +
               ", " + courseType;
    }
}