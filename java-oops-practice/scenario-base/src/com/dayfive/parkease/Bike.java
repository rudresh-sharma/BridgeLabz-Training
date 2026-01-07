package com.dayfive.parkease;
public class Bike extends Vehicle {

    public Bike(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public double getBaseRate() {
        return 20;
    }

    @Override
    public double getPenalty() {
        return 50;
    }
}
