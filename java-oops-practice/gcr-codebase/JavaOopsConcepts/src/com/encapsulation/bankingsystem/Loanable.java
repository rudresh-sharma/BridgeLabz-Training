package com.encapsulation.bankingsystem;

public interface Loanable {
    void applyForLoan(double amount);
    double calculateLoanEligibility();
}
