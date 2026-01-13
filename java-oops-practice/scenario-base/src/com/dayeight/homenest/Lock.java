package com.dayeight.homenest;

public class Lock extends Device {

    public Lock(String id) {
        super(id, 1.0);
    }

    @Override
    public void turnOn() {
        setStatus(true);
        System.out.println("Lock " + getDeviceId() + " secured");
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println("Lock " + getDeviceId() + " unlocked");
    }

    @Override
    public void reset() {
        System.out.println("Lock " + getDeviceId() + " access codes reset");
    }
}
