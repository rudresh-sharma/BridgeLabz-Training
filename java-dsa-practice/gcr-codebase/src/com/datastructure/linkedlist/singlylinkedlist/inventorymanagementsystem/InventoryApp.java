package com.datastructure.linkedlist.singlylinkedlist.inventorymanagementsystem;

import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryLinkedList inv = new InventoryLinkedList();
        int choice;

        do {
            System.out.println("\n--- Inventory System ---");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Remove Item");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display All");
            System.out.println("9. Total Inventory Value");
            System.out.println("10. Sort by Name");
            System.out.println("11. Sort by Price");
            System.out.println("12. Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Qty: ");
                    int qty = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();

                    Item item = new Item(id, name, qty, price);

                    if (choice == 1)
                        inv.addAtBeginning(item);
                    else if (choice == 2)
                        inv.addAtEnd(item);
                    else {
                        System.out.print("Position: ");
                        inv.addAtPosition(item, sc.nextInt());
                    }
                    break;

                case 4:
                    inv.removeById(sc.nextInt());
                    break;

                case 5:
                    System.out.print("ID & New Qty: ");
                    inv.updateQuantity(sc.nextInt(), sc.nextInt());
                    break;

                case 6:
                    inv.searchById(sc.nextInt());
                    break;

                case 7:
                    inv.searchByName(sc.next());
                    break;

                case 8:
                    inv.displayAll();
                    break;

                case 9:
                    inv.totalValue();
                    break;

                case 10:
                    inv.sortByName(true);
                    break;

                case 11:
                    inv.sortByPrice(true);
                    break;
            }

        } while (choice != 12);

        sc.close();
    }
}
