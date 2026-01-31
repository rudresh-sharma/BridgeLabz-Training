package com.methodreferences.invoice;

public class Invoice {

    private String transactionId;

    // Constructor
    public Invoice(String transactionId) {
        this.transactionId = transactionId;
        System.out.println("Invoice created for Transaction ID: " + transactionId);
    }

    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "Invoice{transactionId='" + transactionId + "'}";
    }
}
