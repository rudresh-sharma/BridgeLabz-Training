package com.daytwo.traincompanion;

import java.util.Scanner;

public class TestTrain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Train train = new Train();
        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("    TRAIN COMPANION MENU");
            System.out.println("==============================");
            System.out.println("1. Add Compartment");
            System.out.println("2. Move to Next Compartment");
            System.out.println("3. Move to Previous Compartment");
            System.out.println("4. Remove Current Compartment");
            System.out.println("5. Show Adjacent Compartments");
            System.out.println("6. Display Train");
            System.out.println("0. Exit");
            System.out.println("==============================");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter compartment name: ");
                    String name = sc.nextLine();
                    train.addCompartment(name);
                    break;

                case 2:
                    train.moveNext();
                    break;

                case 3:
                    train.movePrev();
                    break;

                case 4:
                    train.removeCurrent();
                    break;

                case 5:
                    train.showAdjacent();
                    break;

                case 6:
                    train.displayTrain();
                    break;

                case 0:
                    System.out.println("Exiting Train Companion...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
