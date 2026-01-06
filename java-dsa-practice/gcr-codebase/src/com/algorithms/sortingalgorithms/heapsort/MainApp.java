/*6. Heap Sort - Sort Job Applicants by Salary
Problem Statement:
A company receives job applications with different expected salary demands. Implement Heap Sort to sort these salary demands in ascending order.
Hint:
Build a Max Heap from the array.
Extract the largest element (root) and place it at the end.
Reheapify the remaining elements and repeat until sorted.

*/


package com.algorithms.sortingalgorithms.heapsort;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of applicants: ");
        int n = sc.nextInt();

        Applicant[] applicants = new Applicant[n];

        System.out.println("Enter expected salaries:");
        for (int i = 0; i < n; i++) {
            int sal = sc.nextInt();
            applicants[i] = new Applicant(sal);
        }

        HeapSort.heapSort(applicants);

        System.out.println("\nSorted Salaries (Ascending):");
        for (Applicant a : applicants) {
            System.out.print(a.getSalary() + " ");
        }
    }
}
