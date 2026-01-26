package com.csvdatahandling.question11;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadLargeCSVInChunks {

    public static void main(String[] args) {

        String inputFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question11\\students_large.csv";

        int CHUNK_SIZE = 100;   // Process 100 lines at a time
        long totalRecords = 0;

        List<String> buffer = new ArrayList<>(CHUNK_SIZE);

        long startTime = System.currentTimeMillis();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String header = br.readLine(); // skip header
            if (header == null) {
                System.out.println("❌ CSV file is empty!");
                return;
            }

            String line;

            while ((line = br.readLine()) != null) {

                buffer.add(line);

                // When buffer reaches 100 lines, process it
                if (buffer.size() == CHUNK_SIZE) {
                    processChunk(buffer);
                    totalRecords += buffer.size();
                    buffer.clear(); // free memory
                }
            }

            // Process remaining lines (< 100)
            if (!buffer.isEmpty()) {
                processChunk(buffer);
                totalRecords += buffer.size();
                buffer.clear();
            }

            long endTime = System.currentTimeMillis();
            double seconds = (endTime - startTime) / 1000.0;  // divide by 1000 to convert ms -> sec

            System.out.println("✅ Total Records Processed: " + totalRecords);
            System.out.println("⏱️ Time Taken: " + seconds + " seconds");

        } catch (IOException e) {
            System.out.println("❌ Error reading large CSV: " + e.getMessage());
        }
    }

    // Simulate processing of each chunk
    private static void processChunk(List<String> chunk) {

        // Here you can parse, filter, validate, insert into DB, etc.
        // For demo, we just print the size of the chunk

        System.out.println("Processed chunk of size: " + chunk.size());

        // Example: count or simple parse
        for (String row : chunk) {
            // String[] parts = row.split(",");
            // Do processing here
        }
    }
}
