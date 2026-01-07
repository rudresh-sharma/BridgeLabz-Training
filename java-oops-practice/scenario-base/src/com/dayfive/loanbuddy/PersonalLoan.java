package com.dayfive.loanbuddy;

public class PersonalLoan extends LoanApplication {

    public PersonalLoan(Applicant applicant, int termInMonths, double interestRate) {
        super(applicant, "PersonalLoan", termInMonths, interestRate);
    }

    @Override
    public boolean approveLoan() {
        if (applicant.getCreditScore() >= 700 && applicant.getIncome() >= 20000) {
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

        double EMI = (P * R * Math.pow(1 + R, N)) / (Math.pow(1 + R, N) - 1);
        return EMI;
    }
}
