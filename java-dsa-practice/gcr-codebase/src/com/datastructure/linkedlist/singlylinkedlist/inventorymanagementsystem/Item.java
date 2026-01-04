package com.datastructure.linkedlist.singlylinkedlist.inventorymanagementsystem;

public class Item {
    int itemId;
    String name;
    int quantity;
    double price;

    public Item(int itemId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalValue() {
        return quantity * price;
    }

    public void display() {
        System.out.println("ID: " + itemId + ", Name: " + name +
                ", Qty: " + quantity + ", Price: " + price +
                ", Total: " + getTotalValue());
    }
}
