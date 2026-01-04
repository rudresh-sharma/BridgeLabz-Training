package com.inheritance;

public class VehicleVMS {

    protected int maxSpeed;
    protected String model;

    public VehicleVMS(int maxSpeed, String model) {
        this.maxSpeed = maxSpeed;
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("Model : " + model);
        System.out.println("Max Speed : " + maxSpeed + " km/h");
    }
}
