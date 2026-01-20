package com.dayeight.FoodFest;

public class StallData {
    String stallName;
    int footfall;

    public StallData(String stallName, int footfall) {
        this.stallName = stallName;
        this.footfall = footfall;
    }

    @Override
    public String toString() {
        return stallName + " -> " + footfall;
    }
}
