package com.encapsulation.ridehailing;

public class Bike extends Vehicle implements GPS {

    private String location = "Unknown";

    public Bike(String id, String driver) {
        super(id, driver, 8); // ₹8 per km
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm(); // No extra charge
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
