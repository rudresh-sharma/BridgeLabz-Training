package com.collections.listinterface;
import java.util.*;

public class RemoveDuplicatesPreserveOrder {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        // Remove duplicates while preserving order
        Set<Integer> set = new LinkedHashSet<>(list);

        // Convert back to List
        List<Integer> result = new ArrayList<>(set);

        System.out.println("\nList after removing duplicates:");
        System.out.println(result);

        sc.close();
    }
}
