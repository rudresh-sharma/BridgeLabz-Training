package com.dayone.vehiclerentalapplication;

public class Car extends Vehicle {

    public Car(String vehicleId, String model, double ratePerDay) {
        super(vehicleId, model, ratePerDay);
    }

    @Override
    public double calculateRent(int days) {
        double rent = ratePerDay * days;
        rent += rent * 0.10; // 10% insurance surcharge
        return rent;
    }
}
