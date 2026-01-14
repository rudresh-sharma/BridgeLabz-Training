package com.daynine.payxpress;
public class ElectricityBill extends Bill {

    public ElectricityBill(double amount, String dueDate) {
        super("Electricity", amount, dueDate);
    }

    public void pay() {
        if (!isPaid()) {
            markAsPaid();
            System.out.println("⚡ Electricity Bill Paid.");
        } else {
            System.out.println("Already paid.");
        }
    }

    public void sendReminder() {
        System.out.println("⚡ Electricity bill due on " + getDueDate());
    }
}
