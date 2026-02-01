package com.collectors.studentgrouping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentResultGroupingApp {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(new Student("Rohan", "A"));
        students.add(new Student("Priya", "B"));
        students.add(new Student("Amit", "A"));
        students.add(new Student("Sneha", "C"));
        students.add(new Student("Vikram", "B"));
        students.add(new Student("Neha", "A"));
        students.add(new Student("Rahul", "C"));
        students.add(new Student("Anjali", "B"));

        System.out.println("Student Results - Grouped by Grade");
        System.out.println("--------------------------------");

        Map<String, List<String>> groupedByGrade = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGrade,
                        Collectors.mapping(Student::getName, Collectors.toList())
                ));

        groupedByGrade.forEach((grade, names) -> {
            System.out.println("Grade " + grade + ": " + names);
        });

        System.out.println("--------------------------------");
        System.out.println("Total students: " + students.size());
    }
}