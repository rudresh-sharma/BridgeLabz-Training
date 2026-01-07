package com.dayfive.parkease;
public class PaymentService implements IPayable {

    @Override
    public double calculateCharges(int hours, boolean isOverTime) {
        return 0; // Not used directly
    }

    public double calculateCharges(Vehicle vehicle, int hours, boolean isOverTime) {
        double amount = vehicle.getBaseRate() * hours;
        if (isOverTime) {
            amount += vehicle.getPenalty();
        }
        return amount;
    }
}
