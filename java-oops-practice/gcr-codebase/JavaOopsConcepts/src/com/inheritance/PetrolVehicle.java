package com.inheritance;

// PetrolVehicle IS-A Vehicle and also IS-A Refuelable
public class PetrolVehicle extends VehicleVMS implements Refuelable {

    private int fuelTankCapacity; // in liters

    public PetrolVehicle(int maxSpeed, String model, int fuelTankCapacity) {
        super(maxSpeed, model);
        this.fuelTankCapacity = fuelTankCapacity;
    }

    @Override
    public void refuel() {
        System.out.println(model + " is refueling. Tank capacity: " + fuelTankCapacity + " liters");
    }
}
