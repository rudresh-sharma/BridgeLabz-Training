package com.csvdatahandling.question4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FilterCSVDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // Read header
            if (header == null) {
                System.out.println("❌ CSV file is empty!");
                return;
            }

            System.out.println("Header: " + header);
            System.out.println("Students with Marks > 80:");

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip blank lines
                String[] values = line.split(",");
                
                if (values.length < 4) {
                    System.out.println("⚠️ Skipping invalid line: " + line);
                    continue;
                }

                try {
                    int marks = Integer.parseInt(values[3].trim());
                    if (marks > 80) {
                        System.out.println("Student ID: " + values[0] +
                                           ", Name: " + values[1] +
                                           ", Age: " + values[2] +
                                           ", Marks: " + values[3]);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Invalid marks for student: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading CSV file: " + e.getMessage());
        }

        sc.close();
    }
}
