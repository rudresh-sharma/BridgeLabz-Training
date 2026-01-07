/*
 * StringBuffer Problem 1: Concatenate Strings Efficiently Using StringBuffer
Problem:
You are given an array of strings. Write a program that uses StringBuffer to concatenate all the strings in the array efficiently.
Approach:
Create a new StringBuffer object.
Iterate through each string in the array and append it to the StringBuffer.
Return the concatenated string after the loop finishes.
Using StringBuffer ensures efficient string concatenation due to its mutable nature. 
 */

package com.algorithms.utilityclasses.stringbuffer;
public class ConcatenateUsingStringBuffer {
    public static void main(String[] args) {

        String[] words = {"Java", " ", "Full", " ", "Stack", " ", "Developer"};

        // Step 1: Create StringBuffer object
        StringBuffer sb = new StringBuffer();

        // Step 2: Append each string
        for (String word : words) {
            sb.append(word);
        }

        // Step 3: Convert to String
        String result = sb.toString();

        System.out.println("Concatenated String: " + result);
    }
}
