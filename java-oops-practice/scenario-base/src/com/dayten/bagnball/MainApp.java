package com.dayten.bagnball;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to BagNBall System");

        ManagerClass mg = new ManagerClass(5);
        Bag[] bags = mg.getBags();

        // Create 5 bags
        bags[0] = new Bag("B001", "Red", 10);
        bags[1] = new Bag("B002", "Blue", 8);
        bags[2] = new Bag("B003", "Green", 12);
        bags[3] = new Bag("B004", "Yellow", 6);
        bags[4] = new Bag("B005", "Black", 15);

        int choice;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add a ball in bag");
            System.out.println("2. Remove a ball from a bag");
            System.out.println("3. Display all balls in a bag");
            System.out.println("4. Display all bags and their ball count");
            System.out.println("0. Exit");

            System.out.print("Enter option: ");
            choice = in.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Bag Number (1-5): ");
                    int bagNumber = in.nextInt();

                    System.out.print("Enter Ball ID: ");
                    String ballId = in.next();

                    System.out.print("Enter Ball Color: ");
                    String color = in.next();

                    System.out.print("Enter Ball Size: ");
                    int size = in.nextInt();

                    Ball ball = new Ball(ballId, color, size);
                    mg.addAtBag(ball, bagNumber);
                    break;

                case 2:
                    System.out.print("Enter Bag Number (1-5): ");
                    int removeBagNo = in.nextInt();

                    System.out.print("Enter Ball ID to remove: ");
                    String removeId = in.next();

                    mg.removeFromBag(removeId, removeBagNo);
                    break;

                case 3:
                    System.out.print("Enter Bag Number (1-5): ");
                    int showBagNo = in.nextInt();

                    if (showBagNo >= 1 && showBagNo <= 5) {
                        bags[showBagNo - 1].displayBalls();
                    } else {
                        System.out.println("Invalid bag number");
                    }
                    break;

                case 4:
                    mg.displayBagSummary();
                    break;

                case 0:
                    System.out.println("Thank you for using BagNBall System!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 0);

        in.close();
    }
}
