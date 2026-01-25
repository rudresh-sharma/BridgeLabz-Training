package com.collections.queueinterface;
import java.util.*;

public class GenerateBinaryNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        Queue<String> queue = new LinkedList<>();
        List<String> result = new ArrayList<>();

        // Start with first binary number
        queue.add("1");

        for (int i = 0; i < n; i++) {
            String current = queue.remove();
            result.add(current);

            queue.add(current + "0");
            queue.add(current + "1");
        }

        System.out.println("\nFirst " + n + " binary numbers:");
        System.out.println(result);

        sc.close();
    }
}
