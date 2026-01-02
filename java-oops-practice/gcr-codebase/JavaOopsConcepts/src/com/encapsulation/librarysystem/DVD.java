package com.encapsulation.librarysystem;

public class DVD extends LibraryItem implements Reservable {

    public DVD(String id, String title, String author) {
        super(id, title, author);
    }

    @Override
    public int getLoanDuration() {
        return 3; // 3 days
    }

    @Override
    public void reserveItem(String borrowerName) {
        if (available) {
            available = false;
            setBorrower(borrowerName);
            System.out.println("DVD reserved by " + borrowerName);
        } else {
            System.out.println("DVD not available.");
        }
    }

    @Override
    public boolean checkAvailability() {
        return available;
    }
}
