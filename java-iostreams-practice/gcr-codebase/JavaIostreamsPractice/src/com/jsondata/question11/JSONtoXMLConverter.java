package com.jsondata.question11;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class JSONtoXMLConverter {

    public static void main(String[] args) {
        try {
            // Jackson mappers
            ObjectMapper jsonMapper = new ObjectMapper();
            XmlMapper xmlMapper = new XmlMapper();

            // 1️⃣ Read JSON file
            File jsonFile = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question11\\student_merged.json");
            JsonNode jsonNode = jsonMapper.readTree(jsonFile);

            // 2️⃣ Convert JSON to XML string
            String xmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // 3️⃣ Print XML in console
            System.out.println("Converted XML:");
            System.out.println(xmlContent);

            // 4️⃣ Save XML to file
            File xmlFile = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question11\\students_merged.xml");
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(xmlFile, jsonNode);

            System.out.println("XML saved successfully to: " + xmlFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
