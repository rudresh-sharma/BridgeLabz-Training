package com.multithreading.banking;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /*
     * Note: This version is DELIBERATELY NOT synchronized to show race condition
     * In real banking systems -> MUST use synchronization!
     * 
     * // Option 1: Synchronized method
     * public synchronized boolean withdraw(double amount, String customerName)
     * 
     * // Option 2: Synchronized block (more flexible)
     * public boolean withdraw(double amount, String customerName) {
     *      synchronized(this) {
     *           // withdraw logic here...
     *      }
     * }
     */
    public boolean withdraw(double amount, String customerName) {
        if (balance >= amount) {
            // Simulating some processing time (increases chance of race condition)
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            balance -= amount;
            System.out.printf("Transaction successful: %s, Amount: %.0f, Balance: %.0f%n",
                    customerName, amount, balance);
            return true;
        } else {
            System.out.printf("Transaction FAILED: %s, Amount: %.0f, Insufficient Balance: %.0f%n",
                    customerName, amount, balance);
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}