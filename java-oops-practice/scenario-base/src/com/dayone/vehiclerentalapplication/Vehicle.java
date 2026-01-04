package com.dayone.vehiclerentalapplication;

public abstract class Vehicle implements Rentable {
    protected String vehicleId;
    protected String model;
    protected double ratePerDay;

    public Vehicle(String vehicleId, String model, double ratePerDay) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.ratePerDay = ratePerDay;
    }

    // Getters
    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }

    public void setRatePerDay(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    public void displayDetails() {
        System.out.println("Vehicle ID   : " + vehicleId);
        System.out.println("Model        : " + model);
        System.out.println("Rate per Day : " + ratePerDay);
    }

    // Abstract method implemented in subclasses
    public abstract double calculateRent(int days);
}
