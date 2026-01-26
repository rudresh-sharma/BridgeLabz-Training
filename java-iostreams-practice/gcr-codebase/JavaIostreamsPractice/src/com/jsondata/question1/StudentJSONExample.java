package com.jsondata.question1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class StudentJSONExample {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper instance
            ObjectMapper mapper = new ObjectMapper();

            // Create JSON object for student
            ObjectNode student = mapper.createObjectNode();
            student.put("name", "Rudresh Sharma");
            student.put("age", 21);

            // Create subjects array
            ArrayNode subjects = mapper.createArrayNode();
            subjects.add("Mathematics");
            subjects.add("Physics");
            subjects.add("Computer Science");

            // Add subjects array to student object
            student.set("subjects", subjects);

            // Convert JSON object to String (pretty print)
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);

            // Print JSON
            System.out.println(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
