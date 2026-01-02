package com.encapsulation.ecommerceplatform;

import java.util.ArrayList;
import java.util.Scanner;

public class EcommerceMain {

    static ArrayList<Product> products = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\nE-Commerce Menu");
            System.out.println("1. Add Product");
            System.out.println("2. View Product (Final Price)");
            System.out.println("0. Exit");

            choice = in.nextInt();
            in.nextLine();

            if (choice == 1) {
                System.out.println("1. Electronics");
                System.out.println("2. Clothing");
                System.out.println("3. Groceries");
                int type = in.nextInt();
                in.nextLine();

                System.out.print("Enter Product ID: ");
                String id = in.nextLine();

                System.out.print("Enter Name: ");
                String name = in.nextLine();

                System.out.print("Enter Price: ");
                double price = in.nextDouble();
                in.nextLine();

                Product p;

                if (type == 1) {
                    p = new Electronics(id, name, price);
                } else if (type == 2) {
                    p = new Clothing(id, name, price);
                } else {
                    p = new Groceries(id, name, price);
                }

                products.add(p);
                System.out.println("✅ Product Added");
            }

            else if (choice == 2) {
                System.out.print("Enter Product ID: ");
                String id = in.nextLine();

                boolean found = false;

                for (Product p : products) {
                    if (p.getProductId().equals(id)) {
                        printFinalPrice(p);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("❌ Product not found");
                }
            }

        } while (choice != 0);

        System.out.println("System Closed");
    }

    // Polymorphic method
    public static void printFinalPrice(Product p) {

        double price = p.getPrice();
        double discount = p.calculateDiscount();
        double tax = 0;

        if (p instanceof Taxable) {
            tax = ((Taxable) p).calculateTax();
        }

        double finalPrice = price + tax - discount;

        p.showProductDetails();
        System.out.println("Discount : " + discount);
        System.out.println("Tax      : " + tax);
        System.out.println("Final Price : " + finalPrice);
    }
}
