package com.inheritance;

public class Order {

    protected String orderId;
    protected String orderDate;

    public Order(String orderId, String orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }

    // Method to get order status
    public String getOrderStatus() {
        return "Order Placed";
    }

    public void displayDetails() {
        System.out.println("Order ID : " + orderId);
        System.out.println("Order Date : " + orderDate);
    }
}
