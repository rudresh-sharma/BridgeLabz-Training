package com.daynine.payxpress;
public abstract class Bill implements IPayable {

    private String type;
    private double amount;
    private String dueDate;
    private boolean isPaid;

    public Bill(String type, double amount, String dueDate) {
        this.type = type;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isPaid = false;
    }

    protected void markAsPaid() {
        isPaid = true;
    }

    protected boolean isPaid() {
        return isPaid;
    }

    public double calculateLateFee(double penalty) {
        return amount + penalty;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDueDate() {
        return dueDate;
    }
}
