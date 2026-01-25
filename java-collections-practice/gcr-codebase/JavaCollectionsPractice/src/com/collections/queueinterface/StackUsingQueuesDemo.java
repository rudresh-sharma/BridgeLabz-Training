package com.collections.queueinterface;
/*
Question:
Implement a Stack Using Queues

Implement a stack data structure using two queues and support the following operations:
1. push(x) – Insert an element into the stack
2. pop() – Remove and return the top element of the stack
3. top() – Return the top element without removing it

Use only queue operations.
Example:
Push 1, 2, 3 → Pop → Output: 3
*/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class StackUsingQueues {

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();

    // Push operation
    public void push(int x) {
        // Step 1: Add element to empty queue q2
        q2.add(x);

        // Step 2: Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // Step 3: Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Pop operation
    public int pop() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return q1.remove();
    }

    // Top operation
    public int top() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return q1.peek();
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return q1.isEmpty();
    }
}

public class StackUsingQueuesDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StackUsingQueues stack = new StackUsingQueues();

        System.out.print("Enter number of elements to push: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            stack.push(sc.nextInt());
        }

        System.out.println("\nTop element: " + stack.top());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Top after pop: " + stack.top());

        sc.close();
    }
}
