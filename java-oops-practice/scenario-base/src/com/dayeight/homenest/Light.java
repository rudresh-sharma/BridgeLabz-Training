package com.dayeight.homenest;

public class Light extends Device {

    public Light(String id) {
        super(id, 0.5);
    }

    @Override
    public void turnOn() {
        setStatus(true);
        System.out.println("Light " + getDeviceId() + " ON");
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println("Light " + getDeviceId() + " OFF");
    }

    @Override
    public void reset() {
        System.out.println("Light " + getDeviceId() + " reset: brightness defaulted");
    }
}
