package com.csvdatahandling.question9;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadStudentCSV {

    public static void main(String[] args) {

        // 👉 Give absolute path if needed
        String filePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question9\\students.csv";

        List<Student> studentList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line = br.readLine(); // Read header and skip
            System.out.println("Header: " + line);

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");

                if (values.length != 4) {
                    System.out.println("⚠️ Skipping invalid row: " + line);
                    continue;
                }

                int id = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                int age = Integer.parseInt(values[2].trim());
                int marks = Integer.parseInt(values[3].trim());

                // Convert row into Student object
                Student student = new Student(id, name, age, marks);

                studentList.add(student);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("❌ Error reading CSV: " + e.getMessage());
            return;
        }

        // Print all Student objects
        System.out.println("\n📋 Students converted into Java Objects:\n");

        for (Student s : studentList) {
            System.out.println(s);
        }
    }
}
