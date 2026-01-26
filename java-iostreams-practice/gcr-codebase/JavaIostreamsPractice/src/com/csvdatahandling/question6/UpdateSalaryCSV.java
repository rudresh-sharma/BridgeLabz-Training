package com.csvdatahandling.question6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UpdateSalaryCSV {
    public static void main(String[] args) {
    	String inputFile =
    			"C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question6\\employees.csv";

    			String outputFile =
    			"C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question6\\employees_updated.csv";

 // Output CSV file path

        System.out.println("Working Directory = " + new java.io.File(".").getAbsolutePath());

        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String header = br.readLine();  // Read header
            if (header == null) {
                System.out.println("❌ CSV file is empty!");
                return;
            }
            updatedLines.add(header);       // Keep header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                if (values.length < 4) {
                    System.out.println("⚠️ Skipping invalid line: " + line);
                    continue;
                }

                String department = values[2].trim();
                double salary = Double.parseDouble(values[3].trim());

                if (department.equalsIgnoreCase("IT")) {
                    salary = salary * 1.10; // Increase by 10%
                }

                // Reconstruct the line
                String updatedLine = String.join(",", values[0].trim(),
                        values[1].trim(),
                        department,
                        String.format("%.2f", salary));
                updatedLines.add(updatedLine);
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading CSV file: " + e.getMessage());
            return;
        }

        // Write updated lines to new CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            for (String updatedLine : updatedLines) {
                bw.write(updatedLine);
                bw.newLine();
            }
            System.out.println("✅ Updated CSV saved to: " + outputFile);
        } catch (IOException e) {
            System.out.println("❌ Error writing CSV file: " + e.getMessage());
        }
    }
}
