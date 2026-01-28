package com.day1.markmate.util;

import com.day1.markmate.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.List;

public class JsonWriterUtil {

    public static void writeToJson(List<Student> students, String outputFile) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(outputFile), students);

            System.out.println("✅ JSON report generated: " + outputFile);

        } catch (Exception e) {
            System.out.println("❌ Error writing JSON: " + e.getMessage());
        }
    }
}
