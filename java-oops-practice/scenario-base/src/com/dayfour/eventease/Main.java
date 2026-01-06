package com.dayfour.eventease;

public class Main {
    public static void main(String[] args) {

        User u1 = new User("Rudresh", "rudresh@gmail.com", "9999999999");

        Event e1 = new BirthdayEvent("Riya Bday", "Mumbai", "10-Apr-2026", 50, u1);
        Event e2 = new ConferenceEvent("Tech Summit", "Delhi", "20-May-2026", 200, u1);

        e1.schedule();   // Birthday version
        System.out.println("Cost: " + e1.calculateTotalCost());

        e2.schedule();   // Conference version
        System.out.println("Cost: " + e2.calculateTotalCost());
    }
}
