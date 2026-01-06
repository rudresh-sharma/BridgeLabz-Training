/*
 * . Quick Sort - Sort Product Prices
Problem Statement:
An e-commerce company wants to display product prices in ascending order. Implement Quick Sort to sort the product prices.
Hint:
Pick a pivot element (first, last, or random).
Partition the array such that elements smaller than the pivot are on the left and larger ones are on the right.
Recursively apply Quick Sort on left and right partitions.

 */

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
