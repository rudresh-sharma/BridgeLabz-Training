package com.csvdatahandling.question1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSVDemo {

    public static void main(String[] args) {

        // 🔹 Sample CSV file path
        String csvFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question1\\studentduplicate.csv"; // Make sure this file exists in your project folder
        String line = "";
        String splitBy = ",";

        System.out.println("Reading CSV data:\n");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // Read header
            String header = br.readLine();
            System.out.println("Header: " + header);
            System.out.println();

            // Read each line
            while ((line = br.readLine()) != null) {
                // Trim line and skip if empty
                line = line.trim();
                if (line.isEmpty()) continue;

                // Split by comma
                String[] student = line.split(splitBy);

                // Skip if line has fewer than 4 columns
                if (student.length < 4) {
                    System.out.println("⚠️ Skipping invalid line: " + line);
                    continue;
                }

                String id = student[0].trim();
                String name = student[1].trim();
                String age = student[2].trim();
                String marks = student[3].trim();

                System.out.println("Student ID: " + id +
                                   ", Name: " + name +
                                   ", Age: " + age +
                                   ", Marks: " + marks);
            }


        } catch (IOException e) {
            System.err.println("❌ Error reading CSV file: " + e.getMessage());
        }
    }
}
