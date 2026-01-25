package com.collections.setinterface;
import java.util.*;

public class SetToSortedList {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input Set
        System.out.print("Enter number of elements in the set: ");
        int n = sc.nextInt();
        Set<Integer> set = new HashSet<>();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            set.add(sc.nextInt());
        }

        // Convert to List
        List<Integer> sortedList = new ArrayList<>(set);

        // Sort List
        Collections.sort(sortedList);

        // Output
        System.out.println("\nSorted List: " + sortedList);

        sc.close();
    }
}
