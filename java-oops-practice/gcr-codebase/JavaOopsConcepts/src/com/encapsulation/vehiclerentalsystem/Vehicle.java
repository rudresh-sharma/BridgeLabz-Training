package com.encapsulation.vehiclerentalsystem;

public abstract class Vehicle {

    private String vehicleNumber;
    private String type;
    private double rentalRate;

    // Constructor
    public Vehicle(String number, String type, double rate) {
        this.vehicleNumber = number;
        this.type = type;
        this.rentalRate = rate;
    }

    // Abstract method (Polymorphism)
    public abstract double calculateRentalCost(int days);

    // -------- Encapsulation (Getters & Setters) --------

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        if (rentalRate > 0) {
            this.rentalRate = rentalRate;
        }
    }
}
