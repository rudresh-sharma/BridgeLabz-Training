package com.encapsulation.ecommerceplatform;

public class Clothing extends Product implements Taxable {

    public Clothing(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * 0.15;   // 15% discount
    }

    @Override
    public double calculateTax() {
        return getPrice() * 0.12;   // 12% tax
    }

    @Override
    public String getTaxDetails() {
        return "12% GST (Clothing)";
    }
}
