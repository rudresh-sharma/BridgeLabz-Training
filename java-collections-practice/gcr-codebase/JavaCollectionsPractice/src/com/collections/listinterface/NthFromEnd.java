package com.collections.listinterface;
import java.util.LinkedList;
import java.util.Scanner;

public class NthFromEnd {

    public static String findNthFromEnd(LinkedList<String> list, int n) {

        if (n <= 0 || n > list.size()) {
            return "Invalid N value";
        }

        int slow = 0;
        int fast = n;

        while (fast < list.size()) {
            slow++;
            fast++;
        }

        return list.get(slow);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LinkedList<String> list = new LinkedList<>();

        System.out.print("Enter number of elements: ");
        int size = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Enter elements:");
        for (int i = 0; i < size; i++) {
            list.add(sc.nextLine());
        }

        System.out.print("Enter N (Nth element from end): ");
        int n = sc.nextInt();

        String result = findNthFromEnd(list, n);

        System.out.println("\nNth element from end: " + result);

        sc.close();
    }
}
