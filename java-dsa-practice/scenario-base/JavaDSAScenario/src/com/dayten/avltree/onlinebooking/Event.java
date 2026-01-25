package com.dayten.avltree.onlinebooking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event> {

    private String name;
    private LocalDateTime startTime;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String name, String startTime) {
        this.name = name;
        this.startTime = LocalDateTime.parse(startTime, formatter);
    }

    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }

    @Override
    public int compareTo(Event other) {
        return this.startTime.compareTo(other.startTime); // ascending by startTime
    }

    @Override
    public String toString() {
        return name + " at " + startTime.format(formatter);
    }
}
