package com.daynine.payxpress;
public class InternetBill extends Bill {

    public InternetBill(double amount, String dueDate) {
        super("Internet", amount, dueDate);
    }

    public void pay() {
        if (!isPaid()) {
            markAsPaid();
            System.out.println("🌐 Internet Bill Paid.");
        } else {
            System.out.println("Already paid.");
        }
    }

    public void sendReminder() {
        System.out.println("🌐 Internet bill due on " + getDueDate() + " – avoid disconnection!");
    }
}
