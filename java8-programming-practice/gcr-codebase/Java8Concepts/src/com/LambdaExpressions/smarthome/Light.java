package com.LambdaExpressions.smarthome;
public class Light {
    private String name;
    private int brightness; // 0-100

    public Light(String name) {
        this.name = name;
        this.brightness = 0;
    }

    public void setBrightness(int level) {
        this.brightness = level;
        System.out.println(name + " brightness set to " + level + "%");
    }

    public int getBrightness() {
        return brightness;
    }

    public String getName() {
        return name;
    }
}