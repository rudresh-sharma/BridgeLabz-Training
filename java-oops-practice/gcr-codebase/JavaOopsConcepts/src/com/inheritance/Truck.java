package com.inheritance;

public class Truck extends Vehicle {

    private String truckType;
    private String company;

    public Truck(int maxSpeed, String fuelType, String truckType, String company) {
        super(maxSpeed, fuelType);
        this.truckType = truckType;
        this.company = company;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Truck Type : " + truckType);
        System.out.println("Company : " + company);
    }
}
