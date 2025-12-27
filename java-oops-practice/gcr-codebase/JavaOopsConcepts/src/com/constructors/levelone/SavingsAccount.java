package com.constructors.levelone;

public class SavingsAccount extends BankAccount {

    public SavingsAccount(String accNo, String holder, double balance) {
        super(accNo, holder, balance);
    }

    // Demonstrating access of public & protected
    public void showSavingsInfo() {
        System.out.println("Savings Account No: " + accountNumber);   // public
        System.out.println("Holder Name: " + accountHolder);          // protected
    }
}
