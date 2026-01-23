package com.daynine.binarysearchtree.universitysdigitalrecordsystem;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Student root = null;
        int choice;

        do {
            System.out.println("\nWelcome to Digi Record System");
            System.out.println("1. Insertion");
            System.out.println("2. Deletion");
            System.out.println("3. Search & Display (Sorted by Roll No)");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {

                case 1:
                    System.out.println("\nEnter Student Details");
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Roll No: ");
                    String enrollNo = sc.nextLine();

                    root = TreeConstruction.insertInToBST(root, new Student(name, enrollNo));
                    System.out.println("Data Added Successfully!");
                    break;

                case 2:
                    System.out.print("\nEnter Roll No to Delete: ");
                    String delEnroll = sc.nextLine();

                    root = TreeConstruction.deleteNode(root, delEnroll);
                    System.out.println("Delete Operation Completed!");
                    break;

                case 3:
                    System.out.println("\nSorted Student List (by Roll No):");
                    System.out.println("-------------------------------------------");
                    System.out.printf("%-20s %-20s%n", "Name", "Roll No");
                    System.out.println("-------------------------------------------");

                    TreeConstruction.display(root);
                    break;

                case 0:
                    System.out.println("Thank You! Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
