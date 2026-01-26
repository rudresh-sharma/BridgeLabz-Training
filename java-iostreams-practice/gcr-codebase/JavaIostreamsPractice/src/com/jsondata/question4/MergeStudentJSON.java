package com.jsondata.question4;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MergeStudentJSON {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // JSON objects for student 1
            String student1Part1 = "{ \"name\": \"Rudresh Sharma\", \"age\": 21 }";
            String student1Part2 = "{ \"email\": \"rudresh@example.com\", \"subjects\": [\"Math\", \"Physics\"] }";

            // JSON objects for student 2
            String student2Part1 = "{ \"name\": \"Anita Verma\", \"age\": 22 }";
            String student2Part2 = "{ \"email\": \"anita@example.com\", \"subjects\": [\"Biology\", \"Chemistry\"] }";

            // Merge each student's parts
            List<ObjectNode> mergedStudents = new ArrayList<>();

            ObjectNode s1 = (ObjectNode) mapper.readTree(student1Part1);
            s1.setAll((ObjectNode) mapper.readTree(student1Part2));
            mergedStudents.add(s1);

            ObjectNode s2 = (ObjectNode) mapper.readTree(student2Part1);
            s2.setAll((ObjectNode) mapper.readTree(student2Part2));
            mergedStudents.add(s2);

            // Save all merged students into one JSON file
            File file = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question4\\students_merged.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, mergedStudents);

            System.out.println("Merged students JSON saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
