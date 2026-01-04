package com.encapsulation.bankingsystem;

public class SavingsAccount extends BankAccount implements Loanable {

    private double interestRate;
    private double minimumBalance = 1000;

    public SavingsAccount(String accNo, String name, double balance, double interestRate) {
        super(accNo, name, balance);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        return (balance * interestRate) / 100;
    }

    // Savings accounts should not go below minimum balance
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= minimumBalance) {
            balance -= amount;
        } else {
            System.out.println("❌ Cannot withdraw. Minimum balance must be maintained.");
        }
    }

    // Loanable
    @Override
    public void applyForLoan(double amount) {
        System.out.println("Loan applied for ₹" + amount);
    }

    @Override
    public double calculateLoanEligibility() {
        return balance * 5;   // can take loan up to 5x balance
    }
}
