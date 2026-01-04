
package com.datastructure.stackandqueue.sortstackusingrecursion;

import java.util.Stack;

public class MainApp {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        stack.push(30);
        stack.push(10);
        stack.push(50);
        stack.push(20);

        System.out.println("Original Stack: " + stack);

        SortStack.sort(stack);

        System.out.println("Sorted Stack: " + stack);
    }
}
