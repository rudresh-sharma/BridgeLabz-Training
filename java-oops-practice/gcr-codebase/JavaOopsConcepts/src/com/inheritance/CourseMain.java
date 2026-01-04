package com.inheritance;

public class CourseMain {

    public static void main(String[] args) {

        Course course = new Course("Java Basics", 4);

        Course online = new OnlineCourse("OOP in Java", 6, "Udemy", true);

        Course paid = new PaidOnlineCourse("Advanced Java", 8, "Coursera", true, 5000, 20);

        printCourse(course);
        System.out.println();

        printCourse(online);
        System.out.println();

        printCourse(paid);
    }

    // Polymorphism
    public static void printCourse(Course c) {
        c.displayDetails();
    }
}
