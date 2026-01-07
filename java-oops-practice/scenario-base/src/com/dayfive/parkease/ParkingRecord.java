package com.dayfive.parkease;
public class ParkingRecord {
    private String vehicleNumber;
    private int hours;
    private double amount;

    public ParkingRecord(String vehicleNumber, int hours, double amount) {
        this.vehicleNumber = vehicleNumber;
        this.hours = hours;
        this.amount = amount;
    }

    public void display() {
        System.out.println("Vehicle: " + vehicleNumber + 
                           " | Hours: " + hours + 
                           " | Amount: ₹" + amount);
    }
}
