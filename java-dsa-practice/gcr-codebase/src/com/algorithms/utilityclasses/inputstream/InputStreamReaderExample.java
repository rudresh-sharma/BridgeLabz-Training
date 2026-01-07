/*
 * InputStreamReader Problem 1: Convert Byte Stream to Character Stream Using InputStreamReader
Problem:
Write a program that uses InputStreamReader to read binary data from a file and print it as characters. The file contains data encoded in a specific charset (e.g., UTF-8).
Approach:
Create a FileInputStream object to read the binary data from the file.
Wrap the FileInputStream in an InputStreamReader to convert the byte stream into a character stream.
Use a BufferedReader to read characters efficiently from the InputStreamReader.
Read the file line by line and print the characters to the console.
Handle any encoding exceptions as needed.

 */
package com.algorithms.utilityclasses.inputstream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class InputStreamReaderExample {
    public static void main(String[] args) {
        // Full path to your file
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-dsa-practice\\gcr-codebase\\src\\com\\algorithms\\utilityclasses\\inputstream\\example.txt";

        try (
            // Create a FileInputStream to read bytes from the file
            FileInputStream fis = new FileInputStream(filePath);
            // Wrap the byte stream in InputStreamReader to convert bytes to characters
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            // Wrap InputStreamReader in BufferedReader for efficient reading
            BufferedReader br = new BufferedReader(isr)
        ) {
            String line;
            // Read the file line by line and print it
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
