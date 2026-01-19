package com.dayten.bagnball;

public class Ball {
    private String uniqueId;
    private String color;
    private int size;

    public Ball(String uniqueId, String color, int size) {
        this.uniqueId = uniqueId;
        this.color = color;
        this.size = size;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}
