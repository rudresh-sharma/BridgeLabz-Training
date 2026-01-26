package com.jsondata.question3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class ExtractSpecificFields {
    public static void main(String[] args) {
        try {
            // 1️⃣ Create ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // 2️⃣ Read JSON file
            File file = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question3\\students.json");
            JsonNode rootNode = mapper.readTree(file);

            // 3️⃣ Loop through each object in JSON array
            for (JsonNode studentNode : rootNode) {
                String name = studentNode.get("name").asText();
                String email = studentNode.get("email").asText();

                System.out.println("Name: " + name + ", Email: " + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
