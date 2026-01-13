package com.dayeight.budgetwise;

public class AnnualBudget extends Budget {

    public AnnualBudget(double income, java.util.HashMap<String, Double> limits) {
        super(income, limits);
    }

    @Override
    public void generateReport() {
        System.out.println("===== Annual Budget Summary =====");
        System.out.println("Yearly Income: " + income);
        System.out.println("Yearly Expense: " + getTotalExpense());
        System.out.println("Yearly Savings: " + getNetSavings());
    }

    @Override
    public void detectOverspend() {
        System.out.println("Overspend Check (Annual):");
        for (String cat : categoryLimits.keySet()) {
            double spent = 0;
            for (Transaction t : transactions) {
                if (t.getCategory().equals(cat))
                    spent += t.getAmount();
            }
            if (spent > categoryLimits.get(cat)) {
                System.out.println("Warning! " + cat + " crossed annual limit");
            }
        }
    }
}
