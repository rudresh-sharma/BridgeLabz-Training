package com.day2.medInventory;

import java.util.Scanner;

public class MedInventoryDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MedInventoryManager<Item> manager = new MedInventoryManager<>();

        int choice = -1;

        System.out.println("🏥 Welcome to MedInventory System");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️⃣ Process Inventory CSV File");
            System.out.println("2️⃣ Display Categorized Inventory");
            System.out.println("0️⃣ Exit");

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
                    manager.processInventoryFile(path);
                    break;

                case 2:
                    manager.displayByCategory();
                    break;

                case 0:
                    System.out.println("👋 Exiting MedInventory. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }

        sc.close();
    }
}
