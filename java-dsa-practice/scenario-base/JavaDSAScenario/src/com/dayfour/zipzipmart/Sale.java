package com.dayfour.zipzipmart;
class Sale {
    String transactionId;
    String date;     // format: YYYY-MM-DD
    double amount;

    Sale(String id, String date, double amount) {
        this.transactionId = id;
        this.date = date;
        this.amount = amount;
    }

    public String toString() {
        return transactionId + " | " + date + " | ₹" + amount;
    }
}
