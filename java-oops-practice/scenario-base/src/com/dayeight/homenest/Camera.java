package com.dayeight.homenest;

public class Camera extends Device {

    public Camera(String id) {
        super(id, 2.0);
    }

    @Override
    public void turnOn() {
        setStatus(true);
        System.out.println("Camera " + getDeviceId() + " recording");
    }

    @Override
    public void turnOff() {
        setStatus(false);
        System.out.println("Camera " + getDeviceId() + " stopped");
    }

    @Override
    public void reset() {
        System.out.println("Camera " + getDeviceId() + " recalibrated");
    }
}
