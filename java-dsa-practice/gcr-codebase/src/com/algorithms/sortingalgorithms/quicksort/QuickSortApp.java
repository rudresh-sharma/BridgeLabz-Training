package com.algorithms.sortingalgorithms.quicksort;

import java.util.Scanner;

// Main class with user input
public class QuickSortApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        double[] prices = new double[n];

        System.out.println("Enter product prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextDouble();
        }

        ProductPrices pp = new ProductPrices(prices);

        System.out.println("\nProduct prices before sorting:");
        pp.display();

        // Perform Quick Sort
        QuickSort.sort(pp.getPrices(), 0, n - 1);

        System.out.println("\nProduct prices after Quick Sort:");
        pp.display();
    }
}
