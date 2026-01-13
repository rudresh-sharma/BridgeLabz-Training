package com.dayeight.budgetwise;

import java.util.*;

public class TestBudgetWise {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<String, Double> limits = new HashMap<>();

        System.out.print("Enter number of categories: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Category name: ");
            String cat = sc.next();
            System.out.print("Limit: ");
            double limit = sc.nextDouble();
            limits.put(cat, limit);
        }

        System.out.print("Enter income: ");
        double income = sc.nextDouble();

        System.out.print("Choose budget type (1=Monthly, 2=Annual): ");
        int type = sc.nextInt();

        Budget budget;
        if (type == 1)
            budget = new MonthlyBudget(income, limits);
        else
            budget = new AnnualBudget(income, limits);

        int choice;
        do {
            System.out.println("\n1. Add Expense");
            System.out.println("2. Generate Report");
            System.out.println("3. Detect Overspend");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    System.out.print("Date: ");
                    String date = sc.next();
                    System.out.print("Category: ");
                    String cat = sc.next();

                    budget.addTransaction(new Transaction(amt, "EXPENSE", date, cat));
                    break;

                case 2:
                    budget.generateReport();
                    break;

                case 3:
                    budget.detectOverspend();
                    break;
            }
        } while (choice != 0);

        sc.close();
    }
}
