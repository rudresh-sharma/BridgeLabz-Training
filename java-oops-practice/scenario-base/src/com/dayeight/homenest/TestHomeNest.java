package com.dayeight.homenest;

public class TestHomeNest {

    public static void main(String[] args) {

        Device[] devices = {
            new Light("L1"),
            new Camera("C1"),
            new Thermostat("T1"),
            new Lock("K1")
        };

        for (Device d : devices) {
            d.turnOn();
            d.reset();   // polymorphism
            System.out.println("Monthly Energy: " + d.getMonthlyEnergy(5));
            System.out.println("-------------------");
        }
    }
}
