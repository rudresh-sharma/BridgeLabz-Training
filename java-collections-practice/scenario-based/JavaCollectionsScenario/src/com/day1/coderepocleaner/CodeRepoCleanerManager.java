package com.day1.coderepocleaner;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class CodeRepoCleanerManager {

    // Store categorized files
    private Map<FileCategory, List<Path>> categorizedFiles = new HashMap<>();

    public CodeRepoCleanerManager() {
        categorizedFiles.put(FileCategory.VALID, new ArrayList<>());
        categorizedFiles.put(FileCategory.WARNINGS, new ArrayList<>());
        categorizedFiles.put(FileCategory.ERRORS, new ArrayList<>());
    }

    // 🔹 Scan all .java files in nested folders
    public List<Path> scanJavaFiles(String rootFolder) {
        List<Path> javaFiles = new ArrayList<>();

        try {
            Files.walk(Paths.get(rootFolder))
                 .filter(p -> p.toString().endsWith(".java"))
                 .forEach(javaFiles::add);

            System.out.println("✅ Found " + javaFiles.size() + " .java files.");

        } catch (IOException e) {
            System.out.println("❌ Error scanning folders: " + e.getMessage());
        }

        return javaFiles;
    }

    // 🔹 Analyze and categorize files
    public void analyzeAndCategorize(List<Path> files) {

        // Clear previous results
        categorizedFiles.values().forEach(List::clear);

        for (Path file : files) {
            try {
                JavaFileReport report = analyzeFile(file);
                categorize(file, report);
            } catch (IOException e) {
                categorizedFiles.get(FileCategory.ERRORS).add(file);
            }
        }

        System.out.println("📊 Categorization completed.");
    }

    // 🔹 Analyze one Java file
    private JavaFileReport analyzeFile(Path file) throws IOException {

        List<String> lines = Files.readAllLines(file);

        // Regex patterns
        Pattern badMethodPattern = Pattern.compile("\\b(public|private|protected)\\s+\\w+\\s+([A-Z]\\w*)\\s*\\(");
        Pattern importPattern = Pattern.compile("^import\\s+([\\w\\.]+);");

        boolean hasNamingWarning = false;
        boolean hasUnusedImports = false;
        List<String> issues = new ArrayList<>();

        Set<String> imports = new HashSet<>();
        String fullContent = String.join("\n", lines);

        // 1️⃣ Check method naming convention
        for (String line : lines) {
            Matcher m = badMethodPattern.matcher(line);
            if (m.find()) {
                hasNamingWarning = true;
                issues.add("Method naming not camelCase: " + m.group(2));
            }
        }

        // 2️⃣ Collect imports
        for (String line : lines) {
            Matcher m = importPattern.matcher(line.trim());
            if (m.find()) {
                imports.add(m.group(1));
            }
        }

        // 3️⃣ Check unused imports (simple heuristic)
        for (String imp : imports) {
            String className = imp.substring(imp.lastIndexOf('.') + 1);
            if (!fullContent.contains(className)) {
                hasUnusedImports = true;
                issues.add("Unused import: " + imp);
            }
        }

        return new JavaFileReport(file, hasNamingWarning, hasUnusedImports, issues);
    }

    // 🔹 Categorize based on report
    private void categorize(Path file, JavaFileReport report) {

        if (report.hasErrors()) {
            categorizedFiles.get(FileCategory.ERRORS).add(file);
        } else if (report.hasWarnings()) {
            categorizedFiles.get(FileCategory.WARNINGS).add(file);
        } else {
            categorizedFiles.get(FileCategory.VALID).add(file);
        }
    }

    // 🔹 Display categorized summary
    public void displaySummary() {
        System.out.println("\n===== Code Repository Summary =====");

        for (FileCategory category : FileCategory.values()) {
            List<Path> list = categorizedFiles.get(category);
            System.out.println(category + " Files (" + list.size() + "):");
            for (Path p : list) {
                System.out.println("  - " + p);
            }
        }
    }

    // 🔹 Filter by file size (using Streams)
    public void filterBySize(long minBytes) {
        System.out.println("\nFiles larger than " + minBytes + " bytes:");

        categorizedFiles.values().stream()
                .flatMap(List::stream)
                .filter(p -> {
                    try {
                        return Files.size(p) > minBytes;
                    } catch (IOException e) {
                        return false;
                    }
                })
                .forEach(p -> {
                    try {
                        System.out.println(p + " -> " + Files.size(p) + " bytes");
                    } catch (IOException ignored) {}
                });
    }

    // 🔹 Filter by last modified date (after given epoch seconds)
    public void filterByModifiedAfter(long epochSeconds) {
        Instant threshold = Instant.ofEpochSecond(epochSeconds);

        System.out.println("\nFiles modified after " + threshold + ":");

        categorizedFiles.values().stream()
                .flatMap(List::stream)
                .filter(p -> {
                    try {
                        BasicFileAttributes attrs =
                                Files.readAttributes(p, BasicFileAttributes.class);
                        return attrs.lastModifiedTime().toInstant().isAfter(threshold);
                    } catch (IOException e) {
                        return false;
                    }
                })
                .forEach(System.out::println);
    }
}
