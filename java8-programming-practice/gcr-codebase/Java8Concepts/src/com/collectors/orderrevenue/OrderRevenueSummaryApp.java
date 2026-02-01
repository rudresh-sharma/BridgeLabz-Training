package com.collectors.orderrevenue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRevenueSummaryApp {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order("ORD001", "Rohan", 2500.00));
        orders.add(new Order("ORD002", "Priya", 1800.50));
        orders.add(new Order("ORD003", "Rohan", 4200.00));
        orders.add(new Order("ORD004", "Amit", 950.75));
        orders.add(new Order("ORD005", "Priya", 3100.00));
        orders.add(new Order("ORD006", "Sneha", 6700.25));
        orders.add(new Order("ORD007", "Rohan", 1500.00));
        orders.add(new Order("ORD008", "Amit", 2800.00));

        System.out.println("Order Revenue Summary - Total per Customer");
        System.out.println("------------------------------------------");

        Map<String, Double> revenueByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerName,
                        Collectors.summingDouble(Order::getAmount)
                ));

        revenueByCustomer.forEach((customer, total) -> {
            System.out.printf("%-10s : Rs. %.2f%n", customer, total);
        });

        System.out.println("------------------------------------------");
        System.out.println("Total orders processed: " + orders.size());
    }
}