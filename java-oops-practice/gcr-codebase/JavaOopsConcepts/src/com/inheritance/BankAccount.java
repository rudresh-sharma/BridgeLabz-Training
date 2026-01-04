package com.inheritance;

public class BankAccount {

    protected String accountNumber;
    protected double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void displayAccountInfo() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Balance : ₹" + balance);
    }
}
