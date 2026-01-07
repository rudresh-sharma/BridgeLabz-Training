package com.dayfive.parkease;
public class ParkEaseApp {
    public static void main(String[] args) {

        ParkingSlot slot1 = new ParkingSlot(1, "A1", "Car");
        Vehicle car = new Car("MH12AB1234");

        if (slot1.parkVehicle(car)) {
            System.out.println("Car parked successfully in Slot " + slot1.getSlotId());
        }

        PaymentService payment = new PaymentService();
        int hours = 3;
        boolean isOverTime = true;

        double bill = payment.calculateCharges(car, hours, isOverTime);

        ParkingRecord record = new ParkingRecord(car.getVehicleNumber(), hours, bill);
        record.display();

        slot1.removeVehicle();
    }
}
