package com.dayone.smarthome;

/**
 * Air Conditioner appliance with temperature control
 */
public class AC extends Appliance {
    private int temperature;

    public AC(String name, int power, int temperature) {
        super(name, power);
        this.temperature = temperature;
    }

    public void setTemperature(int temperature) { this.temperature = temperature; }

    @Override
    public void turnOn() {
        setState(true);
        System.out.println(getName() + " AC turned ON at " + temperature + "°C");
    }

    @Override
    public void turnOff() {
        setState(false);
        System.out.println(getName() + " AC turned OFF.");
    }
}
