package com.encapsulation.librarysystem;

public interface Reservable {
    void reserveItem(String borrowerName);
    boolean checkAvailability();
}
