package com.generics.universitycoursemanagementsystem;

import java.util.*;

public class UniversityCourseManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Course<? extends CourseType>> courses = new ArrayList<>();
        List<CourseType> courseTypes = new ArrayList<>();

        System.out.print("Enter number of courses: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nCourse " + i);
            System.out.print("Course Name: ");
            String name = sc.nextLine();

            System.out.print("Department: ");
            String dept = sc.nextLine();

            System.out.println("Choose Course Type:");
            System.out.println("1. Exam Based");
            System.out.println("2. Assignment Based");
            System.out.println("3. Research Based");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                ExamCourse ec = new ExamCourse();
                courses.add(new Course<>(name, dept, ec));
                courseTypes.add(ec);
            } else if (choice == 2) {
                AssignmentCourse ac = new AssignmentCourse();
                courses.add(new Course<>(name, dept, ac));
                courseTypes.add(ac);
            } else if (choice == 3) {
                ResearchCourse rc = new ResearchCourse();
                courses.add(new Course<>(name, dept, rc));
                courseTypes.add(rc);
            } else {
                System.out.println("Invalid choice");
                i--;
            }
        }

        System.out.println("\n---- Course Details ----");
        for (Course<? extends CourseType> c : courses) {
            System.out.println(c);
        }

        System.out.println("\n---- Evaluation Methods ----");
        CourseUtil.displayCourses(courseTypes);

        sc.close();
    }
}