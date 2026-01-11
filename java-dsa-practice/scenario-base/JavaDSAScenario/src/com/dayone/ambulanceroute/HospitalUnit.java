package com.dayone.ambulanceroute;

public class HospitalUnit {
    String name;
    boolean available; // true if unit can accept patient
    HospitalUnit next;

    public HospitalUnit(String name, boolean available) {
        this.name = name;
        this.available = available;
        this.next = null;
    }

    @Override
    public String toString() {
        return name + (available ? " (Available)" : " (Unavailable)");
    }
}
