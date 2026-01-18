package com.generics.universitycoursemanagementsystem;

import java.util.List;

public class CourseUtil {

    public static void displayCourses(List<? extends CourseType> courseTypes) {
        for (CourseType type : courseTypes) {
            System.out.println(type.getEvaluationType() + " → " + type);
        }
    }
}