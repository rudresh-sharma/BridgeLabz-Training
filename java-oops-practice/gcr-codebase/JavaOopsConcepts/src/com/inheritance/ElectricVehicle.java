package com.inheritance;

// ElectricVehicle IS-A Vehicle
public class ElectricVehicle extends VehicleVMS {

    private int batteryCapacity; // in kWh

    public ElectricVehicle(int maxSpeed, String model, int batteryCapacity) {
        super(maxSpeed, model);
        this.batteryCapacity = batteryCapacity;
    }

    public void charge() {
        System.out.println(model + " is charging. Battery: " + batteryCapacity + " kWh");
    }
}
