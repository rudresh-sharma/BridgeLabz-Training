/*
 * Linear Search Problem 2: Search for a Specific Word in a List of Sentences
Problem:
You are given an array of sentences (strings). Write a program that performs Linear Search to find the first sentence containing a specific word. If the word is found, return the sentence. If no sentence contains the word, return "Not Found".
Approach:
Iterate through the list of sentences.
For each sentence, check if it contains the specific word.
If the word is found, return the current sentence.
If no sentence contains the word, return "Not Found".

 */
package com.algorithms.searchingalgorithms.linearsearch;

import java.util.Scanner;

public class WordSearchInSentences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of sentences
        System.out.print("Enter the number of sentences: ");
        int n = sc.nextInt();
        sc.nextLine(); // Consume newline left-over

        String[] sentences = new String[n];

        // Input sentences
        System.out.println("Enter the sentences:");
        for (int i = 0; i < n; i++) {
            sentences[i] = sc.nextLine();
        }

        // Input the word to search
        System.out.print("Enter the word to search: ");
        String word = sc.nextLine();

        // Perform linear search
        String result = findSentenceContainingWord(sentences, word);

        System.out.println("Result: " + result);

        sc.close();
    }

    // Linear search method to find first sentence containing the word
    public static String findSentenceContainingWord(String[] arr, String word) {
        for (String sentence : arr) {
            if (sentence.contains(word)) { // Check if sentence contains the word
                return sentence; // Return the first matching sentence
            }
        }
        return "Not Found"; // If no sentence contains the word
    }
}
