package com.csvdatahandling.question12;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DetectCsvDuplicates {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question1\\studentduplicate.csv"; // Make sure this file exists in your project folder
// Replace with your CSV file path
        String line;
        String splitBy = ","; // CSV separator
        Map<String, List<String>> idToRows = new HashMap<>(); // Map to store rows by ID

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read header
            String header = br.readLine();
            if (header == null) {
                System.out.println("CSV file is empty.");
                return;
            }

            // Read all rows
            while ((line = br.readLine()) != null) {
                String[] values = line.split(splitBy);
                String id = values[0]; // Assuming first column is ID
                idToRows.computeIfAbsent(id, k -> new ArrayList<>()).add(line);
            }

            // Print duplicates
            boolean hasDuplicates = false;
            for (Map.Entry<String, List<String>> entry : idToRows.entrySet()) {
                if (entry.getValue().size() > 1) {
                    hasDuplicates = true;
                    System.out.println("Duplicate ID: " + entry.getKey());
                    for (String row : entry.getValue()) {
                        System.out.println(row);
                    }
                    System.out.println("-----");
                }
            }

            if (!hasDuplicates) {
                System.out.println("No duplicates found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
