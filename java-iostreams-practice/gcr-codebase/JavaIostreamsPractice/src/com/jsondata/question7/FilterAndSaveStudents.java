package com.jsondata.question7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilterAndSaveStudents {
    public static void main(String[] args) {
        try {
            // 1️⃣ Create ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // 2️⃣ Read the original JSON file
            File inputFile = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question7\\students_list.json");
            JsonNode rootNode = mapper.readTree(inputFile);

            // 3️⃣ List to store filtered students
            List<JsonNode> filteredStudents = new ArrayList<>();

            // 4️⃣ Filter students with age > 25
            for (JsonNode studentNode : rootNode) {
                if (studentNode.has("age") && studentNode.get("age").asInt() > 25) {
                    filteredStudents.add(studentNode);
                }
            }

            // 5️⃣ Print filtered students in console
            System.out.println("Students with age > 25:");
            filteredStudents.forEach(System.out::println);

            // 6️⃣ Save filtered students to a new JSON file
            File outputFile = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question7\\students_age_gt_25.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, filteredStudents);

            System.out.println("Filtered students saved to: " + outputFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
