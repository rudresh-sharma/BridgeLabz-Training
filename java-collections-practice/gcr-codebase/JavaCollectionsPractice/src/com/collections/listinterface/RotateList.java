package com.collections.listinterface;
import java.util.*;

public class RotateList {

    public static void rotateList(List<Integer> list, int k) {
        int n = list.size();
        k = k % n; // handle rotations greater than size

        List<Integer> temp = new ArrayList<>();

        // Add elements from k to end
        for (int i = k; i < n; i++) {
            temp.add(list.get(i));
        }

        // Add elements from start to k
        for (int i = 0; i < k; i++) {
            temp.add(list.get(i));
        }

        // Copy back to original list
        for (int i = 0; i < n; i++) {
            list.set(i, temp.get(i));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        System.out.print("Enter rotation value: ");
        int k = sc.nextInt();

        rotateList(list, k);

        System.out.println("Rotated List: " + list);

        sc.close();
    }
}
