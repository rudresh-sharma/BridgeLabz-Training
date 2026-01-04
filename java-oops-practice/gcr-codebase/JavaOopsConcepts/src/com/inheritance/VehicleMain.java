package com.inheritance;

public class VehicleMain {

    public static void main(String[] args) {

        Vehicle[] vehicles = new Vehicle[3];

        vehicles[0] = new Car(180, "Petrol", "Swift", "Maruti", 5);
        vehicles[1] = new Truck(120, "Diesel", "Heavy Duty", "Tata");
        vehicles[2] = new Motorcycle(140, "Petrol", "Yamaha", "R15");

        // Polymorphism in action
        for (Vehicle v : vehicles) {
            v.displayInfo();
            System.out.println("----------------------");
        }
    }
}
