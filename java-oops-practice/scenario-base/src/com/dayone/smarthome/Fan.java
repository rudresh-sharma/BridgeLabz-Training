package com.dayone.smarthome;

/**
 * Fan appliance with speed control
 */
public class Fan extends Appliance {
    private int speed; // 1 to 3

    public Fan(String name, int power) {
        super(name, power);
        this.speed = 1; // default speed
    }

    public void setSpeed(int speed) {
        if(speed >= 1 && speed <= 3) this.speed = speed;
    }

    @Override
    public void turnOn() {
        setState(true);
        System.out.println(getName() + " Fan turned ON at speed " + speed);
    }

    @Override
    public void turnOff() {
        setState(false);
        System.out.println(getName() + " Fan turned OFF.");
    }
}
