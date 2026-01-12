package com.daytwo.callcenter;

import java.util.Scanner;

public class TestCallCenter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CallCenter center = new CallCenter();

        int choice;

        do {
            System.out.println("\n===========================");
            System.out.println("      CALL CENTER MENU");
            System.out.println("===========================");
            System.out.println("1. Add Call");
            System.out.println("2. Process Next Call");
            System.out.println("3. Show Call Count");
            System.out.println("0. Exit");
            System.out.println("===========================");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Customer ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Is VIP? (true/false): ");
                    boolean vip = sc.nextBoolean();

                    Customer c = new Customer(id, name, vip);
                    center.addCall(c);
                    break;

                case 2:
                    center.processCall();
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    String cid = sc.next();
                    center.showCallCount(cid);
                    break;

                case 0:
                    System.out.println("Exiting Call Center...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
