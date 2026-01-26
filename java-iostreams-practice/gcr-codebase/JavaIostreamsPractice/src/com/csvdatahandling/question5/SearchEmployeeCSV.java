package com.csvdatahandling.question5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SearchEmployeeCSV {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter CSV file path: ");
        String filePath = sc.nextLine();

        System.out.print("Enter employee name to search: ");
        String searchName = sc.nextLine().trim().toLowerCase();

        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String header = br.readLine(); // Read header
            if (header == null) {
                System.out.println("❌ CSV file is empty!");
                return;
            }

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip blank lines

                String[] values = line.split(",");
                if (values.length < 4) {
                    System.out.println("⚠️ Skipping invalid line: " + line);
                    continue;
                }

                String empName = values[1].trim().toLowerCase();
                if (empName.equals(searchName)) {
                    System.out.println("Employee Found!");
                    System.out.println("Name: " + values[1].trim());
                    System.out.println("Department: " + values[2].trim());
                    System.out.println("Salary: " + values[3].trim());
                    found = true;
                    break; // Stop after first match
                }
            }

            if (!found) {
                System.out.println("❌ Employee not found: " + searchName);
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading CSV file: " + e.getMessage());
        }

        sc.close();
    }
}
