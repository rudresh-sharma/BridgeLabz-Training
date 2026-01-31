package com.functionalinterfaces.implementinginterfaces.multivehiclerentalsystem;

import java.util.Scanner;

public class RentalSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Vehicle vehicle = null;
        boolean exit = false;

        while (!exit) {

            System.out.println("\n===== Multi-Vehicle Rental System =====");
            System.out.println("1. Select Car");
            System.out.println("2. Select Bike");
            System.out.println("3. Select Bus");
            System.out.println("4. Rent Vehicle");
            System.out.println("5. Return Vehicle");
            System.out.println("6. View Rental Amount");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            try {
                switch (choice) {

                    case 1:
                        vehicle = new Car();
                        System.out.println("Car selected.");
                        break;

                    case 2:
                        vehicle = new Bike();
                        System.out.println("Bike selected.");
                        break;

                    case 3:
                        vehicle = new Bus();
                        System.out.println("Bus selected.");
                        break;

                    case 4:
                        if (vehicle == null) {
                            System.out.println("Please select a vehicle first.");
                            break;
                        }
                        System.out.print("Enter number of rental days: ");
                        int days = sc.nextInt();
                        vehicle.rent(days);
                        break;

                    case 5:
                        if (vehicle == null) {
                            System.out.println("No vehicle selected.");
                            break;
                        }
                        vehicle.returnVehicle();
                        break;

                    case 6:
                        if (vehicle == null) {
                            System.out.println("No vehicle selected.");
                            break;
                        }
                        System.out.println("Total Rent: ₹" + vehicle.getRentalAmount());
                        break;

                    case 7:
                        exit = true;
                        System.out.println("Thank you for using Rental System!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (RentalException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
