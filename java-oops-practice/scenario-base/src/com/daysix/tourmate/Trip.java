package com.daysix.tourmate;
public class Trip {
    protected String destination;
    protected int duration;
    protected double budget;

    Transport transport;
    Hotel hotel;
    Activity activity;

    public Trip(String destination, int duration, Transport transport, Hotel hotel, Activity activity) {
        this.destination = destination;
        this.duration = duration;
        this.transport = transport;
        this.hotel = hotel;
        this.activity = activity;
        this.budget = transport.getCost() + hotel.getCost() + activity.getCost();
    }

    public void showDetails() {
        System.out.println("Trip to: " + destination);
        System.out.println("Duration: " + duration + " days");
        System.out.println("Total budget: ₹" + budget);
    }
}
