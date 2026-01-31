package com.functionalinterfaces.implementinginterfaces.multivehiclerentalsystem;
public interface Vehicle {

    void rent(int days);

    void returnVehicle();

    boolean isRented();

    double getRentalAmount();
}
