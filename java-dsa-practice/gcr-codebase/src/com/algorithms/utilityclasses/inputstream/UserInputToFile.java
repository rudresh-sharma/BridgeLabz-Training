package com.algorithms.utilityclasses.inputstream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserInputToFile {
    public static void main(String[] args) {
        // File where user input will be written
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-dsa-practice\\gcr-codebase\\src\\com\\algorithms\\utilityclasses\\inputstream\\user_input.txt";

        try (
            // Read user input from console
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            // Write input to file
            FileWriter fw = new FileWriter(filePath, true) // true = append mode
        ) {
            System.out.println("Enter text to write to file. Type 'exit' to stop:");

            String inputLine;
            while (true) {
                System.out.print("> "); // prompt
                inputLine = br.readLine();
                if ("exit".equalsIgnoreCase(inputLine)) {
                    break; // stop on "exit"
                }
                fw.write(inputLine + System.lineSeparator()); // write line to file
            }

            System.out.println("User input has been saved to file.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
