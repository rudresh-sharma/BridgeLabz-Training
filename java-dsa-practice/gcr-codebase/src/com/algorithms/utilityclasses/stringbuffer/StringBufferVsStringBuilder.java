/*
 * StringBuffer Problem 2: Compare StringBuffer with StringBuilder for String Concatenation
Problem:
Write a program that compares the performance of StringBuffer and StringBuilder for concatenating strings. For large datasets (e.g., concatenating 1 million strings), compare the execution time of both classes.
Approach:
Initialize two StringBuffer and StringBuilder objects.
Perform string concatenation in both objects, appending 1 million strings (e.g., "hello").
Measure the time taken to complete the concatenation using System.nanoTime() for both StringBuffer and StringBuilder.
Output the time taken by both classes for comparison.

 */

package com.algorithms.utilityclasses.stringbuffer;
public class StringBufferVsStringBuilder {
    public static void main(String[] args) {

        int count = 1_000_000;
        String text = "hello";

        // ---------- StringBuilder ----------
        StringBuilder sb = new StringBuilder();
        long startBuilder = System.nanoTime();

        for (int i = 0; i < count; i++) {
            sb.append(text);
        }

        long endBuilder = System.nanoTime();
        long builderTime = endBuilder - startBuilder;

        // ---------- StringBuffer ----------
        StringBuffer sbuf = new StringBuffer();
        long startBuffer = System.nanoTime();

        for (int i = 0; i < count; i++) {
            sbuf.append(text);
        }

        long endBuffer = System.nanoTime();
        long bufferTime = endBuffer - startBuffer;

        // ---------- Results ----------
        System.out.println("StringBuilder Time: " + builderTime + " ns");
        System.out.println("StringBuffer Time : " + bufferTime + " ns");

        if (builderTime < bufferTime) {
            System.out.println("StringBuilder is faster.");
        } else {
            System.out.println("StringBuffer is faster.");
        }
    }
}
