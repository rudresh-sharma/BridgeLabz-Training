package com.inheritance;

public class Motorcycle extends Vehicle {

    private String company;
    private String model;

    public Motorcycle(int maxSpeed, String fuelType, String company, String model) {
        super(maxSpeed, fuelType);
        this.company = company;
        this.model = model;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Company : " + company);
        System.out.println("Model : " + model);
    }
}
