/*
 * StringBuilder Problem 1: Reverse a String Using StringBuilder
Problem:
Write a program that uses StringBuilder to reverse a given string. For example, if the input is "hello", the output should be "olleh".
Approach:
Create a new StringBuilder object.
Append the string to the StringBuilder.
Use the reverse() method of StringBuilder to reverse the string.
Convert the StringBuilder back to a string and return it.
 */
package com.algorithms.utilityclasses.stringbuilder;
import java.util.Scanner;

public class ReverseStringUsingStringBuilder {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Step 1: Create StringBuilder object
        StringBuilder sb = new StringBuilder();

        // Step 2: Append the string
        sb.append(input);

        // Step 3: Reverse the string
        sb.reverse();

        // Step 4: Convert back to String
        String reversed = sb.toString();

        System.out.println("Reversed string: " + reversed);
    }
}
