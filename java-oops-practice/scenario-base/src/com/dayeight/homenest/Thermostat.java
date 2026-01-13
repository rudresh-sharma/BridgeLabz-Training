package com.dayeight.homenest;

public class Thermostat extends Device {

    public Thermostat(String id) {
        super(id, 3.5);
    }

    @Override
    public void turnOn() {
        setStatus(true);
        System.out.println("Thermostat " + getDeviceId() + " heating");
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println("Thermostat " + getDeviceId() + " off");
    }

    @Override
    public void reset() {
        System.out.println("Thermostat " + getDeviceId() + " temperature reset");
    }
}
