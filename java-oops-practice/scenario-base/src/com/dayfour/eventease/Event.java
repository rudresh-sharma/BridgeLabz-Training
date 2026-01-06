package com.dayfour.eventease;

public class Event implements ISchedulable {

    private static int counter = 1000;     // Auto-ID generator
    private final int eventId;             // Cannot be modified

    private String eventName;
    private String location;
    private String date;
    private int attendees;

    private double venueCost;
    private double serviceCost;
    private double discount;

    protected User organizer;

    // Constructor without services
    public Event(String eventName, String location, String date, int attendees, User organizer) {
        this.eventId = ++counter;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.attendees = attendees;
        this.organizer = organizer;
        this.venueCost = 5000;
        this.serviceCost = 0;
        this.discount = 0;
    }

    // Constructor with services
    public Event(String eventName, String location, String date, int attendees,
                 double serviceCost, double discount, User organizer) {

        this(eventName, location, date, attendees, organizer);
        this.serviceCost = serviceCost;
        this.discount = discount;
    }

    public double calculateTotalCost() {
        return venueCost + serviceCost - discount;   // Operator usage
    }

    public int getEventId() {
        return eventId;
    }

    @Override
    public void schedule() {
        System.out.println("Event scheduled on " + date);
    }

    @Override
    public void reschedule(String newDate) {
        this.date = newDate;
        System.out.println("Event rescheduled to " + newDate);
    }

    @Override
    public void cancel() {
        System.out.println("Event cancelled");
    }
}
