package com.csvdatahandling.question10;

import java.io.*;
import java.util.*;

public class MergeTwoCSV {

    public static void main(String[] args) {

        String file1 = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question10\\students1.csv";

        String file2 = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question10\\students2.csv";

        String outputFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question10\\merged_students.csv";

        // Map<ID, "Name,Age">
        Map<String, String> basicInfoMap = new HashMap<>();

        // Step 1: Read students1.csv
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("⚠️ Skipping invalid row in students1: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                String age = parts[2].trim();

                basicInfoMap.put(id, name + "," + age);
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading students1.csv: " + e.getMessage());
            return;
        }

        // Step 2: Read students2.csv and merge
        List<String> mergedLines = new ArrayList<>();
        mergedLines.add("ID,Name,Age,Marks,Grade"); // header

        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {

            String line = br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("⚠️ Skipping invalid row in students2: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String marks = parts[1].trim();
                String grade = parts[2].trim();

                // Find matching record from students1
                if (basicInfoMap.containsKey(id)) {
                    String nameAge = basicInfoMap.get(id); // "Name,Age"

                    String mergedLine = id + "," + nameAge + "," + marks + "," + grade;
                    mergedLines.add(mergedLine);
                } else {
                    System.out.println("⚠️ No matching ID in students1 for ID: " + id);
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading students2.csv: " + e.getMessage());
            return;
        }

        // Step 3: Write merged file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            for (String row : mergedLines) {
                bw.write(row);
                bw.newLine();
            }

            System.out.println("✅ Merged CSV created at:");
            System.out.println(outputFile);

        } catch (IOException e) {
            System.out.println("❌ Error writing merged CSV: " + e.getMessage());
        }
    }
}
