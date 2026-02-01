package com.collectors.orderrevenue;

public class Order {
    private String orderId;
    private String customerName;
    private double amount;

    public Order(String orderId, String customerName, double amount) {
	this.orderId = orderId;
	this.customerName = customerName;
	this.amount = amount;
    }

    public String getCustomerName() {
	return customerName;
    }

    public double getAmount() {
	return amount;
    }

    @Override
    public String toString() {
	return "Order[" + orderId + " | " + customerName + " | Rs." + amount + "]";
    }
}