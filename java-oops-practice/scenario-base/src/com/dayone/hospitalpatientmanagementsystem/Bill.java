package com.dayone.hospitalpatientmanagementsystem;

public class Bill implements Payable {

    private double baseAmount;
    private double taxRate;
    private double discount;

    public Bill(double baseAmount, double taxRate, double discount) {
        this.baseAmount = baseAmount;
        this.taxRate = taxRate;
        this.discount = discount;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // Abstraction
    @Override
    public double calculatePayment() {
        double tax = (baseAmount * taxRate) / 100;
        return baseAmount + tax - discount;
    }
}
