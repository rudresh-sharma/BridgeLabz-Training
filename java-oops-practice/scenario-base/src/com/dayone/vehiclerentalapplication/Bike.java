package com.dayone.vehiclerentalapplication;

public class Bike extends Vehicle {

    public Bike(String vehicleId, String model, double ratePerDay) {
        super(vehicleId, model, ratePerDay);
    }

    @Override
    public double calculateRent(int days) {
        double rent = ratePerDay * days;
        if (days > 5) {
            rent -= rent * 0.05; // 5% discount
        }
        return rent;
    }
}
