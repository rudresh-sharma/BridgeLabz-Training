package com.dayone.vehiclerentalapplication;

/**
 * Customer class represents a customer renting a vehicle.
 * It stores customer info and the vehicle they rented.
 */
public class Customer {
    // Private fields (Encapsulation)
    private String customerId;
    private String name;
    private Vehicle rentedVehicle; // HAS-A relationship
    private int rentDays;

    /**
     * Constructor to initialize customer details
     */
    public Customer(String customerId, String name, Vehicle rentedVehicle, int rentDays) {
        this.customerId = customerId;
        this.name = name;
        this.rentedVehicle = rentedVehicle;
        this.rentDays = rentDays;
    }

    // Getters and Setters could be added here if needed

    /**
     * Display rental invoice for the customer
     * Demonstrates polymorphism: calculateRent() differs for each Vehicle type
     */
    public void displayInvoice() {
        System.out.println("\n----- Customer Rental Invoice -----");
        System.out.println("Customer ID   : " + customerId);
        System.out.println("Customer Name : " + name);
        // Show rented vehicle details
        rentedVehicle.displayDetails();
        System.out.println("Rent Days     : " + rentDays);
        // Polymorphic call: Bike/Car/Truck calculateRent() called automatically
        System.out.println("Total Rent    : " + rentedVehicle.calculateRent(rentDays));
        System.out.println("----------------------------------\n");
    }
}
