package com.generics.universitycoursemanagementsystem;

public class ResearchCourse extends CourseType {

    public ResearchCourse() {
        super("Research Paper & Presentation");
    }

    @Override
    public String getEvaluationType() {
        return "Research-Based";
    }
}