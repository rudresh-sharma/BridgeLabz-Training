package com.datastructure.linkedlist.circularlinkedlist.ticketreservationsystem;

public class CircularTicketList {

    private Node head = null;
    private Node tail = null;

    // Add Ticket at End
    public void addTicket(Ticket ticket) {
        Node newNode = new Node(ticket);

        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
    }

    // Remove Ticket by ID
    public void removeTicket(int id) {
        if (head == null) {
            System.out.println("No tickets");
            return;
        }

        Node curr = head;
        Node prev = tail;

        do {
            if (curr.data.ticketId == id) {

                if (curr == head && curr == tail) {
                    head = tail = null;
                }
                else if (curr == head) {
                    head = head.next;
                    tail.next = head;
                }
                else if (curr == tail) {
                    tail = prev;
                    tail.next = head;
                }
                else {
                    prev.next = curr.next;
                }

                System.out.println("Ticket removed");
                return;
            }

            prev = curr;
            curr = curr.next;
        } while (curr != head);

        System.out.println("Ticket not found");
    }

    // Display all tickets
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked");
            return;
        }

        Node temp = head;
        do {
            temp.data.display();
            temp = temp.next;
        } while (temp != head);
    }

    // Search by Customer or Movie
    public void search(String key) {
        if (head == null) return;

        Node temp = head;
        boolean found = false;

        do {
            if (temp.data.customerName.equalsIgnoreCase(key) ||
                temp.data.movieName.equalsIgnoreCase(key)) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No matching ticket found");
    }

    // Count Tickets
    public void countTickets() {
        if (head == null) {
            System.out.println("Total Tickets: 0");
            return;
        }

        int count = 0;
        Node temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total Tickets Booked: " + count);
    }
}
