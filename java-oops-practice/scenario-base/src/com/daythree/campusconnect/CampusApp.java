package com.daythree.campusconnect;

import java.util.ArrayList;
import java.util.Scanner;

public class CampusApp {

    static Scanner in = new Scanner(System.in);
    static ArrayList<Course> courses = new ArrayList<>();
    static Student student;

    public static void main(String[] args) {

        courses.add(new Course("Java"));
        courses.add(new Course("Python"));
        courses.add(new Course("Data Structures"));

        System.out.print("Enter Student Name: ");
        String name = in.nextLine();
        System.out.print("Enter Email: ");
        String email = in.nextLine();
        System.out.print("Enter ID: ");
        String id = in.nextLine();

        student = new Student(name, email, id);

        int choice;
        do {
            System.out.println("\nCampusConnect Menu");
            System.out.println("1. Enroll Course");
            System.out.println("2. Drop Course");
            System.out.println("3. Add Grade");
            System.out.println("4. View Student Details");
            System.out.println("5. Exit");

            choice = in.nextInt();

            switch (choice) {
                case 1 -> enroll();
                case 2 -> drop();
                case 3 -> addGrade();
                case 4 -> student.printDetails();
            }

        } while (choice != 5);
    }

    static void enroll() {
        Course c = chooseCourse();
        c.enrollCourse(student);
    }

    static void drop() {
        Course c = chooseCourse();
        c.dropCourse(student);
    }

    static void addGrade() {
        Course c = chooseCourse();
        System.out.print("Enter marks: ");
        int marks = in.nextInt();
        student.addGrade(c, marks);
    }

    static Course chooseCourse() {
        System.out.println("Available Courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
        }
        System.out.print("Choose: ");
        int ch = in.nextInt();
        return courses.get(ch - 1);
    }
}
