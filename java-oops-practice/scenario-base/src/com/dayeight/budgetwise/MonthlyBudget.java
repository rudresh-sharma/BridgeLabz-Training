package com.dayeight.budgetwise;

public class MonthlyBudget extends Budget {

    public MonthlyBudget(double income, java.util.HashMap<String, Double> limits) {
        super(income, limits);
    }

    @Override
    public void generateReport() {
        System.out.println("----- Monthly Budget Report -----");
        System.out.println("Income: " + income);
        System.out.println("Expenses: " + getTotalExpense());
        System.out.println("Savings: " + getNetSavings());
    }

    @Override
    public void detectOverspend() {
        System.out.println("Overspend Check (Monthly):");
        for (String cat : categoryLimits.keySet()) {
            double spent = 0;
            for (Transaction t : transactions) {
                if (t.getCategory().equals(cat))
                    spent += t.getAmount();
            }
            if (spent > categoryLimits.get(cat)) {
                System.out.println(cat + " exceeded limit!");
            }
        }
    }
}
