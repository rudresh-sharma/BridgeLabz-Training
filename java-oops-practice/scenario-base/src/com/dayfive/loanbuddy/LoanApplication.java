package com.dayfive.loanbuddy;

public abstract class LoanApplication implements IApprovable {
    protected Applicant applicant;
    protected String loanType;
    protected int termInMonths;
    protected double interestRate;
    protected boolean loanStatus;   // true = approved, false = rejected

    // Constructor
    public LoanApplication(Applicant applicant, String loanType, int termInMonths, double interestRate) {
        this.applicant = applicant;
        this.loanType = loanType;
        this.termInMonths = termInMonths;
        this.interestRate = interestRate;
        this.loanStatus = false; // default
    }

    // Loan status should not be publicly set (encapsulation)
    protected void setLoanStatus(boolean status) {
        this.loanStatus = status;
    }

    public boolean getLoanStatus() {
        return loanStatus;
    }

    public String getLoanType() {
        return loanType;
    }

    public int getTermInMonths() {
        return termInMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public Applicant getApplicant() {
        return applicant;
    }
}
