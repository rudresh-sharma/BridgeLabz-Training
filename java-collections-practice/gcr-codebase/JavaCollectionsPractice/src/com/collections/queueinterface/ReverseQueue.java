package com.collections.queueinterface;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ReverseQueue {

    // Recursive method using only queue operations
    public static void reverseQueue(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }

        int front = queue.remove();
        reverseQueue(queue);
        queue.add(front);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }

        reverseQueue(queue);

        System.out.println("\nReversed Queue: " + queue);

        sc.close();
    }
}
