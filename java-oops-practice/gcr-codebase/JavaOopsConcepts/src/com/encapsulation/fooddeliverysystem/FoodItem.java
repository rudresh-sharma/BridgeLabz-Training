package com.encapsulation.fooddeliverysystem;

public abstract class FoodItem {

    private String itemName;
    private double price;
    private int quantity;

    protected double discount;   // accessible to subclasses

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Encapsulation
    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    protected double getBaseTotal() {
        return price * quantity;
    }

    // Abstract method (Polymorphism)
    public abstract double calculateTotalPrice();

    // Concrete method
    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }
}
