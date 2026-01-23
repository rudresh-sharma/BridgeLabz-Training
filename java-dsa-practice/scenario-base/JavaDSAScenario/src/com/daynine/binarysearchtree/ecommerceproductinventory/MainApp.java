package com.daynine.binarysearchtree.ecommerceproductinventory;

import java.util.Scanner;
public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Product root = null;
        int choice;

        do {
            System.out.println("\n=== E-Commerce Product Inventory ===");
            System.out.println("1. Insert Product");
            System.out.println("2. Lookup Product by SKU");
            System.out.println("3. Update Product Price");
            System.out.println("4. Display Sorted Product List (by SKU)");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter SKU: ");
                    String sku = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    root = InventoryTree.insert(root, new Product(name, sku, price));
                    System.out.println("Product inserted successfully!");
                    break;

                case 2:
                    System.out.print("Enter SKU to search: ");
                    String searchSku = sc.nextLine();

                    Product found = InventoryTree.search(root, searchSku);

                    if (found != null) {
                        System.out.println("Product Found:");
                        System.out.println("Name  : " + found.getName());
                        System.out.println("SKU   : " + found.getSku());
                        System.out.println("Price : " + found.getPrice());
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter SKU to update: ");
                    String updateSku = sc.nextLine();

                    System.out.print("Enter New Price: ");
                    double newPrice = sc.nextDouble();

                    boolean updated = InventoryTree.updatePrice(root, updateSku, newPrice);

                    if (updated) {
                        System.out.println("Price updated successfully!");
                    } else {
                        System.out.println("Product not found. Update failed.");
                    }
                    break;

                case 4:
                    System.out.println("\nSorted Product List (by SKU)");
                    System.out.println("-------------------------------------------");
                    System.out.printf("%-15s %-15s %-10s%n", "SKU", "Name", "Price");
                    System.out.println("-------------------------------------------");

                    InventoryTree.displaySorted(root);
                    break;

                case 0:
                    System.out.println("Exiting Inventory System...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
