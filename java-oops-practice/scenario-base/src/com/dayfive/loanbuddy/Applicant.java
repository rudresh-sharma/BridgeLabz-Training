package com.dayfive.loanbuddy;

public class Applicant {
    private String name;
    private int creditScore;
    private double income;
    private double loanAmount;

    // Constructor
    public Applicant(String name, int creditScore, double income, double loanAmount) {
        this.name = name;
        this.creditScore = creditScore;
        this.income = income;
        this.loanAmount = loanAmount;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public double getIncome() {
        return income;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
}
