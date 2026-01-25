package com.dayten.avltree.hospitalqueuemanagementsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patient implements Comparable<Patient> {

    private String name;
    private LocalDateTime checkInTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Patient(String name, String checkInTime) {
        this.name = name;
        this.checkInTime = LocalDateTime.parse(checkInTime, formatter);
    }

    public String getName() { return name; }
    public LocalDateTime getCheckInTime() { return checkInTime; }

    @Override
    public int compareTo(Patient other) {
        return this.checkInTime.compareTo(other.checkInTime); // Ascending order
    }

    @Override
    public String toString() {
        return name + " (Checked-in: " + checkInTime.format(formatter) + ")";
    }
}
