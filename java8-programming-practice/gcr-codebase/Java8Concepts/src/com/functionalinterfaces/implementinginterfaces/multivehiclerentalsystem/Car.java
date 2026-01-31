package com.functionalinterfaces.implementinginterfaces.multivehiclerentalsystem;
public class Car extends AbstractVehicle {

    private static final double RATE_PER_DAY = 1500;

    @Override
    public void rent(int days) {
        validateRent(days);
        rentalAmount = days * RATE_PER_DAY;
        rented = true;
        System.out.println("Car rented for " + days + " days. Amount: ₹" + rentalAmount);
    }
}
