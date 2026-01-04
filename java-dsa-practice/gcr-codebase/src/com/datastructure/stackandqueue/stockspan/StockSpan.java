package com.datastructure.stackandqueue.stockspan;

import java.util.Stack;

public class StockSpan {

    public static int[] calculateSpan(int[] price) {
        int n = price.length;
        int[] span = new int[n];

        Stack<Integer> stack = new Stack<>(); // stores indices

        stack.push(0);
        span[0] = 1;

        for (int i = 1; i < n; i++) {

            while (!stack.isEmpty() && price[i] >= price[stack.peek()]) {
                stack.pop();
            }

            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        return span;
    }
}
