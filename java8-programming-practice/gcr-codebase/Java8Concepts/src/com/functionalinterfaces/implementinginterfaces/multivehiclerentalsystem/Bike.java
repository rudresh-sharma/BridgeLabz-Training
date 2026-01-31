package com.functionalinterfaces.implementinginterfaces.multivehiclerentalsystem;
public class Bike extends AbstractVehicle {

    private static final double RATE_PER_DAY = 500;

    @Override
    public void rent(int days) {
        validateRent(days);
        rentalAmount = days * RATE_PER_DAY;
        rented = true;
        System.out.println("Bike rented for " + days + " days. Amount: ₹" + rentalAmount);
    }
}
