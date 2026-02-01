package com.streamapi.foreach.welcomeattendee;

import java.util.ArrayList;
import java.util.List;

public class EventWelcomeApp {
    public static void main(String[] args) {
        List<Attendee> attendees = new ArrayList<>();

        attendees.add(new Attendee("Rohan Sharma", "Indore"));
        attendees.add(new Attendee("Priya Jain", "Bhopal"));
        attendees.add(new Attendee("Amit Patel", "Ujjain"));
        attendees.add(new Attendee("Sneha Verma", "Jabalpur"));
        attendees.add(new Attendee("Vikram Singh", "Gwalior"));
        attendees.add(new Attendee("Neha Gupta", "Rewa"));

        System.out.println("Event Attendee Welcome Messages");
        System.out.println("-------------------------------");

        attendees.stream()
                .forEach(att -> {
                    System.out.println("Welcome " + att.getName() + "!");
                    System.out.println("Glad to have you with us from " + att.getCity() + ".");
                    System.out.println("Enjoy the event!");
                    System.out.println("-------------------------------");
                });

        System.out.println("Total attendees welcomed: " + attendees.size());
    }
}