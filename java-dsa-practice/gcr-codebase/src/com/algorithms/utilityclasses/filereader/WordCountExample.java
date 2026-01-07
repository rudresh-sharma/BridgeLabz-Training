/*
 * FileReader Problem 2: Count the Occurrence of a Word in a File Using FileReader
Problem:
Write a program that uses FileReader and BufferedReader to read a file and count how many times a specific word appears in the file.
Approach:
Create a FileReader to read from the file and wrap it in a BufferedReader.
Initialize a counter variable to keep track of word occurrences.
For each line in the file, split it into words and check if the target word exists.
Increment the counter each time the word is found.
Print the final count.

 */

package com.algorithms.utilityclasses.filereader;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WordCountExample {
    public static void main(String[] args) {
        // Full path to your file
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-dsa-practice\\gcr-codebase\\src\\com\\algorithms\\utilityclasses\\filereader\\example.txt";
        
        // Word to count
        String targetWord = "Java";
        int count = 0;

        try (FileReader fr = new FileReader(filePath);
             BufferedReader br = new BufferedReader(fr)) {

            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {
                // Split line into words using space and punctuation as delimiters
                String[] words = line.split("\\W+"); // \W+ splits by non-word characters
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) { // case-insensitive comparison
                        count++;
                    }
                }
            }

            System.out.println("The word \"" + targetWord + "\" appears " + count + " times in the file.");

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
