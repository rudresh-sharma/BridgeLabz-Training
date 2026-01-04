package com.encapsulation.vehiclerentalsystem;

public class Bike extends Vehicle implements Insurable {

    private double insuranceAmount;
    private String policyNumber; // Encapsulated

    public Bike(String vehicleNumber, double rentalRate, String policyNumber) {
        super(vehicleNumber, "Bike", rentalRate);
        this.policyNumber = policyNumber;
    }

    // Rental cost: simple calculation per day
    @Override
    public double calculateRentalCost(int days) {
        return getRentalRate() * days;
    }

    // Insurance calculation for bike (e.g., 5% of rental rate)
    @Override
    public double calculateInsurance() {
        insuranceAmount = getRentalRate() * 0.05;
        return insuranceAmount;
    }

    // Return insurance details
    @Override
    public String getInsuranceDetails() {
        return "Bike Insurance: " + insuranceAmount + " | Policy: " + getMaskedPolicy();
    }

    // Encapsulation: only expose masked policy number
    public String getMaskedPolicy() {
        if (policyNumber.length() <= 4) return "XXXX-" + policyNumber;
        return "XXXX-" + policyNumber.substring(policyNumber.length() - 4);
    }
}
