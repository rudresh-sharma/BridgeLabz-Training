package com.encapsulation.fooddeliverysystem;

import java.util.ArrayList;

public class FoodDeliveryMain {
    public static void main(String[] args) {

        ArrayList<FoodItem> order = new ArrayList<>();

        FoodItem f1 = new VegItem("Paneer Pizza", 300, 2);
        FoodItem f2 = new NonVegItem("Chicken Burger", 250, 2);

        ((Discountable) f1).applyDiscount(10);  // 10% discount
        ((Discountable) f2).applyDiscount(5);   // 5% discount

        order.add(f1);
        order.add(f2);

        for (FoodItem item : order) {
            System.out.println("--------------------");
            item.getItemDetails();
            System.out.println("Discount: " + ((Discountable)item).getDiscountDetails());
            System.out.println("Total Price: " + item.calculateTotalPrice());
        }
    }
}
