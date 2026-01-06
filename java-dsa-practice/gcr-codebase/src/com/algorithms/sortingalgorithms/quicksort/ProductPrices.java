package com.algorithms.sortingalgorithms.quicksort;

// Stores and manages product prices
public class ProductPrices {

    private double[] prices;

    // Constructor
    public ProductPrices(double[] prices) {
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
