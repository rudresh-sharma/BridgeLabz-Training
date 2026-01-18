package com.generics.smartwarehousemanagementsystem;

import java.util.List;

public class WarehouseUtil {
    public static void displayItems(List<? extends WarehouseItem> items) {
        if(items.isEmpty()) {
            System.out.println("No items in this category.");
            return;
        }
        for (WarehouseItem item : items) {
            System.out.println(item.getCategory() + " Item: " + item.getName());
        }
    }
}
