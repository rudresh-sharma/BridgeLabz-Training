package com.functionalinterfaces.marker.sensitivedata;

import java.util.Scanner;

public class SensitiveDataTaggingApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // sample sensitive objects
        Customer customer = new Customer("CUST1001", "Yasumi Sharma",
                "9876543210", "abhishek@example.com", "123456789012");
        BankAccount account = new BankAccount("50100012345678", "SBIN0001234", 85000.50, "ABCDE1234F");

        // non-sensitive example
        String normalMessage = "Meeting at 10 AM tomorrow";

        System.out.println("Sensitive Data Tagging Demo (custom marker interface)");
        System.out.println("1 - Process Customer data (sensitive)");
        System.out.println("2 - Process Bank Account data (sensitive)");
        System.out.println("3 - Process normal message (not sensitive)");
        System.out.print("Choose option (1-3): ");

        int choice = input.nextInt();

        Object selectedData;

        if (choice == 1) {
            selectedData = customer;
        } else if (choice == 2) {
            selectedData = account;
        } else if (choice == 3) {
            selectedData = normalMessage;
        } else {
            System.out.println("Invalid choice.");
            input.close();
            return;
        }

        System.out.println("\nProcessing data...");
        SensitiveDataProcessor.processAndEncrypt(selectedData);

        input.close();
    }
}