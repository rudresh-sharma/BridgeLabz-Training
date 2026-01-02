package com.encapsulation.bankingsystem;

public class CurrentAccount extends BankAccount implements Loanable {

    private double overdraftLimit = 50000;

    public CurrentAccount(String accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    public double calculateInterest() {
        return balance * 0.01;  // 1% interest
    }

    // Allow overdraft
    @Override
    public void withdraw(double amount) {
        if (balance - amount >= -overdraftLimit) {
            balance -= amount;
        } else {
            System.out.println("❌ Overdraft limit exceeded");
        }
    }

    // Loanable
    @Override
    public void applyForLoan(double amount) {
        System.out.println("Loan applied for ₹" + amount);
    }

    @Override
    public double calculateLoanEligibility() {
        return balance * 3 + overdraftLimit;
    }
}
