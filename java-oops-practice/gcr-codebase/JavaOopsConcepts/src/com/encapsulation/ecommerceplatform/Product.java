package com.encapsulation.ecommerceplatform;

public abstract class Product {

    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    // Abstract discount method
    public abstract double calculateDiscount();

    // Encapsulation
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    public void showProductDetails() {
        System.out.println("\nProduct ID : " + productId);
        System.out.println("Name       : " + name);
        System.out.println("Base Price : " + price);
    }
}
