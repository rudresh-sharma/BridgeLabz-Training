package com.generics.universitycoursemanagementsystem;

public abstract class CourseType {

    protected String evaluationMethod;

    public CourseType(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public abstract String getEvaluationType();

    @Override
    public String toString() {
        return "Evaluation Method: " + evaluationMethod;
    }
}