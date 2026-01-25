package com.dayten.avltree.onlinebooking;

import java.util.List;

public class TicketBookingSystem {

    private AVLTree tree = new AVLTree();

    // Scenario 1: Insert Event
    public void addEvent(String name, String startTime) {
        Event event = new Event(name, startTime);
        tree.insert(event);
        System.out.println("✅ Event added: " + event);
    }

    // Scenario 2: Cancel Event
    public void cancelEvent(String name, String startTime) {
        Event event = new Event(name, startTime);
        tree.remove(event);
        System.out.println("✅ Event cancelled (if existed): " + event);
    }

    // Scenario 3: Show Events in order
    public void showEvents() {
        List<Event> events = tree.getEventsInOrder();
        if (events.isEmpty()) {
            System.out.println("No upcoming events.");
            return;
        }
        System.out.println("📅 Upcoming Events:");
        for (Event e : events) {
            System.out.println("- " + e);
        }
    }
}
