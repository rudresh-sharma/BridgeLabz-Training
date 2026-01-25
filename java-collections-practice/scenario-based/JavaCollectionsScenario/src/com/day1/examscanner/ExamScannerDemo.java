package com.day1.examscanner;

import java.util.Scanner;

public class ExamScannerDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println(" Welcome to ExamScanner!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Process Math Answer Sheet");
            System.out.println("2️ Process Science Answer Sheet");
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
                    runScanner(sc, new MathAnswerSheet());
                    break;

                case 2:
                    runScanner(sc, new ScienceAnswerSheet());
                    break;

                case 0:
                    System.out.println("👋 Exiting ExamScanner. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }

        sc.close();
    }

    // 🔹 Generic runner method
    private static <T extends AnswerSheet> void runScanner(Scanner sc, T sheet) {

        ExamScannerManager<T> manager = new ExamScannerManager<>(sheet);

        System.out.print("Enter CSV file path: ");
        String path = sc.nextLine();

        manager.processAnswerFile(path);
        manager.displayRankList();
    }
}
