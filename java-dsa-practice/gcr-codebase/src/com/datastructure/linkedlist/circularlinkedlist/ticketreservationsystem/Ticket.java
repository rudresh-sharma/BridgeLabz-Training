package com.datastructure.linkedlist.circularlinkedlist.ticketreservationsystem;

public class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNo;
    String bookingTime;

    public Ticket(int ticketId, String customerName, String movieName,
                  String seatNo, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNo = seatNo;
        this.bookingTime = bookingTime;
    }

    public void display() {
        System.out.println("ID: " + ticketId + ", Customer: " + customerName +
                ", Movie: " + movieName + ", Seat: " + seatNo +
                ", Time: " + bookingTime);
    }
}
