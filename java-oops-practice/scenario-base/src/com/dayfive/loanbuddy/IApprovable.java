package com.dayfive.loanbuddy;

public interface IApprovable {
    boolean approveLoan();       // Approve or reject loan
    double calculateEMI();       // Calculate EMI based on loan type
}
