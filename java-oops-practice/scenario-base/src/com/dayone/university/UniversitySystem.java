package com.dayone.university;

import java.util.*;

public class UniversitySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();

        // Sample courses
        courses.add(new Course("C101", "Mathematics", 3));
        courses.add(new Course("C102", "Physics", 4));
        courses.add(new Course("C103", "Computer Science", 3));

        while (true) {
            System.out.println("\n--- UNIVERSITY SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. Enroll in Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. Show Transcript");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String sid = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Enter Type (U=Undergrad, P=Postgrad): ");
                    String type = sc.nextLine().toUpperCase();

                    Student s = type.equals("U") ? new Undergraduate(sid, sname) : new Postgraduate(sid, sname);
                    students.add(s);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    // Enroll student in course
                    System.out.println("Select Student:");
                    for (int i = 0; i < students.size(); i++)
                        System.out.println((i+1) + ". " + students.get(i).studentId + " - " + students.get(i).name);
                    int sChoice = sc.nextInt(); sc.nextLine();
                    Student st = students.get(sChoice-1);

                    System.out.println("Select Course:");
                    for (int i = 0; i < courses.size(); i++)
                        System.out.println((i+1) + ". " + courses.get(i).getCourseName());
                    int cChoice = sc.nextInt(); sc.nextLine();
                    Course cr = courses.get(cChoice-1);

                    Enrollment e = new Enrollment(st, cr);
                    enrollments.add(e);
                    System.out.println("Enrollment successful!");
                    break;

                case 3:
                    // Assign grade
                    System.out.println("Select Enrollment:");
                    for (int i = 0; i < enrollments.size(); i++)
                    	System.out.println((i+1) + ". " + enrollments.get(i).getStudent().getStudentId() 
                                + " - " + enrollments.get(i).getCourse().getCourseName());
                    int eChoice = sc.nextInt(); sc.nextLine();
                    Enrollment en = enrollments.get(eChoice-1);

                    System.out.print("Enter Grade (A-F): ");
                    String grade = sc.nextLine();
                    en.assignGrade(grade);
                    System.out.println("Grade assigned!");
                    break;

                case 4:
                    // Show transcript
                    System.out.println("Select Student:");
                    for (int i = 0; i < students.size(); i++)
                        System.out.println((i+1) + ". " + students.get(i).studentId + " - " + students.get(i).name);
                    int tChoice = sc.nextInt(); sc.nextLine();
                    students.get(tChoice-1).displayTranscript();
                    break;

                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
