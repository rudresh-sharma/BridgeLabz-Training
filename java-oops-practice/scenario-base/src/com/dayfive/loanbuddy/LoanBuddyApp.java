/*
 * Post by Vishal Bhakare
Vishal Bhakare
Created 11:27 AM11:27 AM
14. "LoanBuddy – Loan Approval Automation"
Story: A fintech startup named FinlyTech is launching a product called LoanBuddy, a web and
mobile app that automates personal loan approvals for salaried and self-employed applicants.
LoanBuddy should collect applicant data, verify eligibility, evaluate credit risk, and either
approve or reject the loan based on custom rules. It also calculates monthly EMIs and provides
a personalized repayment plan.
You’re hired as a Java backend developer to design and implement the core loan approval
engine.
Requirements:
● Applicant class: name, creditScore, income, loanAmount.
● LoanApplication class with loan type, term, and interest rate.
● Interface IApprovable with approveLoan() and calculateEMI().
● Use constructors to support different types of loans (personal, home, auto).
● Operators for EMI calculation: P × R × (1+R)^N / ((1+R)^N – 1)
● Encapsulation: keep credit score and internal approval logic private.
● Inheritance: different loan types (HomeLoan, AutoLoan) extending base class.
● Polymorphism: EMI calculation logic varies by loan type.
● Access modifiers to limit loan status changes to internal processes only.

 */



package com.dayfive.loanbuddy;

import java.util.Scanner;

public class LoanBuddyApp {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("======= Welcome to LoanBuddy =======");

        System.out.print("Enter your name: ");
        String name = in.nextLine();

        System.out.print("Enter your credit score: ");
        int creditScore = in.nextInt();

        System.out.print("Enter your monthly income: ");
        double income = in.nextDouble();

        System.out.print("Enter loan amount requested: ");
        double loanAmount = in.nextDouble();

        Applicant applicant = new Applicant(name, creditScore, income, loanAmount);

        int choice;
        do {
            System.out.println("\nSelect Loan Type:");
            System.out.println("1. Personal Loan");
            System.out.println("2. Home Loan");
            System.out.println("3. Auto Loan");
            System.out.println("0. Exit");

            choice = in.nextInt();

            if (choice == 0) break;

            System.out.print("Enter loan term in months: ");
            int term = in.nextInt();

            System.out.print("Enter annual interest rate (%): ");
            double rate = in.nextDouble();

            LoanApplication loan = null;

            switch (choice) {
                case 1:
                    loan = new PersonalLoan(applicant, term, rate);
                    break;
                case 2:
                    loan = new HomeLoan(applicant, term, rate);
                    break;
                case 3:
                    loan = new AutoLoan(applicant, term, rate);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            if (loan != null) {
                boolean approved = loan.approveLoan();
                System.out.println("\nLoan Status: " + (approved ? "Approved" : "Rejected"));
                if (approved) {
                    System.out.println("Monthly EMI: " + loan.calculateEMI());
                }
            }

        } while (choice != 0);

        System.out.println("Thank you for using LoanBuddy!");
    }
}
