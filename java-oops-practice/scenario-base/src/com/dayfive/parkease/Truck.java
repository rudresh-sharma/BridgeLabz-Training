package com.dayfive.parkease;
public class Truck extends Vehicle {

    public Truck(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public double getBaseRate() {
        return 100;
    }

    @Override
    public double getPenalty() {
        return 200;
    }
}
