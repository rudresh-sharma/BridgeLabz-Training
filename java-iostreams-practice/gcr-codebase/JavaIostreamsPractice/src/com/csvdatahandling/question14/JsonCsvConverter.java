package com.csvdatahandling.question14;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class JsonCsvConverter {

    public static void main(String[] args) throws Exception {

        // ---- CHANGE THESE PATHS ACCORDING TO YOUR SYSTEM ----
        String jsonInput = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question14\\students.json";
        String csvFromJson = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question14\\students_from_json.csv";

        String csvInput = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question14\\employees.csv";
        String jsonFromCsv = "C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\csvdatahandling\\question14\\employees_from_csv.json";

        // 1️⃣ JSON -> CSV (Students)
        List<Student> students = readJson(jsonInput);
        writeStudentsCsv(students, csvFromJson);
        System.out.println("Students JSON converted to CSV.");

        // 2️⃣ CSV -> JSON (Employees)
        List<Employee> employees = readEmployeesCsv(csvInput);
        writeEmployeesJson(employees, jsonFromCsv);
        System.out.println("Employees CSV converted to JSON.");
    }

    // =====================================================
    // =============== JSON -> CSV (STUDENTS) ===============
    // =====================================================

    private static List<Student> readJson(String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new FileReader(filePath),
                new TypeReference<List<Student>>() {}
        );
    }

    private static void writeStudentsCsv(List<Student> students, String filePath) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            // Header
            String[] header = {"id", "name", "age", "department"};
            writer.writeNext(header);

            // Data
            for (Student s : students) {
                String[] row = {
                        String.valueOf(s.getId()),
                        s.getName(),
                        String.valueOf(s.getAge()),
                        s.getDepartment()
                };
                writer.writeNext(row);
            }
        }
    }

    // =====================================================
    // =============== CSV -> JSON (EMPLOYEES) ==============
    // =====================================================

    private static List<Employee> readEmployeesCsv(String filePath) throws Exception {
        List<Employee> employees = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            boolean isHeader = true;

            while ((line = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false; // skip header
                    continue;
                }

                Employee e = new Employee();
                e.setEmpId(Integer.parseInt(line[0]));
                e.setName(line[1]);
                e.setDesignation(line[2]);
                e.setDepartment(line[3]);
                e.setSalary(Integer.parseInt(line[4]));

                employees.add(e);
            }
        }

        return employees;
    }

    private static void writeEmployeesJson(List<Employee> employees, String filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter()
              .writeValue(new FileWriter(filePath), employees);
    }
}
