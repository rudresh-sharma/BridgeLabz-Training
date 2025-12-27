package com.constructors.levelone;

import java.util.Scanner;

public class CarRental {

    private String customerName;
    private String carModel;
    private int rentalDays;

    static Scanner in = new Scanner(System.in);

    // Constructor to initialize rental details
    public CarRental(String customerName, String carModel, int rentalDays) {
        this.customerName = customerName;
        this.carModel = carModel;
        this.rentalDays = rentalDays;
    }

    // Method to calculate total cost
    public int calculateTotalCost() {
        switch (carModel) {
            case "Tiago":
                return 100 * rentalDays;

            case "Alto":
                return 50 * rentalDays;

            case "XUV700":
                return 1000 * rentalDays;

            default:
                return 0;
        }
    }

    // Display rental details
    public void display() {
        System.out.println("\nCustomer: " + customerName);
        System.out.println("Car Model: " + carModel);
        System.out.println("Rental Days: " + rentalDays);
        System.out.println("Total Cost: ₹" + calculateTotalCost());
    }

   
}
