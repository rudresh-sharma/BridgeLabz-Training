/*
 * Selection Sort - Sort Exam Scores
Problem Statement:
A university needs to sort students’ exam scores in ascending order. Implement Selection Sort to achieve this.
Hint:
Find the minimum element in the array.
Swap it with the first unsorted element.
Repeat the process for the remaining elements.

 */


package com.algorithms.sortingalgorithms.selectionsort;

import java.util.Scanner;

// Main class with user input
public class SelectionSortApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] scores = new int[n];

        System.out.println("Enter exam scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        ExamScores es = new ExamScores(scores);

        System.out.println("\nScores before sorting:");
        es.display();

        // Perform Selection Sort
        SelectionSort.sort(es.getScores());

        System.out.println("\nScores after Selection Sort:");
        es.display();
    }
}
