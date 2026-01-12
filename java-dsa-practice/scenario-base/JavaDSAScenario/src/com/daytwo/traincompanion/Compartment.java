package com.daytwo.traincompanion;

public class Compartment {
    String name;          // like Pantry, WiFi, Sleeper
    Compartment prev;
    Compartment next;

    public Compartment(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}
