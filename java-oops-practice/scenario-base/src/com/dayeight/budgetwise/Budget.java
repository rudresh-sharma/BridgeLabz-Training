package com.dayeight.budgetwise;

import java.util.*;

public abstract class Budget implements IAnalyzable {

    protected double income;
    protected HashMap<String, Double> categoryLimits;
    protected List<Transaction> transactions = new ArrayList<>();

    public Budget(double income, HashMap<String, Double> categoryLimits) {
        this.income = income;
        this.categoryLimits = categoryLimits;
    }

    // Encapsulated: only way to add expense
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    // operator logic
    public double getTotalExpense() {
        double sum = 0;
        for (Transaction t : transactions) {
            if (t.getType().equals("EXPENSE"))
                sum += t.getAmount();
        }
        return sum;
    }

    public double getNetSavings() {
        return income - getTotalExpense();
    }
}
