package com.streamapi.foreach.welcomeattendee;

public class Attendee {
    private String name;
    private String city;

    // constructor
    public Attendee(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}