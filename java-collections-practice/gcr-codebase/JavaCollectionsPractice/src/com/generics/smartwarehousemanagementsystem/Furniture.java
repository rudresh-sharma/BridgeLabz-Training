package com.generics.smartwarehousemanagementsystem;
public class Furniture extends WarehouseItem {
    public Furniture(String name) { super(name); }

    @Override
    public String getCategory() { return "Furniture"; }
}
