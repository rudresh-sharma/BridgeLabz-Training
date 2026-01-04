package com.dayone.smarthome;

/**
 * Light appliance with custom behavior for turning on/off
 */
public class Light extends Appliance {

    public Light(String name, int power) {
        super(name, power);
    }

    @Override
    public void turnOn() {
        setState(true);
        System.out.println(getName() + " Light turned ON. Brightness adjusted!");
    }

    @Override
    public void turnOff() {
        setState(false);
        System.out.println(getName() + " Light turned OFF.");
    }
}
