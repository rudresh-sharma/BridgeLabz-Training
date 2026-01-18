package com.generics.universitycoursemanagementsystem;

public class ExamCourse extends CourseType {

    public ExamCourse() {
        super("Written Examination");
    }

    @Override
    public String getEvaluationType() {
        return "Exam-Based";
    }
}