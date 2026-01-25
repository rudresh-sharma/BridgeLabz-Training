package com.day1.resumeanalyzer;

import java.util.Scanner;

public class ResumeDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ResumeAnalyzer analyzer = new ResumeAnalyzer();
        int choice = -1;

        System.out.println(" Welcome to ResumeAnalyzer System!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Process resumes from folder");
            System.out.println("2️ Display all candidates sorted by keyword matches");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter folder path: ");
                    String folder = sc.nextLine();
                    analyzer.processFolder(folder);
                    break;

                case 2:
                    analyzer.displayAllCandidates();
                    break;

                case 0:
                    System.out.println("👋 Exiting ResumeAnalyzer. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
