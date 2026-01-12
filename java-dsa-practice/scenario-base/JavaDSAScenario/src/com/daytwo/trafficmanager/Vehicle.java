package com.daytwo.trafficmanager;

public class Vehicle {
    String number;
    Vehicle next;

    public Vehicle(String number) {
        this.number = number;
        this.next = null;
    }
}
