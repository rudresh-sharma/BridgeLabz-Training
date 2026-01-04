package com.inheritance;

public class Car extends Vehicle {

    private String model;
    private String company;
    private int seatCapacity;

    public Car(int maxSpeed, String fuelType, String model, String company, int seatCapacity) {
        super(maxSpeed, fuelType);
        this.model = model;
        this.company = company;
        this.seatCapacity = seatCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Company : " + company);
        System.out.println("Model : " + model);
        System.out.println("Seat Capacity : " + seatCapacity);
    }
}
