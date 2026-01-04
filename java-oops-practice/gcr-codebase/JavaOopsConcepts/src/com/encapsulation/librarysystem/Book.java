package com.encapsulation.librarysystem;

public class Book extends LibraryItem implements Reservable {

    public Book(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 14; // 14 days
    }

    @Override
    public void reserveItem(String borrowerName) {
        if (available) {
            available = false;
            setBorrower(borrowerName);
            System.out.println("Book reserved by " + borrowerName);
        } else {
            System.out.println("Book not available.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return available;
    }
}
