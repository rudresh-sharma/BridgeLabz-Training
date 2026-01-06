/*
 * 2. Insertion Sort - Sort Employee IDs
Problem Statement:
A company stores employee IDs in an unsorted array. Implement Insertion Sort to sort the employee IDs in ascending order.
Hint:
Divide the array into sorted and unsorted parts.
Pick an element from the unsorted part and insert it into its correct position in the sorted part.
Repeat for all elements.

 */

package com.algorithms.sortingalgorithms.insertionsort;

import java.util.Scanner;

// Main class with user input
public class InsertionSortApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();

        int[] ids = new int[n];

        System.out.println("Enter Employee IDs:");
        for (int i = 0; i < n; i++) {
            ids[i] = sc.nextInt();
        }

        EmployeeIDs emp = new EmployeeIDs(ids);

        System.out.println("\nEmployee IDs before sorting:");
        emp.display();

        // Perform insertion sort
        InsertionSort.sort(emp.getIds());

        System.out.println("\nEmployee IDs after Insertion Sort:");
        emp.display();
    }
}
