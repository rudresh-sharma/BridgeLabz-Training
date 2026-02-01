package com.streamapi.foreach.stockpricelogger;

public class Stock {
    private String symbol;
    private double price;
    private String time;

    // constructor
    public Stock(String symbol, double price, String time) {
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

    // good string representation
    @Override
    public String toString() {
        return symbol + " -> Rs. " + price + " (" + time + ")";
    }
}