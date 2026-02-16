package com.dbmsjdbc.hms.model;

public class Bill {

    private int visitId;
    private double amount;

    public Bill(int visitId, double amount) {
        this.visitId = visitId;
        this.amount = amount;
    }

    public int getVisitId() { return visitId; }
    public double getAmount() { return amount; }
}
