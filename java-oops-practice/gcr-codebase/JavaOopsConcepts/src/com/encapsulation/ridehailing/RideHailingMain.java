package com.encapsulation.ridehailing;

import java.util.ArrayList;

public class RideHailingMain {

    // Polymorphic Method
    public static void calculateRide(Vehicle v, double distance) {
        System.out.println("----------------------");
        v.getVehicleDetails();
        System.out.println("Distance: " + distance + " km");
        System.out.println("Fare: ₹" + v.calculateFare(distance));
    }

    public static void main(String[] args) {

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        Vehicle car = new Car("C101", "Rudresh");
        Vehicle bike = new Bike("B202", "Amit");
        Vehicle auto = new Auto("A303", "Ravi");

        vehicles.add(car);
        vehicles.add(bike);
        vehicles.add(auto);

        // GPS usage
        ((GPS)car).updateLocation("MG Road");
        ((GPS)bike).updateLocation("BTM");
        ((GPS)auto).updateLocation("Airport");

        for (Vehicle v : vehicles) {
            calculateRide(v, 10);   // polymorphism
        }
    }
}
