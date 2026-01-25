package com.day1.resumeanalyzer;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class ResumeAnalyzer {

    // Map of email -> ResumeData
    private Map<String, ResumeData> candidates = new HashMap<>();

    // Keywords to search
    private List<String> keywords = Arrays.asList("Java", "Python", "Spring");

    // Regex patterns
    private Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,6}");
    private Pattern phonePattern = Pattern.compile("\\+?\\d{10,13}");

    // Process resumes from folder
    public void processFolder(String folderPath) {
        Path dir = Paths.get(folderPath);

        if (!Files.exists(dir) || !Files.isDirectory(dir)) {
            System.out.println("❌ Folder does not exist: " + folderPath);
            return;
        }

        try {
            Files.list(dir).forEach(file -> {
                if (Files.isRegularFile(file)) {
                    try {
                        processResume(file.toFile());
                    } catch (Exception e) {
                        System.out.println("⚠️ Skipping invalid resume: " + file.getFileName());
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("❌ Error reading folder: " + e.getMessage());
        }
    }

    // Process individual resume
    private void processResume(File file) throws IOException {
        String content = "";

        if (file.getName().endsWith(".txt")) {
            content = new String(Files.readAllBytes(file.toPath()));
        } else if (file.getName().endsWith(".pdf")) {
            // Simple PDF handling using PDFBox or similar
            content = extractTextFromPDF(file);
        } else {
            throw new IOException("Unsupported file type");
        }

        String email = extractFirstMatch(emailPattern, content);
        String phone = extractFirstMatch(phonePattern, content);
        String name = extractName(content, email);

        if (email == null) throw new IOException("No valid email found");

        List<String> foundSkills = new ArrayList<>();
        for (String keyword : keywords) {
            if (Pattern.compile("\\b" + keyword + "\\b", Pattern.CASE_INSENSITIVE).matcher(content).find()) {
                foundSkills.add(keyword);
            }
        }

        ResumeData resume = new ResumeData(name, email, phone, foundSkills);
        candidates.put(email, resume);
        System.out.println("✅ Processed resume: " + name + " (" + email + ")");
    }

    // Placeholder PDF text extraction (use Apache PDFBox in real projects)
    private String extractTextFromPDF(File file) {
        // In a real implementation, read PDF text here
        return ""; // Return empty for placeholder
    }

    private String extractFirstMatch(Pattern pattern, String content) {
        Matcher matcher = pattern.matcher(content);
        return matcher.find() ? matcher.group() : null;
    }

    private String extractName(String content, String email) {
        // Simple approach: first line or before email
        String[] lines = content.split("\n");
        for (String line : lines) {
            if (!line.contains(email) && line.trim().length() > 0) {
                return line.trim();
            }
        }
        return "Unknown";
    }

    // Get candidates sorted by keyword match count
    public List<ResumeData> getSortedCandidates() {
        List<ResumeData> list = new ArrayList<>(candidates.values());
        list.sort((a, b) -> Integer.compare(b.getKeywordMatchCount(), a.getKeywordMatchCount()));
        return list;
    }

    public void displayAllCandidates() {
        List<ResumeData> list = getSortedCandidates();
        for (ResumeData r : list) {
            System.out.println("-------------------------------");
            System.out.println(r);
        }
    }
}
