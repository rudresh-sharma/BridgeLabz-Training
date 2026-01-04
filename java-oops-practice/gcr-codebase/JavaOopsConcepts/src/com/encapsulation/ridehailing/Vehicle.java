package com.encapsulation.ridehailing;

public abstract class Vehicle {

    // Encapsulated fields
    private String vehicleId;
    private String driverName;
    private double ratePerKm;

    public Vehicle(String id, String driver, double rate) {
        this.vehicleId = id;
        this.driverName = driver;
        this.ratePerKm = rate;
    }

    // Encapsulation (getters)
    public String getVehicleId() {
        return vehicleId;
    }

    public String getDriverName() {
        return driverName;
    }

    protected double getRatePerKm() {
        return ratePerKm;
    }

    // Concrete method
    public void getVehicleDetails() {
        System.out.println("Vehicle ID  : " + vehicleId);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Rate per Km: " + ratePerKm);
    }

    // Abstraction
    public abstract double calculateFare(double distance);
}
