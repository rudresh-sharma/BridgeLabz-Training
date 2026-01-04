package com.encapsulation.employeemanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class EMSMain {

    static ArrayList<Employee> emps = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\nEMS Menu");
            System.out.println("1. Create Employee");
            System.out.println("2. View Employee");
            System.out.println("0. Exit");

            choice = in.nextInt();
            in.nextLine();   // clear buffer

            if (choice == 1) {

                System.out.println("Types of Employee:");
                System.out.println("1. FullTime Employee");
                System.out.println("2. PartTime Employee");

                int empType = in.nextInt();
                in.nextLine();   // clear buffer

                System.out.print("Enter Employee Name: ");
                String name = in.nextLine();

                System.out.print("Enter Employee Id: ");
                String empId = in.nextLine();

                System.out.print("Enter Employee baseSalary: ");
                double baseSalary = in.nextDouble();
                in.nextLine();   // clear buffer

                System.out.print("Enter Department: ");
                String dept = in.nextLine();

                Employee tempEmp;

                if (empType == 1) {
                    tempEmp = new FullTimeEmployee(name, empId, baseSalary, dept);
                } else {
                    tempEmp = new PartTimeEmployee(name, empId, baseSalary, dept);
                }

                emps.add(tempEmp);
                System.out.println("✅ Employee Added Successfully");
            }

            else if (choice == 2) {
                System.out.print("Enter employee ID: ");
                String empId = in.nextLine();

                boolean found = false;

                for (Employee emp : emps) {
                    if (emp.getEmployeeId().equals(empId)) {
                        emp.displayDetails();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("❌ Incorrect employee Id.");
                }
            }

        } while (choice != 0);

        System.out.println("System Closed");
    }
}
