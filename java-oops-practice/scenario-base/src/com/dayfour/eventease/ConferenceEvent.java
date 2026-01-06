package com.dayfour.eventease;

public class ConferenceEvent extends Event {

    public ConferenceEvent(String name, String location, String date, int attendees, User user) {
        super(name, location, date, attendees, 8000, 1000, user);
    }

    @Override
    public void schedule() {
        System.out.println("Conference scheduled with speakers and equipment.");
    }
}
