package com.day1.coderepocleaner;

import java.nio.file.Path;
import java.util.List;

public class JavaFileReport {

    private Path filePath;
    private boolean hasNamingWarning;
    private boolean hasUnusedImports;
    private List<String> issues;

    public JavaFileReport(Path filePath, boolean hasNamingWarning,
                          boolean hasUnusedImports, List<String> issues) {
        this.filePath = filePath;
        this.hasNamingWarning = hasNamingWarning;
        this.hasUnusedImports = hasUnusedImports;
        this.issues = issues;
    }

    public Path getFilePath() {
        return filePath;
    }

    public boolean hasWarnings() {
        return hasNamingWarning || hasUnusedImports;
    }

    public boolean hasErrors() {
        // For simplicity: treat severe naming issues as errors
        return false;
    }

    public List<String> getIssues() {
        return issues;
    }

    @Override
    public String toString() {
        return filePath.toString() + " -> Issues: " + issues;
    }
}
