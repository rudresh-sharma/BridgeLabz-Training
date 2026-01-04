package com.encapsulation.librarysystem;

import java.util.ArrayList;

public class LibraryMain {
    public static void main(String[] args) {

        ArrayList<LibraryItem> items = new ArrayList<>();

        items.add(new Book("B101", "Java Programming", "James Gosling"));
        items.add(new Magazine("M201", "Tech Weekly", "Editorial Team"));
        items.add(new DVD("D301", "OOP Concepts", "Training Videos"));

        for (LibraryItem item : items) {
            System.out.println("----------------------");
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");

            if (item instanceof Reservable) {
                Reservable r = (Reservable) item;
                System.out.println("Available: " + r.checkAvailability());
            }
        }
    }
}
