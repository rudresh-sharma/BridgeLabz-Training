package com.vehicle.rentalsystem;

public class Truck extends Vehicle implements Insurable {

    private double insuranceAmount;
    private String policyNumber; // Encapsulated

    public Truck(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Truck", rentalRate);
        this.policyNumber = policyNumber;
    }

    // Rental cost: simple calculation per day
    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    // Insurance calculation for truck (e.g., 15% of rental rate)
    @Override
    public double calculateInsurance() {
        insuranceAmount = getRentalRate() * 0.15;
        return insuranceAmount;
    }

    // Return insurance details
    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance: " + insuranceAmount + " | Policy: " + getMaskedPolicy();
    }

    // Encapsulation: only expose masked policy number
    public String getMaskedPolicy() {
        if (policyNumber.length() <= 4) return "XXXX-" + policyNumber;
        return "XXXX-" + policyNumber.substring(policyNumber.length() - 4);
    }
}
