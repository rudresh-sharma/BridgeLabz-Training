package com.dayfive.loanbuddy;

public class HomeLoan extends LoanApplication {

    public HomeLoan(Applicant applicant, int termInMonths, double interestRate) {
        super(applicant, "HomeLoan", termInMonths, interestRate);
    }

    @Override
    public boolean approveLoan() {
        // Simple approval rule for demonstration
        if (applicant.getCreditScore() >= 700 && applicant.getIncome() >= 50000) {
            setLoanStatus(true);
        } else {
            setLoanStatus(false);
        }
        return loanStatus;
    }

    @Override
    public double calculateEMI() {
        double P = applicant.getLoanAmount();
        double R = interestRate / 12 / 100;  // monthly interest
        int N = termInMonths;

        double EMI = (P * R * Math.pow(1 + R, N)) / (Math.pow(1 + R, N) - 1);
        return EMI;
    }
}
