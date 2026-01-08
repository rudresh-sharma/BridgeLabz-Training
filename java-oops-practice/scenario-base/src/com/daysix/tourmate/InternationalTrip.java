package com.daysix.tourmate;
public class InternationalTrip extends Trip implements IBookable {
    public InternationalTrip(String destination, int duration, Transport transport, Hotel hotel, Activity activity) {
        super(destination, duration, transport, hotel, activity);
    }

    @Override
    public void book() {
        System.out.println("Booking international trip to " + destination + " with visa and insurance");
    }

    @Override
    public void cancel() {
        System.out.println("Canceling international trip to " + destination);
    }
}
