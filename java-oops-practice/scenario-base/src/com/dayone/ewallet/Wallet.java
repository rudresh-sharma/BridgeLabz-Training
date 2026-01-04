package com.dayone.ewallet;

import java.util.*;

/**
 * Abstract wallet class to handle balance and transactions
 * Encapsulates balance and transaction history
 */
public abstract class Wallet implements Transferrable {
    protected double balance; // Encapsulation: cannot be accessed directly
    protected List<Transaction> history = new ArrayList<>();

    public Wallet(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void addTransaction(Transaction tx) {
        history.add(tx);
    }

    public void showHistory() {
        if(history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for(Transaction tx : history) {
                tx.displayTransaction();
            }
        }
    }

    // Abstract transfer method (Polymorphism)
    public abstract void transferTo(User receiver, double amount);
}
