package com.dayfour.eventease;

public class BirthdayEvent extends Event {

    public BirthdayEvent(String name, String location, String date, int attendees, User user) {
        super(name, location, date, attendees, 3000, 500, user);
    }

    @Override
    public void schedule() {
        System.out.println("Birthday party scheduled with cake and decorations!");
    }
}
