package com.inheritance;

public class VehicleMainVMS {

    public static void main(String[] args) {

        VehicleVMS v1 = new ElectricVehicle(150, "Tesla Model 3", 75);
        VehicleVMS v2 = new PetrolVehicle(180, "Honda City", 40);

        v1.displayInfo();
        ((ElectricVehicle) v1).charge();

        System.out.println();

        v2.displayInfo();
        ((Refuelable) v2).refuel();   // Interface polymorphism
    }
}
