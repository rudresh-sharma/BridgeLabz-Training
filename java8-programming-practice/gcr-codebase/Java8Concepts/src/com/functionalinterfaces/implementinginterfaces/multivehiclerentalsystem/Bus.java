package com.functionalinterfaces.implementinginterfaces.multivehiclerentalsystem;
public class Bus extends AbstractVehicle {

    private static final double RATE_PER_DAY = 3000;

    @Override
    public void rent(int days) {
        validateRent(days);
        rentalAmount = days * RATE_PER_DAY;
        rented = true;
        System.out.println("Bus rented for " + days + " days. Amount: ₹" + rentalAmount);
    }
}
