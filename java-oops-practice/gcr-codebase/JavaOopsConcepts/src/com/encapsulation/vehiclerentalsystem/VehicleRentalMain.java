package com.encapsulation.vehiclerentalsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class VehicleRentalMain { 

    static Scanner in = new Scanner(System.in);
    static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\nVehicle Rental System Menu");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicle Details");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            choice = in.nextInt();
            in.nextLine(); // clear buffer

            if (choice == 1) {
                addVehicle();
            } else if (choice == 2) {
                viewVehicleDetails();
            }

        } while (choice != 0);

        System.out.println("System Closed");
    }

    // ---------------- Add Vehicle ----------------
    public static void addVehicle() {
        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Car");
        System.out.println("2. Bike");
        System.out.println("3. Truck");

        int type = in.nextInt();
        in.nextLine();

        System.out.print("Enter Vehicle Number: ");
        String number = in.nextLine();

        System.out.print("Enter Rental Rate per Day: ");
        double rate = in.nextDouble();
        in.nextLine();

        System.out.print("Enter Insurance Policy Number: ");
        String policy = in.nextLine();

        Vehicle v;

        switch (type) {
            case 1:
                v = new Car(number, rate, policy);
                break;
            case 2:
                v = new Bike(number, rate, policy);
                break;
            case 3:
                v = new Truck(number, rate, policy);
                break;
            default:
                System.out.println("❌ Invalid vehicle type");
                return;
        }

        vehicles.add(v);
        System.out.println("✅ Vehicle Added Successfully!");
    }

    // ---------------- View Vehicle ----------------
    public static void viewVehicleDetails() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        System.out.print("Enter Vehicle Number to view: ");
        String number = in.nextLine();

        boolean found = false;

        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equals(number)) {
                System.out.println("\nVehicle Type: " + v.getType());

                System.out.print("Enter number of rental days: ");
                int days = in.nextInt();
                in.nextLine();

                double rent = v.calculateRentalCost(days);
                System.out.println("Rental Cost for " + days + " days: " + rent);

                if (v instanceof Insurable) {
                    Insurable ins = (Insurable) v;
                    double insurance = ins.calculateInsurance();
                    System.out.println(ins.getInsuranceDetails());
                }

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Vehicle not found");
        }
    }
}
