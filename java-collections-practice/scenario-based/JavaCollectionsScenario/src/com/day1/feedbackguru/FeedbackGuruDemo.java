package com.day1.feedbackguru;

import java.util.Scanner;

public class FeedbackGuruDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FeedbackGuruManager manager = new FeedbackGuruManager();
        int choice = -1;

        System.out.println(" Welcome to FeedbackGuru - Smart Survey Analyzer!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Process feedback files from folder");
            System.out.println("2️ Display categorized feedback");
            System.out.println("3️ Clear all processed data");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter folder path containing .txt feedback files: ");
                    String folder = sc.nextLine();

                    System.out.print("Enter feedback type (Service/Product/etc): ");
                    String type = sc.nextLine();

                    manager.processFeedbackFolder(folder, type);
                    break;

                case 2:
                    manager.displayCategorizedFeedback();
                    break;

                case 3:
                    manager.clearData();
                    System.out.println("🧹 All previous feedback data cleared.");
                    break;

                case 0:
                    System.out.println("👋 Exiting FeedbackGuru. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
