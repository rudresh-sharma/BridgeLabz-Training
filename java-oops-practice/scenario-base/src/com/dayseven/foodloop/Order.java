package com.dayseven.foodloop;
import java.util.ArrayList;

public class Order implements IOrderable {

    private ArrayList<FoodItem> items;
    private double total;

    // Constructor for custom combo meal
    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        if (item.isAvailable()) {
            items.add(item);
        } else {
            System.out.println(item.getName() + " is not available!");
        }
    }

    // Polymorphism
    public double applyDiscount() {
        if (total > 500)
            return total * 0.10;
        else if (total > 300)
            return total * 0.05;
        else
            return 0;
    }

    // Operator logic
    public double calculateTotal() {
        total = 0;
        for (FoodItem f : items) {
            total += f.getPrice();
        }
        total = total - applyDiscount();
        return total;
    }

    @Override
    public void placeOrder() {
        System.out.println("Order placed!");
        System.out.println("Final Bill: ₹" + calculateTotal());
    }

    @Override
    public void cancelOrder() {
        items.clear();
        System.out.println("Order cancelled.");
    }
}
