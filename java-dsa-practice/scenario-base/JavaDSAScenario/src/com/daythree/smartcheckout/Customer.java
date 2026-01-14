package com.daythree.smartcheckout;
import java.util.HashMap;

public class Customer {
    String name;
    HashMap<String, Integer> cart = new HashMap<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addItem(String itemName, int quantity) {
        cart.put(itemName, cart.getOrDefault(itemName, 0) + quantity);
    }
}
