package com.datastructure.linkedlist.circularlinkedlist.ticketreservationsystem;

public class TicketApp {
    public static void main(String[] args) {

        CircularTicketList list = new CircularTicketList();

        list.addTicket(new Ticket(101, "Ravi", "Avatar", "A1", "10:00 AM"));
        list.addTicket(new Ticket(102, "Amit", "Avatar", "A2", "10:05 AM"));
        list.addTicket(new Ticket(103, "Neha", "Inception", "B1", "10:10 AM"));

        System.out.println("All Tickets:");
        list.displayTickets();

        System.out.println("\nSearch Avatar:");
        list.search("Avatar");

        System.out.println("\nRemove Ticket 102");
        list.removeTicket(102);

        System.out.println("\nTickets After Removal:");
        list.displayTickets();

        list.countTickets();
    }
}
