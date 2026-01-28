package com.day1.markmate.util;

import com.day1.markmate.model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CSVReaderUtil {

    public static List<Student> readCSV(String filePath) {

        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String headerLine = br.readLine(); // Subject names
            String[] headers = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");
                String studentId = data[0];
                String name = data[1];

                Map<String, Integer> marks = new HashMap<>();

                for (int i = 2; i < headers.length; i++) {
                    try {
                        marks.put(headers[i], Integer.parseInt(data[i]));
                    } catch (NumberFormatException e) {
                        marks.put(headers[i], 0); // Invalid marks defaulted to 0
                    }
                }

                students.add(new Student(studentId, name, marks));
            }

        } catch (Exception e) {
            System.out.println("❌ Error reading CSV: " + e.getMessage());
        }

        return students;
    }
}
