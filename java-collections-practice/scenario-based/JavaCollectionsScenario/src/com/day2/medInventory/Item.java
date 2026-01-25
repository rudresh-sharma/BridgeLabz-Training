package com.day2.medInventory;

import java.util.Objects;

public class Item {

    private String itemId;
    private String itemName;
    private int quantity;
    private String expiryDate; // dd-MM-yyyy

    public Item(String itemId, String itemName, int quantity, String expiryDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return itemId.equals(item.itemId); // Duplicate based on ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    @Override
    public String toString() {
        return itemId + " | " + itemName +
                " | Qty: " + quantity +
                " | Expiry: " + expiryDate;
    }
}
