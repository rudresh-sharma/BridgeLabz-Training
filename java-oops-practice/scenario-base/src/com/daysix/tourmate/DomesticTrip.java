package com.daysix.tourmate;
public class DomesticTrip extends Trip implements IBookable {
    public DomesticTrip(String destination, int duration, Transport transport, Hotel hotel, Activity activity) {
        super(destination, duration, transport, hotel, activity);
    }

    @Override
    public void book() {
        System.out.println("Booking domestic trip to " + destination);
    }

    @Override
    public void cancel() {
        System.out.println("Canceling domestic trip to " + destination);
    }
}
