package com.dayfive.parkease;
public class Car extends Vehicle {

    public Car(String vehicleNumber) {
        super(vehicleNumber);
    }

    @Override
    public double getBaseRate() {
        return 50;   // ₹50 per hour
    }

    @Override
    public double getPenalty() {
        return 100;  // ₹100 overtime
    }
}
