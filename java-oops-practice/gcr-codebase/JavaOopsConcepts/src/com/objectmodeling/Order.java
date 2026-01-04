package com.objectmodeling;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private ArrayList<Product> products;

    public Order(int orderId) {
        this.orderId = orderId;
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to Order " + orderId);
    }

    public void showOrder() {
        System.out.println("\nOrder ID: " + orderId);
        double total = 0;
        for (Product p : products) {
            System.out.println(p.getName() + " - ₹" + p.getPrice());
            total += p.getPrice();
        }
        System.out.println("Total = ₹" + total);
    }
}
