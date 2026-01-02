package com.encapsulation.ridehailing;

public class Car extends Vehicle implements GPS {

    private String location = "Unknown";

    public Car(String id, String driver) {
        super(id, driver, 15); // ₹15 per km
    }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm() + 50; // Car service charge
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
