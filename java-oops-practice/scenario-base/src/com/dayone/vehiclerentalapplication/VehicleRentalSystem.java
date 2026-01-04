package com.dayone.vehiclerentalapplication;

import java.util.*;

/**
 * Main class for Vehicle Rental System.
 * Provides a menu-driven interface for renting bikes, cars, and trucks.
 * Demonstrates OOP concepts:
 *  - Inheritance (Bike, Car, Truck extend Vehicle)
 *  - Polymorphism (calculateRent)
 *  - Encapsulation (private fields)
 *  - Abstraction (Rentable interface)
 */
public class VehicleRentalSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lists to store available vehicles and customers
        List<Vehicle> vehicles = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        // Predefined Vehicles added to the system
        vehicles.add(new Bike("B001", "Honda CB Shine", 500));
        vehicles.add(new Bike("B002", "TVS Apache", 600));
        vehicles.add(new Car("C001", "Maruti Swift", 1500));
        vehicles.add(new Car("C002", "Hyundai i20", 1800));
        vehicles.add(new Truck("T001", "Tata Ace", 3000));
        vehicles.add(new Truck("T002", "Ashok Leyland", 4000));

        // Main loop for menu-driven interaction
        while (true) {
            System.out.println("\n===== VEHICLE RENTAL SYSTEM =====");
            System.out.println("1. Show Available Vehicles");
            System.out.println("2. Rent a Vehicle");
            System.out.println("3. Show All Customers & Invoices");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {

                case 1:
                    // Display all vehicles
                    System.out.println("\n--- Available Vehicles ---");
                    for (int i = 0; i < vehicles.size(); i++) {
                        System.out.println((i + 1) + ". " + vehicles.get(i).getModel() +
                                " (" + vehicles.get(i).getClass().getSimpleName() + ")");
                    }
                    break;

                case 2:
                    // Rent a vehicle
                    System.out.print("Enter Customer ID: ");
                    String custId = sc.nextLine();

                    System.out.print("Enter Customer Name: ");
                    String custName = sc.nextLine();

                    // Show vehicle options
                    System.out.println("Select Vehicle to Rent (Enter number): ");
                    for (int i = 0; i < vehicles.size(); i++) {
                        System.out.println((i + 1) + ". " + vehicles.get(i).getModel() +
                                " (" + vehicles.get(i).getClass().getSimpleName() + ")");
                    }

                    int vChoice = sc.nextInt();
                    sc.nextLine();

                    // Validate choice
                    if (vChoice < 1 || vChoice > vehicles.size()) {
                        System.out.println("Invalid choice!");
                        break;
                    }

                    Vehicle selectedVehicle = vehicles.get(vChoice - 1);

                    System.out.print("Enter number of days to rent: ");
                    int days = sc.nextInt();
                    sc.nextLine();

                    // Create new Customer object
                    Customer customer = new Customer(custId, custName, selectedVehicle, days);
                    customers.add(customer);

                    System.out.println("Vehicle rented successfully!");
                    break;

                case 3:
                    // Display all customer invoices
                    if (customers.isEmpty()) {
                        System.out.println("No rentals yet!");
                    } else {
                        for (Customer c : customers) {
                            c.displayInvoice();
                        }
                    }
                    break;

                case 4:
                    // Exit system
                    System.out.println("Exiting system. Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
