package com.day1.feedbackguru;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class FeedbackGuruManager {

    // Category -> List of feedback messages
    private Map<String, List<String>> categorizedFeedback = new HashMap<>();

    // Regex to extract rating like 7/10, 9/10
    private Pattern ratingPattern = Pattern.compile("(\\d{1,2})\\s*/\\s*10");

    public FeedbackGuruManager() {
        categorizedFeedback.put("Positive", new ArrayList<>());
        categorizedFeedback.put("Neutral", new ArrayList<>());
        categorizedFeedback.put("Negative", new ArrayList<>());
    }

    // 🔹 Read all .txt feedback files from folder
    public void processFeedbackFolder(String folderPath, String feedbackType) {
        Path dir = Paths.get(folderPath);

        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            System.out.println("❌ Folder does not exist: " + folderPath);
            return;
        }

        try {
            Files.list(dir)
                 .filter(p -> p.toString().endsWith(".txt"))
                 .forEach(file -> processSingleFile(file.toFile(), feedbackType));

            System.out.println("✅ Finished processing all feedback files.");

        } catch (IOException e) {
            System.out.println("❌ Error reading folder: " + e.getMessage());
        }
    }

    // 🔹 Process one file
    private void processSingleFile(File file, String feedbackType) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {
                try {
                    processLine(line, feedbackType);
                } catch (Exception e) {
                    // Handle invalid line gracefully
                    System.out.println("⚠️ Skipping invalid line: " + line);
                }
            }

            System.out.println("📄 Processed file: " + file.getName());

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + file.getName());
        }
    }

    // 🔹 Process one feedback line
    private void processLine(String line, String feedbackType) {
        Matcher matcher = ratingPattern.matcher(line);

        if (!matcher.find()) {
            throw new IllegalArgumentException("No rating found");
        }

        int rating = Integer.parseInt(matcher.group(1));

        Feedback<String> feedback = new Feedback<>(feedbackType, line, rating);

        String category = categorize(rating);
        categorizedFeedback.get(category).add(feedback.toString());
    }

    // 🔹 Categorize based on rating
    private String categorize(int rating) {
        if (rating >= 8) return "Positive";
        else if (rating >= 5) return "Neutral";
        else return "Negative";
    }

    // 🔹 Display categorized feedback
    public void displayCategorizedFeedback() {
        for (Map.Entry<String, List<String>> entry : categorizedFeedback.entrySet()) {
            System.out.println("\n=== " + entry.getKey() + " Feedback ===");
            List<String> list = entry.getValue();

            if (list.isEmpty()) {
                System.out.println("No feedback in this category.");
            } else {
                for (String msg : list) {
                    System.out.println(msg);
                }
            }
        }
    }

    // 🔹 Clear previous results (for fresh run)
    public void clearData() {
        categorizedFeedback.get("Positive").clear();
        categorizedFeedback.get("Neutral").clear();
        categorizedFeedback.get("Negative").clear();
    }
}
