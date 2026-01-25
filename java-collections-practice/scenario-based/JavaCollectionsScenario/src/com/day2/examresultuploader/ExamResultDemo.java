package com.day2.examresultuploader;

import java.util.Scanner;

public class ExamResultDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExamResultManager<String> manager = new ExamResultManager<>();

        int choice = -1;

        System.out.println(" Welcome to ExamResultUploader");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Upload CSV Marks File");
            System.out.println("2️ Display All Marks");
            System.out.println("3️ Display Top Scorers per Subject");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter CSV file path: ");
                    String path = sc.nextLine();
                    manager.processCSV(path);
                    break;

                case 2:
                    manager.displayAll();
                    break;

                case 3:
                    manager.displayTopScorers();
                    break;

                case 0:
                    System.out.println("👋 Exiting ExamResultUploader. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }

        sc.close();
    }
}
