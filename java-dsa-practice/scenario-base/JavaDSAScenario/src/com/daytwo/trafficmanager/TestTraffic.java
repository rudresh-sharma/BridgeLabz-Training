package com.daytwo.trafficmanager;

import java.util.Scanner;

public class TestTraffic {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Roundabout rb = new Roundabout();
        int choice;

        do {
            System.out.println("\n===============================");
            System.out.println("     TRAFFIC MANAGER MENU");
            System.out.println("===============================");
            System.out.println("1. Add Vehicle to Queue");
            System.out.println("2. Enter Roundabout");
            System.out.println("3. Exit Roundabout");
            System.out.println("4. Show Roundabout");
            System.out.println("5. Show Waiting Queue");
            System.out.println("0. Exit");
            System.out.println("===============================");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle Number: ");
                    String num = sc.nextLine();
                    rb.addToQueue(num);
                    break;

                case 2:
                    rb.enterRoundabout();
                    break;

                case 3:
                    rb.exitRoundabout();
                    break;

                case 4:
                    rb.displayRoundabout();
                    break;

                case 5:
                    rb.displayQueue();
                    break;

                case 0:
                    System.out.println("Exiting Traffic Manager...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
