package com.encapsulation.bankingsystem;

import java.util.ArrayList;

public class BankMain {
    public static void main(String[] args) {

        ArrayList<BankAccount> accounts = new ArrayList<>();

        accounts.add(new SavingsAccount("S101", "Rudresh", 50000, 4));
        accounts.add(new CurrentAccount("C201", "Amit", 30000));

        for (BankAccount acc : accounts) {
            System.out.println("----------------------");
            acc.displayDetails();

            double interest = acc.calculateInterest();
            System.out.println("Interest: " + interest);

            if (acc instanceof Loanable) {
                Loanable l = (Loanable) acc;
                System.out.println("Loan Eligibility: " + l.calculateLoanEligibility());
            }
        }
    }
}
