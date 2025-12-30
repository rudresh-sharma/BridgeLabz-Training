package com.objectmodeling;

public class BankMain {
    public static void main(String[] args) {

        // Create Bank
        Bank sbi = new Bank("SBI Bank");

        // Create Customers
        Customer ram = new Customer("Ram");
        Customer sita = new Customer("Sita");

        // Bank opens accounts for customers
        sbi.openAccount(ram, 5000);
        sbi.openAccount(sita, 8000);

        // Customers communicate with bank
        ram.viewBalance();
        sita.viewBalance();
    }
}
