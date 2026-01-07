/*
 * StringBuilder Problem 2: Remove Duplicates from a String Using StringBuilder
Problem:
Write a program that uses StringBuilder to remove all duplicate characters from a given string while maintaining the original order.
Approach:
Initialize an empty StringBuilder and a HashSet to keep track of characters.
Iterate over each character in the string:
If the character is not in the HashSet, append it to the StringBuilder and add it to the HashSet.
Return the StringBuilder as a string without duplicates.

 */

package com.algorithms.utilityclasses.stringbuilder;
import java.util.HashSet;
import java.util.Scanner;

public class RemoveDuplicatesUsingStringBuilder {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Step 1: Create StringBuilder
        StringBuilder sb = new StringBuilder();

        // Step 2: Create HashSet to track characters
        HashSet<Character> set = new HashSet<>();

        // Step 3: Traverse each character
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Step 4: If not already seen, append it
            if (!set.contains(ch)) {
                sb.append(ch);
                set.add(ch);
            }
        }

        // Step 5: Convert StringBuilder to String
        String result = sb.toString();

        System.out.println("String without duplicates: " + result);
    }
}
