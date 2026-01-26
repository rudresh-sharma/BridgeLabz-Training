package com.csvdatahandling.question7;

import java.io.*;
import java.util.*;

class Employee {
    String id;
    String name;
    String department;
    double salary;

    public Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class SortBySalaryCSV {

    public static void main(String[] args) {

        String inputFile =
        "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question7\\employees.csv";

        String outputFile =
        "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question7\\employees_sorted.csv";

        List<Employee> employees = new ArrayList<>();

        // 1️⃣ Read CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

            String header = br.readLine(); // read header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");

                if (values.length < 4) {
                    System.out.println("⚠️ Skipping invalid line: " + line);
                    continue;
                }

                String id = values[0].trim();
                String name = values[1].trim();
                String dept = values[2].trim();
                double salary = Double.parseDouble(values[3].trim());

                employees.add(new Employee(id, name, dept, salary));
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading CSV file: " + e.getMessage());
            return;
        }

        // 2️⃣ Sort by salary DESCENDING
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));

        // 3️⃣ Write sorted data to new CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            // Write header
            bw.write("ID,Name,Department,Salary");
            bw.newLine();

            for (Employee e : employees) {
                String line = e.id + "," + e.name + "," + e.department + "," + e.salary;
                bw.write(line);
                bw.newLine();
            }

            System.out.println("✅ Sorted CSV saved to:");
            System.out.println(outputFile);

        } catch (IOException e) {
            System.out.println("❌ Error writing CSV file: " + e.getMessage());
            return;
        }

        // 4️⃣ Print Top 5 Highest Paid
        System.out.println("\n🏆 Top 5 Highest Paid Employees:");
        System.out.println("--------------------------------");

        int limit = Math.min(5, employees.size());

        for (int i = 0; i < limit; i++) {
            Employee e = employees.get(i);
            System.out.println(
                (i + 1) + ". ID: " + e.id +
                ", Name: " + e.name +
                ", Dept: " + e.department +
                ", Salary: " + e.salary
            );
        }
    }
}
