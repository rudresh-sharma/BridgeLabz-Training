package com.functionalinterfaces.implementinginterfaces.multivehiclerentalsystem;
public abstract class AbstractVehicle implements Vehicle {

    protected boolean rented;
    protected double rentalAmount;

    @Override
    public boolean isRented() {
        return rented;
    }

    @Override
    public double getRentalAmount() {
        if (!rented) {
            throw new RentalException("Vehicle is not rented yet.");
        }
        return rentalAmount;
    }

    protected void validateRent(int days) {
        if (days <= 0) {
            throw new RentalException("Rental days must be greater than zero.");
        }
        if (rented) {
            throw new RentalException("Vehicle is already rented.");
        }
    }

    @Override
    public void returnVehicle() {
        if (!rented) {
            throw new RentalException("Vehicle is not rented, cannot return.");
        }
        rented = false;
        rentalAmount = 0;
        System.out.println(getClass().getSimpleName() + " returned successfully.");
    }
}
