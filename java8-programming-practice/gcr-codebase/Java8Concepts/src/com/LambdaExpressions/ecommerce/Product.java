package com.LambdaExpressions.ecommerce;
public class Product {
    private String name;
    private double price;
    private double rating;
    private double discount; // percentage discount

    public Product(String name, double price, double rating, double discount) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return name + " | Price: $" + price + " | Rating: " + rating + " | Discount: " + discount + "%";
    }
}
