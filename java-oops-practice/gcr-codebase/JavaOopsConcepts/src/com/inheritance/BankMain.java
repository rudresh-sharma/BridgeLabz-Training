package com.inheritance;

public class BankMain {

    public static void main(String[] args) {

        SavingsAccount savings = new SavingsAccount("SA101", 50000, 4.5);
        CheckingAccount checking = new CheckingAccount("CA201", 30000, 10000);
        FixedDepositAccount fd = new FixedDepositAccount("FD301", 100000, 24);

        printAccount(savings);
        System.out.println();

        printAccount(checking);
        System.out.println();

        printAccount(fd);
    }

    public static void printAccount(BankAccount account) {
        account.displayAccountInfo();

        // Downcasting to call subclass methods
        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).displayAccountType();
        } 
        else if (account instanceof CheckingAccount) {
            ((CheckingAccount) account).displayAccountType();
        } 
        else if (account instanceof FixedDepositAccount) {
            ((FixedDepositAccount) account).displayAccountType();
        }
    }
}
