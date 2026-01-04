package com.inheritance;

public class SmartHomeMain {

    public static void main(String[] args) {

        // Polymorphism: Device reference holding Thermostat object
        Device device = new Thermostat("T1001", "ON", 24);

        device.displayStatus();
    }
}
