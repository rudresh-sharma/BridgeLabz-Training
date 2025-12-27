package com.constructors.levelone;

public class BankMain {
    public static void main(String[] args) {

        SavingsAccount sa = new SavingsAccount(
                "ACC101",
                "Rudresh Sharma",
                5000
        );

        // Access public & protected via subclass method
        sa.showSavingsInfo();

        // Access private balance via public methods
        System.out.println("Current Balance: ₹" + sa.getBalance());

        sa.deposit(2000);
        System.out.println("After Deposit: ₹" + sa.getBalance());

        sa.withdraw(1500);
        System.out.println("After Withdrawal: ₹" + sa.getBalance());
    }
}
