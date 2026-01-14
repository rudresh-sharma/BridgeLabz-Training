package com.daythree.smartcheckout;
import java.util.*;

public class Supermarket {

    HashMap<String, Item> store = new HashMap<>();
    Queue<Customer> queue = new LinkedList<>();

    // Add items to store
    public void addItem(String name, double price, int stock) {
        store.put(name, new Item(name, price, stock));
    }

    // Add customer to queue
    public void addCustomer(Customer c) {
        queue.add(c);
        System.out.println(c.name + " added to checkout queue.");
    }

    // Process next customer
    public void processCustomer() {
        if (queue.isEmpty()) {
            System.out.println("No customers in queue.");
            return;
        }

        Customer c = queue.poll();
        double total = 0;

        System.out.println("\nProcessing bill for " + c.name);

        for (String itemName : c.cart.keySet()) {
            int qty = c.cart.get(itemName);

            if (!store.containsKey(itemName)) {
                System.out.println(itemName + " not available.");
                continue;
            }

            Item item = store.get(itemName);

            if (item.stock < qty) {
                System.out.println("Not enough stock for " + itemName);
                continue;
            }

            double cost = item.price * qty;
            item.stock -= qty;
            total += cost;

            System.out.println(itemName + " x " + qty + " = ₹" + cost);
        }

        System.out.println("Total Bill = ₹" + total);
        System.out.println("Thank you, " + c.name + "!");
    }

    // Display remaining stock
    public void showStock() {
        System.out.println("\nCurrent Store Stock:");
        for (Item i : store.values()) {
            System.out.println(i.name + " | Price: ₹" + i.price + " | Stock: " + i.stock);
        }
    }
}
