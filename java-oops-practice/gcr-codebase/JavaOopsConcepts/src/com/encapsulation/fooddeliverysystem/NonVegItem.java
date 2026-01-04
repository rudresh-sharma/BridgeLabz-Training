package com.encapsulation.fooddeliverysystem;

public class NonVegItem extends FoodItem implements Discountable {

    private double extraCharge = 50;  // extra for non-veg

    public NonVegItem(String name, double price, int qty) {
        super(name, price, qty);
    }

    @Override
    public double calculateTotalPrice() {
        return getBaseTotal() + extraCharge - discount;
    }

    @Override
    public void applyDiscount(double percent) {
        discount = (getBaseTotal() * percent) / 100;
    }

    @Override
    public double getDiscountDetails() {
        return discount;
    }
}
