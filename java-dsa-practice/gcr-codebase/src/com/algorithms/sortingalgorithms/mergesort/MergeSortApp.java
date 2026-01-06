package com.algorithms.sortingalgorithms.mergesort;

import java.util.Scanner;

// Main class with user input
public class MergeSortApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();

        double[] prices = new double[n];

        System.out.println("Enter book prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextDouble();
        }

        BookPrices bp = new BookPrices(prices);

        System.out.println("\nBook prices before sorting:");
        bp.display();

        // Perform Merge Sort
        MergeSort.sort(bp.getPrices(), 0, n - 1);

        System.out.println("\nBook prices after Merge Sort:");
        bp.display();
    }
}
