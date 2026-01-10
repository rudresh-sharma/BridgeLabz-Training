package com.dayseven.skillforge;
import java.util.Scanner;

public class SkillForgeApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String sName = sc.nextLine();

        System.out.print("Enter student email: ");
        String sEmail = sc.nextLine();

        Student student = new Student(sName, sEmail);

        Instructor instructor = new Instructor("Mr. Raj", "raj@skillforge.com");

        Course course = new Course("Java Full Stack", "Mr. Raj");
        instructor.uploadCourse(course);

        System.out.println("\nCourse: " + course.getTitle());
        System.out.println("Rating: " + course.getRating());
        System.out.println("Reviews: " + course.getReviews());

        System.out.print("\nEnter % progress completed: ");
        double prog = sc.nextDouble();
        student.updateProgress(prog);

        System.out.print("Enter marks scored: ");
        double marks = sc.nextDouble();
        student.addMarks(marks);

        student.generateCertificate();

        sc.close();
    }
}
