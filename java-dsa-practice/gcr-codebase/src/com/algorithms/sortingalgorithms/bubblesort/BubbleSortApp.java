package com.algorithms.sortingalgorithms.bubblesort;
import java.util.Scanner;

// Main class with user input
public class BubbleSortApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] marks = new int[n];

        System.out.println("Enter student marks:");
        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        StudentMarks sm = new StudentMarks(marks);

        System.out.println("\nMarks before sorting:");
        sm.display();

        // Sorting
        BubbleSort.sort(sm.getMarks());

        System.out.println("\nMarks after Bubble Sort:");
        sm.display();
    }
}