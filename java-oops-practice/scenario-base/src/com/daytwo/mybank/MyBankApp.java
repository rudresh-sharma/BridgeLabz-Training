package com.daytwo.mybank;

import java.util.ArrayList;
import java.util.Scanner;

public class MyBankApp {

    static ArrayList<Account> customers = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n\nWelcome to MyBank");
            System.out.println("==========================");
            System.out.println("1. Open Savings Account");
            System.out.println("2. Open Current Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Check Balance");
            System.out.println("6. Calculate Interest");
            System.out.println("7. Exit");

            System.out.print("Enter your choice (1-7): ");
            while (!in.hasNextInt()) {
                System.out.println("Please enter a valid number (1-7).");
                in.next(); // discard invalid input
            }
            choice = in.nextInt();
            in.nextLine(); // clear buffer

            Account a = null;

            switch (choice) {

                // ---------------- OPEN ACCOUNT ----------------
                case 1:
                case 2:
                    System.out.print("Enter your name: ");
                    String name = in.nextLine();

                    System.out.print("Enter your phone number: ");
                    String phoneNumber = in.nextLine();

                    System.out.print("Enter your DOB (mm-dd-yyyy): ");
                    String dob = in.nextLine();

                    System.out.print("Please pay initial deposit (>500): ");
                    double initDepo = 0;
                    while (true) {
                        if (in.hasNextDouble()) {
                            initDepo = in.nextDouble();
                            in.nextLine(); // clear buffer
                            if (initDepo >= 500) break;
                            else System.out.print("Deposit must be >= 500. Enter again: ");
                        } else {
                            System.out.print("Invalid amount. Enter again: ");
                            in.next(); // discard invalid input
                        }
                    }

                    String accNo = Account.generateAccountNumber(); // static method

                    if (choice == 1) {
                        a = new SavingsAccount(name, phoneNumber, dob, initDepo, accNo);
                    } else {
                        a = new CurrentAccount(name, phoneNumber, dob, initDepo, accNo);
                    }

                    customers.add(a);
                    System.out.println("\nCongratulations! Your account is created.");
                    a.showDetails();
                    break;

                // ---------------- DEPOSIT ----------------
                case 3:
                    System.out.print("Enter your account number: ");
                    String givenAccNo1 = in.nextLine();
                    boolean found1 = false;

                    for (Account c : customers) {
                        if (c.getAccountNumber().equals(givenAccNo1)) {
                            System.out.print("Enter deposit amount: ");
                            double money = in.nextDouble();
                            in.nextLine(); // clear buffer
                            c.deposit(money);
                            found1 = true;
                            break;
                        }
                    }

                    if (!found1) {
                        System.out.println("Incorrect account number");
                    }
                    break;

                // ---------------- WITHDRAW ----------------
                case 4:
                    System.out.print("Enter your account number: ");
                    String givenAccNo2 = in.nextLine();
                    boolean found2 = false;

                    for (Account c : customers) {
                        if (c.getAccountNumber().equals(givenAccNo2)) {
                            System.out.print("Enter withdrawal amount: ");
                            double money = in.nextDouble();
                            in.nextLine(); // clear buffer
                            c.withdraw(money);
                            found2 = true;
                            break;
                        }
                    }

                    if (!found2) {
                        System.out.println("Incorrect account number");
                    }
                    break;

                // ---------------- CHECK BALANCE ----------------
                case 5:
                    System.out.print("Enter your account number: ");
                    String givenAccNo3 = in.nextLine();
                    boolean found3 = false;

                    for (Account c : customers) {
                        if (c.getAccountNumber().equals(givenAccNo3)) {
                            c.checkBalance();
                            found3 = true;
                            break;
                        }
                    }

                    if (!found3) {
                        System.out.println("Incorrect account number");
                    }
                    break;

                // ---------------- CALCULATE INTEREST ----------------
                case 6:
                    System.out.print("Enter your account number: ");
                    String givenAccNo4 = in.nextLine();
                    boolean found4 = false;

                    for (Account c : customers) {
                        if (c.getAccountNumber().equals(givenAccNo4)) {
                            c.calculateInterest(); // polymorphism
                            found4 = true;
                            break;
                        }
                    }

                    if (!found4) {
                        System.out.println("Incorrect account number");
                    }
                    break;

                case 7:
                    System.out.println("Thank you for using MyBank 💳");
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1-7.");
            }

        } while (choice != 7);

        System.out.println("Have a great day!");
    }
}
