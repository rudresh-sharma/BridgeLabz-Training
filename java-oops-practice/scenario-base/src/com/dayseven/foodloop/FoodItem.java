package com.dayseven.foodloop;
public class FoodItem {
    private String name;
    private String category;
    private double price;
    private boolean availability;  

    public FoodItem(String name, String category, double price, boolean availability) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Encapsulation
    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
