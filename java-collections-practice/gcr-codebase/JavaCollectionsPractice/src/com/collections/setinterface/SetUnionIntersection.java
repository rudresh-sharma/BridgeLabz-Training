package com.collections.setinterface;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SetUnionIntersection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input Set 1
        System.out.print("Enter number of elements in Set 1: ");
        int n1 = sc.nextInt();
        Set<Integer> set1 = new HashSet<>();

        System.out.println("Enter elements of Set 1:");
        for (int i = 0; i < n1; i++) {
            set1.add(sc.nextInt());
        }

        // Input Set 2
        System.out.print("\nEnter number of elements in Set 2: ");
        int n2 = sc.nextInt();
        Set<Integer> set2 = new HashSet<>();

        System.out.println("Enter elements of Set 2:");
        for (int i = 0; i < n2; i++) {
            set2.add(sc.nextInt());
        }

        // Union
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);

        // Intersection
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        // Output
        System.out.println("\nUnion: " + union);
        System.out.println("Intersection: " + intersection);

        sc.close();
    }
}
