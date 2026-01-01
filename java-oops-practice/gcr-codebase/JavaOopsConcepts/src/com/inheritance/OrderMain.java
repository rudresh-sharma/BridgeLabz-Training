package com.inheritance;

public class OrderMain {

    public static void main(String[] args) {

        Order order1 = new Order("ORD101", "01-Jan-2025");
        Order order2 = new ShippedOrder("ORD102", "02-Jan-2025", "TRK5678");
        Order order3 = new DeliveredOrder("ORD103", "03-Jan-2025", "TRK9876", "06-Jan-2025");

        printOrder(order1);
        System.out.println();

        printOrder(order2);
        System.out.println();

        printOrder(order3);
    }

    // Polymorphic method
    public static void printOrder(Order order) {
        order.displayDetails();
        System.out.println("Current Status : " + order.getOrderStatus());
    }
}
