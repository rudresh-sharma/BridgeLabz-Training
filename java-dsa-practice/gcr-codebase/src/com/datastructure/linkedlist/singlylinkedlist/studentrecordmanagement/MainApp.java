package com.datastructure.linkedlist.singlylinkedlist.studentrecordmanagement;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList list = new StudentLinkedList();
        int choice;

        do {
            System.out.println("\n--- Student Record System ---");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Position");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Update Grade");
            System.out.println("7. Display All");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Roll No: ");
                    int r = sc.nextInt();
                    System.out.print("Name: ");
                    String n = sc.next();
                    System.out.print("Age: ");
                    int a = sc.nextInt();
                    System.out.print("Grade: ");
                    String g = sc.next();

                    Student s = new Student(r, n, a, g);

                    if (choice == 1)
                        list.addAtBeginning(s);
                    else if (choice == 2)
                        list.addAtEnd(s);
                    else {
                        System.out.print("Position: ");
                        int p = sc.nextInt();
                        list.addAtPosition(s, p);
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    list.delete(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Roll No to search: ");
                    list.search(sc.nextInt());
                    break;

                case 6:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    System.out.print("New Grade: ");
                    String grade = sc.next();
                    list.updateGrade(roll, grade);
                    break;

                case 7:
                    list.displayAll();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 8);

        sc.close();
    }
}
