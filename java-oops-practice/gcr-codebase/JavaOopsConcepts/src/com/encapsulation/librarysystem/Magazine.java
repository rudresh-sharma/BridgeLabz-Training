package com.encapsulation.librarysystem;

public class Magazine extends LibraryItem implements Reservable {

    public Magazine(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 7; // 7 days
    }

    @Override
    public void reserveItem(String borrowerName) {
        if (available) {
            available = false;
            setBorrower(borrowerName);
            System.out.println("Magazine reserved by " + borrowerName);
        } else {
            System.out.println("Magazine not available.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return available;
    }
}
