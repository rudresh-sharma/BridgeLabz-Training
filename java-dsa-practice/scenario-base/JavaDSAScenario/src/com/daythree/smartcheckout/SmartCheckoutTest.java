package com.daythree.smartcheckout;
import java.util.Scanner;

public class SmartCheckoutTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Supermarket sm = new Supermarket();

        // Add store items
        sm.addItem("Milk", 50, 20);
        sm.addItem("Bread", 40, 15);
        sm.addItem("Eggs", 6, 100);
        sm.addItem("Rice", 60, 25);

        while (true) {
            System.out.println("\n--- Smart Checkout System ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Process Customer");
            System.out.println("3. Show Stock");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter customer name: ");
                String name = sc.next();

                Customer c = new Customer(name);

                while (true) {
                    System.out.print("Enter item name (or 'done'): ");
                    String item = sc.next();
                    if (item.equalsIgnoreCase("done")) break;

                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();

                    c.addItem(item, qty);
                }

                sm.addCustomer(c);
            }

            else if (choice == 2) {
                sm.processCustomer();
            }

            else if (choice == 3) {
                sm.showStock();
            }

            else if (choice == 4) {
                System.out.println("System Closed.");
                break;
            }
        }
    }
}
