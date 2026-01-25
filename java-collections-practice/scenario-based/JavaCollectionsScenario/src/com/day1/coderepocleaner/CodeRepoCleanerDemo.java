package com.day1.coderepocleaner;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CodeRepoCleanerDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CodeRepoCleanerManager manager = new CodeRepoCleanerManager();
        List<Path> scannedFiles = null;
        int choice = -1;

        System.out.println(" Welcome to CodeRepoCleaner!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Scan all .java files from folder");
            System.out.println("2️ Analyze and categorize files");
            System.out.println("3️ Display categorized summary");
            System.out.println("4️ Filter files by size");
            System.out.println("5️ Filter files by last modified date");
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
                    System.out.print("Enter root folder path: ");
                    String root = sc.nextLine();
                    scannedFiles = manager.scanJavaFiles(root);
                    break;

                case 2:
                    if (scannedFiles == null || scannedFiles.isEmpty()) {
                        System.out.println("⚠️ No files scanned yet. Run option 1 first.");
                    } else {
                        manager.analyzeAndCategorize(scannedFiles);
                    }
                    break;

                case 3:
                    manager.displaySummary();
                    break;

                case 4:
                    System.out.print("Enter minimum file size in bytes: ");
                    long size = Long.parseLong(sc.nextLine());
                    manager.filterBySize(size);
                    break;

                case 5:
                    System.out.print("Enter epoch seconds (e.g., 1700000000): ");
                    long epoch = Long.parseLong(sc.nextLine());
                    manager.filterByModifiedAfter(epoch);
                    break;

                case 0:
                    System.out.println("👋 Exiting CodeRepoCleaner. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
