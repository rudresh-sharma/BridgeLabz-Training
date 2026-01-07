package com.dayfive.loanbuddy;

public class AutoLoan extends LoanApplication {

    public AutoLoan(Applicant applicant, int termInMonths, double interestRate) {
        super(applicant, "AutoLoan", termInMonths, interestRate);
    }

    @Override
    public boolean approveLoan() {
        // Auto loans may allow slightly lower credit score
        if (applicant.getCreditScore() >= 650 && applicant.getIncome() >= 30000) {
            setLoanStatus(true);
        } else {
            setLoanStatus(false);
        }
        return loanStatus;
    }

    @Override
    public double calculateEMI() {
        double P = applicant.getLoanAmount();
        double R = interestRate / 12 / 100;
        int N = termInMonths;

        // Auto loan may have slightly different EMI rounding
        double EMI = (P * R * Math.pow(1 + R, N)) / (Math.pow(1 + R, N) - 1);
        return Math.round(EMI * 100.0) / 100.0;
    }
}
