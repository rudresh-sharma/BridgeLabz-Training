package com.objectmodeling;

import java.util.ArrayList;

public class Bank {
    private String bankName;
    private ArrayList<Customer> customers;

    public Bank(String bankName) {
        this.bankName = bankName;
        customers = new ArrayList<>();
    }

    // Communication method
    public void openAccount(Customer customer, double initialBalance) {
        customers.add(customer);
        customer.setBank(this);
        customer.setBalance(initialBalance);

        System.out.println(customer.getName() + " opened an account in " + bankName +
                " with balance ₹" + initialBalance);
    }

    public String getBankName() {
        return bankName;
    }
}
