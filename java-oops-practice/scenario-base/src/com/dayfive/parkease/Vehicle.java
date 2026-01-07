package com.dayfive.parkease;
public abstract class Vehicle {
    protected String vehicleNumber;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public abstract double getBaseRate();
    public abstract double getPenalty();
}
