package com.objectmodeling;
public class Customer {
    private String name;
    private double balance;
    private Bank bank;   // Association (Customer linked to Bank)

    public Customer(String name) {
        this.name = name;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    // Communication method
    public void viewBalance() {
        System.out.println(name + "'s balance in " + bank.getBankName() + " is ₹" + balance);
    }
}
