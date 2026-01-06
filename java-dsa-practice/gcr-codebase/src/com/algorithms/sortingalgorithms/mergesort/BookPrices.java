package com.algorithms.sortingalgorithms.mergesort;

// Stores and manages book prices
public class BookPrices {

    private double[] prices;

    // Constructor
    public BookPrices(double[] prices) {
        this.prices = prices;
    }

    // Getter
    public double[] getPrices() {
        return prices;
    }

    // Display prices
    public void display() {
        for (double p : prices) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}
