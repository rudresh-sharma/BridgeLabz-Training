package com.generics.smartwarehousemanagementsystem;

import java.util.Scanner;

public class WarehouseMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Storage<Electronics> electronicsStorage = new Storage<>();
        Storage<Groceries> groceryStorage = new Storage<>();
        Storage<Furniture> furnitureStorage = new Storage<>();

        boolean exit = false;

        while(!exit) {
            System.out.println("\n--- Smart Warehouse Menu ---");
            System.out.println("1. Add Electronics");
            System.out.println("2. Add Groceries");
            System.out.println("3. Add Furniture");
            System.out.println("4. Display Electronics");
            System.out.println("5. Display Groceries");
            System.out.println("6. Display Furniture");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch(choice) {
                case 1:
                    System.out.print("Enter Electronics name: ");
                    String eName = sc.nextLine();
                    electronicsStorage.addItem(new Electronics(eName));
                    break;
                case 2:
                    System.out.print("Enter Grocery name: ");
                    String gName = sc.nextLine();
                    groceryStorage.addItem(new Groceries(gName));
                    break;
                case 3:
                    System.out.print("Enter Furniture name: ");
                    String fName = sc.nextLine();
                    furnitureStorage.addItem(new Furniture(fName));
                    break;
                case 4:
                    System.out.println("\n--- Electronics ---");
                    WarehouseUtil.displayItems(electronicsStorage.getItems());
                    break;
                case 5:
                    System.out.println("\n--- Groceries ---");
                    WarehouseUtil.displayItems(groceryStorage.getItems());
                    break;
                case 6:
                    System.out.println("\n--- Furniture ---");
                    WarehouseUtil.displayItems(furnitureStorage.getItems());
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting Warehouse System...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
