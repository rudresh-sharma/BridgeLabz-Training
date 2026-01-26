package com.csvdatahandling.question3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountCSVRows {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question1\\student.csv"; // CSV file path

        int rowCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Skip the header row
            if ((line = br.readLine()) != null) {
                System.out.println("Header: " + line);
            }

            // Count remaining lines
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) { // skip blank lines
                    rowCount++;
                }
            }


            System.out.println("✅ Number of records (excluding header): " + rowCount);

        } catch (IOException e) {
            System.out.println("❌ Error reading CSV file: " + e.getMessage());
        }
    }
}
