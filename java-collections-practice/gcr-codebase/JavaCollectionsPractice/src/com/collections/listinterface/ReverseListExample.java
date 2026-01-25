package com.collections.listinterface;
import java.util.*;

public class ReverseListExample {

    // Generic method to reverse any List
    public static void reverseList(List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            Integer temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        // ---------- ArrayList ----------
        List<Integer> arrayList = new ArrayList<>();
        System.out.println("Enter elements for ArrayList:");
        for (int i = 0; i < n; i++) {
            arrayList.add(sc.nextInt());
        }

        reverseList(arrayList);
        System.out.println("Reversed ArrayList: " + arrayList);

        // ---------- LinkedList ----------
        List<Integer> linkedList = new LinkedList<>();
        System.out.println("\nEnter elements for LinkedList:");
        for (int i = 0; i < n; i++) {
            linkedList.add(sc.nextInt());
        }

        reverseList(linkedList);
        System.out.println("Reversed LinkedList: " + linkedList);

        sc.close();
    }
}
