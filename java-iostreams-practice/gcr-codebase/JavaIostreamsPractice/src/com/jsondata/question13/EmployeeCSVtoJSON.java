package com.jsondata.question13;

import com.opencsv.CSVReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileReader;
import java.io.File;
import java.util.Arrays;

public class EmployeeCSVtoJSON {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question13\\employee_report.csv";
        String jsonFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question13\\employee_report.json";

        try {
            CSVReader reader = new CSVReader(new FileReader(csvFile));
            ObjectMapper mapper = new ObjectMapper();
            ArrayNode employeesArray = mapper.createArrayNode();

            String[] header = reader.readNext(); // Read header line
            String[] line;

            while ((line = reader.readNext()) != null) {
                ObjectNode employee = mapper.createObjectNode();

                for (int i = 0; i < header.length; i++) {
                    String key = header[i].trim();
                    String value = line[i].trim();

                    if (key.equalsIgnoreCase("Salary")) {
                        employee.put(key, Double.parseDouble(value));
                    } else if (key.equalsIgnoreCase("Employee ID")) {
                        employee.put(key.replace(" ", "_"), Integer.parseInt(value)); // Replace space for JSON key
                    } else {
                        employee.put(key.replace(" ", "_"), value);
                    }
                }

                employeesArray.add(employee);
            }

            // Print JSON in console
            System.out.println("Employee JSON Data:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeesArray));

            // Save JSON to file
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFile), employeesArray);
            System.out.println("JSON saved to: " + jsonFile);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
