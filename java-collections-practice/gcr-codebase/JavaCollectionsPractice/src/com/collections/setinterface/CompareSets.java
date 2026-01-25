package com.collections.setinterface.comparesets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CompareSets {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // First Set
        System.out.print("Enter number of elements in Set 1: ");
        int n1 = sc.nextInt();
        Set<Integer> set1 = new HashSet<>();

        System.out.println("Enter elements of Set 1:");
        for (int i = 0; i < n1; i++) {
            set1.add(sc.nextInt());
        }

        // Second Set
        System.out.print("\nEnter number of elements in Set 2: ");
        int n2 = sc.nextInt();
        Set<Integer> set2 = new HashSet<>();

        System.out.println("Enter elements of Set 2:");
        for (int i = 0; i < n2; i++) {
            set2.add(sc.nextInt());
        }

        // Compare sets
        if (set1.equals(set2)) {
            System.out.println("\nOutput: true (Both sets are equal)");
        } else {
            System.out.println("\nOutput: false (Sets are not equal)");
        }

        sc.close();
    }
}
