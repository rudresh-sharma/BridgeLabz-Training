package com.encapsulation.ridehailing;

public class Auto extends Vehicle implements GPS {

    private String location = "Unknown";

    public Auto(String id, String driver) {
        super(id, driver, 10); // ₹10 per km
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm() + 20; // Auto base charge
    }

    @Override
    public String getCurrentLocation() {
        return location;
    }

    @Override
    public void updateLocation(String newLocation) {
        location = newLocation;
    }
}
