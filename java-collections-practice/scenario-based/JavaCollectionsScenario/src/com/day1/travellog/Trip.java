package com.day1.travellog;

import java.io.Serializable;

public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String country;
    private int durationDays;
    private String description;

    public Trip(String city, String country, int durationDays, String description) {
        this.city = city;
        this.country = country;
        this.durationDays = durationDays;
        this.description = description;
    }

    public String getCity() { return city; }
    public String getCountry() { return country; }
    public int getDurationDays() { return durationDays; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "City: " + city +
               ", Country: " + country +
               ", Duration: " + durationDays + " days" +
               ", Description: " + description;
    }
}
