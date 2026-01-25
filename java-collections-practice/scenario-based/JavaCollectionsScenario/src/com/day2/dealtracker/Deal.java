package com.day2.dealtracker;

import java.time.LocalDate;

public class Deal {

    private String dealCode;
    private LocalDate validTill;
    private int discountPercent;
    private int minimumPurchase;

    public Deal(String dealCode, LocalDate validTill, int discountPercent, int minimumPurchase) {
        this.dealCode = dealCode;
        this.validTill = validTill;
        this.discountPercent = discountPercent;
        this.minimumPurchase = minimumPurchase;
    }

    public String getDealCode() { return dealCode; }
    public LocalDate getValidTill() { return validTill; }
    public int getDiscountPercent() { return discountPercent; }
    public int getMinimumPurchase() { return minimumPurchase; }

    @Override
    public String toString() {
        return "DealCode: " + dealCode +
               " | ValidTill: " + validTill +
               " | Discount: " + discountPercent + "%" +
               " | MinimumPurchase: " + minimumPurchase;
    }
}
