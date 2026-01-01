package com.inheritance;

// Thermostat is a type of Device
public class Thermostat extends Device {

    private int temperatureSetting;

    // Constructor
    public Thermostat(String deviceId, String status, int temperatureSetting) {
        super(deviceId, status);   // call parent constructor
        this.temperatureSetting = temperatureSetting;
    }

    // Overriding displayStatus()
    @Override
    public void displayStatus() {
        super.displayStatus();    // show device info
        System.out.println("Temperature Setting : " + temperatureSetting + "°C");
    }
}
