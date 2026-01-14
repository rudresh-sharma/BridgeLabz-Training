package com.daynine.payxpress;
public class WaterBill extends Bill {

    public WaterBill(double amount, String dueDate) {
        super("Water", amount, dueDate);
    }

    public void pay() {
        if (!isPaid()) {
            markAsPaid();
            System.out.println("💧 Water Bill Paid.");
        } else {
            System.out.println("Already paid.");
        }
    }

    public void sendReminder() {
        System.out.println("💧 Water bill due on " + getDueDate());
    }
}
