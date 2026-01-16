package com.dayfour.eventmanager;
class Ticket {
    String ticketId;
    String eventName;
    double price;

    Ticket(String id, String event, double price) {
        this.ticketId = id;
        this.eventName = event;
        this.price = price;
    }

    public String toString() {
        return ticketId + " | " + eventName + " | ₹" + price;
    }
}
