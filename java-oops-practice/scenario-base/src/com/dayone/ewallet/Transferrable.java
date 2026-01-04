package com.dayone.ewallet;

/**
 * Interface to define fund transfer behavior
 */
public interface Transferrable {
    void transferTo(User receiver, double amount); // Transfer funds from one user to another
}
