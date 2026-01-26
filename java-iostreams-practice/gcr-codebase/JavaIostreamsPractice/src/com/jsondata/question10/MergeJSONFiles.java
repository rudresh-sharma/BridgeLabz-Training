package com.jsondata.question10;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;

public class MergeJSONFiles {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // 1️⃣ Read JSON files
            JsonNode json1 = mapper.readTree(new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question10\\student_part1.json"));
            JsonNode json2 = mapper.readTree(new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question10\\student_part2.json"));

            // 2️⃣ Ensure they are ObjectNodes (not arrays)
            ObjectNode merged = (ObjectNode) json1;
            merged.setAll((ObjectNode) json2);

            // 3️⃣ Print merged JSON
            System.out.println("Merged JSON:");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(merged));

            // 4️⃣ Save merged JSON to file
            File mergedFile = new File("C:\\Users\\ASUS\\OneDrive\\Desktop\\Java programming workspace\\java-iostreams-practice\\gcr-codebase\\JavaIostreamsPractice\\src\\com\\jsondata\\question10\\student_merged.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(mergedFile, merged);

            System.out.println("Merged JSON saved to: " + mergedFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
