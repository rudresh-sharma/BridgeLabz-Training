/*
 * 6. RoboWarehouse – Shelf Loading Robot (Insertion Sort)
Story: A robot loads packages onto shelves based on weight. It adds one package at a time
and must maintain an ascending order by weight for balance. Insertion Sort helps since new
items are inserted into the sorted list dynamically.
Key Concepts:
● Insertion into sorted lists
● Low memory footprint
● Ideal for streaming insertions
 */


package com.dayfive.robowarehouse;

import java.util.Scanner;

public class WareHouseMain {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter number of weights");
        int n = in.nextInt();

        int[] weights = new int[n];

        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();

            // insert the newly added weight into sorted part
            InsertionSort.insertSorted(weights, i);
        }

        System.out.println("Sorted Weights:");
        for (int w : weights) {
            System.out.print(w + " ");
        }
    }
}
