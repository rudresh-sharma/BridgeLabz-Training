package com.jsondata.question5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class ValidateJSON {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question5\\students_merged.json");

        try {
            // Parse the JSON file
            JsonNode rootNode = mapper.readTree(jsonFile);

            // If parsing succeeds, JSON is valid
            System.out.println("JSON is valid!");
            
            // Optional: print root node type
            System.out.println("Root node type: " + rootNode.getNodeType());

        } catch (Exception e) {
            // If an exception occurs, JSON is invalid
            System.out.println("Invalid JSON: " + e.getMessage());
        }
    }
}
