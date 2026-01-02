package com.encapsulation.ecommerceplatform;

public class Groceries extends Product {

    public Groceries(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.05;   // 5% discount
    }
}
