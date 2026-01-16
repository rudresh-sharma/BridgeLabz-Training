package com.dayfour.eventmanager;
public class EventManager {

    public static void main(String[] args) {

        Ticket[] tickets = {
                new Ticket("T101", "Rock Concert", 1500),
                new Ticket("T102", "Tech Conference", 3500),
                new Ticket("T103", "Comedy Show", 800),
                new Ticket("T104", "Music Fest", 1200),
                new Ticket("T105", "Startup Meet", 2000)
        };

        System.out.println("🎟 Before Sorting:");
        for (Ticket t : tickets) {
            System.out.println(t);
        }

        TicketQuickSort.quickSort(tickets, 0, tickets.length - 1);

        System.out.println("\n📊 After Sorting (By Ticket Price):");
        for (Ticket t : tickets) {
            System.out.println(t);
        }

        System.out.println("\n🔥 Top 3 Cheapest Tickets:");
        for (int i = 0; i < 3; i++) {
            System.out.println(tickets[i]);
        }

        System.out.println("\n💎 Top 3 Most Expensive Tickets:");
        for (int i = tickets.length - 3; i < tickets.length; i++) {
            System.out.println(tickets[i]);
        }
    }
}
