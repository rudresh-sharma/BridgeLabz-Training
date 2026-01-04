package com.dayone.smarthome;

/**
 * Abstract class representing a generic appliance
 * Encapsulates power usage and device state
 */
public abstract class Appliance implements Controllable {
    private String name;     // Appliance name
    private boolean isOn;    // Current state
    private int power;       // Power consumption in watts

    public Appliance(String name, int power) {
        this.name = name;
        this.power = power;
        this.isOn = false; // default off
    }

    public String getName() { return name; }
    public int getPower() { return power; }
    public boolean isOn() { return isOn; }

    protected void setState(boolean state) { this.isOn = state; }

    public void displayStatus() {
        System.out.println(name + " is " + (isOn ? "ON" : "OFF") + " | Power: " + power + "W");
    }

    // Abstract methods implemented in subclasses
    public abstract void turnOn();
    public abstract void turnOff();
}
