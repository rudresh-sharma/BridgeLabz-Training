package com.encapsulation.fooddeliverysystem;

public class VegItem extends FoodItem implements Discountable {

    public VegItem(String name, double price, int qty) {
        super(name, price, qty);
    }

    @Override
    public double calculateTotalPrice() {
        return getBaseTotal() - discount;
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
