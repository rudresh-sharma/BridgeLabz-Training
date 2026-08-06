package com.completeannotationdisms;



import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.completeannotationdisms.config.AppConfig;
import com.completeannotationdisms.model.Student;
import com.completeannotationdisms.service.StudentService;
import com.completeannotationdisms.service.StudentServiceConstructor;
import com.completeannotationdisms.service.StudentServiceField;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void showMenu() {

        System.out.println("\n====================================");
        System.out.println("   Student Management System");
        System.out.println("====================================");
        System.out.println("1. Setter Injection");
        System.out.println("2. Constructor Injection");
        System.out.println("3. Field Injection");
        System.out.println("4. Exit");
        System.out.print("Enter Choice: ");
    }

    public static void main(String[] args) {

        // Create Spring Container using Java Configuration
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Get Beans
        StudentService studentService =
                context.getBean("studentServiceImpl", StudentService.class);

        StudentServiceConstructor constructorService =
                context.getBean("studentServiceConstructor",
                        StudentServiceConstructor.class);

        StudentServiceField fieldService =
                context.getBean("studentServiceField",
                        StudentServiceField.class);

        while (true) {

            showMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 4) {
                System.out.println("\nApplication Closed.");
                break;
            }

            System.out.println("\nEnter Student Details");

            System.out.print("First Name : ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name  : ");
            String lastName = scanner.nextLine();

            System.out.print("Email      : ");
            String email = scanner.nextLine();

            System.out.print("Course     : ");
            String course = scanner.nextLine();

            Student student =
                    new Student(firstName, lastName, email, course);

            switch (choice) {

                case 1 -> {
                    System.out.println("\nUsing Setter Injection");
                    studentService.addStudent(student);
                }

                case 2 -> {
                    System.out.println("\nUsing Constructor Injection");
                    constructorService.addStudent(student);
                }

                case 3 -> {
                    System.out.println("\nUsing Field Injection");
                    fieldService.addStudent(student);
                }

                default -> System.out.println("Invalid Choice!");
            }
        }

        scanner.close();
    }
}