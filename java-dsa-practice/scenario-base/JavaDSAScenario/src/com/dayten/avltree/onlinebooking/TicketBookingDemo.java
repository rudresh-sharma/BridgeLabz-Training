package com.dayten.avltree.onlinebooking;

import java.util.Scanner;

public class TicketBookingDemo {

    public static void main(String[] args) {

        TicketBookingSystem system = new TicketBookingSystem();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println(" Welcome to Online Ticket Booking!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Insert Event");
            System.out.println("2️ Cancel Event");
            System.out.println("3️ Show Events in Order");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter start time (yyyy-MM-dd HH:mm): ");
                    String time = sc.nextLine();
                    system.addEvent(name, time);
                    break;

                case 2:
                    System.out.print("Enter event name to cancel: ");
                    String cancelName = sc.nextLine();
                    System.out.print("Enter start time (yyyy-MM-dd HH:mm): ");
                    String cancelTime = sc.nextLine();
                    system.cancelEvent(cancelName, cancelTime);
                    break;

                case 3:
                    system.showEvents();
                    break;

                case 0:
                    System.out.println("👋 Exiting Ticket Booking. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
