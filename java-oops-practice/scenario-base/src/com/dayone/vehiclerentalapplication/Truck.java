package com.dayone.vehiclerentalapplication;

public class Truck extends Vehicle {

    public Truck(String vehicleId, String model, double ratePerDay) {
        super(vehicleId, model, ratePerDay);
    }

    @Override
    public double calculateRent(int days) {
        double rent = ratePerDay * days + 500; // fixed surcharge
        return rent;
    }
}
