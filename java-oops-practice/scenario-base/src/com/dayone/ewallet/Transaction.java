package com.dayone.ewallet;

import java.time.LocalDateTime;

/**
 * Represents a single transaction record
 */
public class Transaction {
    private String fromUser;
    private String toUser;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(String fromUser, String toUser, double amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public void displayTransaction() {
        System.out.println(timestamp + " : " + fromUser + " -> " + toUser + " : $" + amount);
    }
}
