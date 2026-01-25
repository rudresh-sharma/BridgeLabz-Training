package com.day1.examscanner;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ExamScannerManager<T extends AnswerSheet> {

    private T answerSheet;
    private Map<String, Integer> scoreMap = new HashMap<>();

    // Regex:
    // Name,Option,Option,Option,...
    // Options must be A-D
    private static final Pattern LINE_PATTERN =
            Pattern.compile("^[A-Za-z ]+(,(A|B|C|D))+$");

    public ExamScannerManager(T answerSheet) {
        this.answerSheet = answerSheet;
    }

    // 🔹 Read and process CSV file
    public void processAnswerFile(String filePath) {

        scoreMap.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {

                try {
                    processLine(line, lineNumber);
                } catch (IllegalArgumentException e) {
                    System.out.println("⚠️ Line " + lineNumber + " skipped: " + e.getMessage());
                }

                lineNumber++;
            }

            System.out.println("✅ Finished processing file for subject: " +
                    answerSheet.getSubjectName());

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // 🔹 Process one line
    private void processLine(String line, int lineNumber) {

        Matcher matcher = LINE_PATTERN.matcher(line.trim());

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid format -> " + line);
        }

        String[] parts = line.split(",");

        String studentName = parts[0].trim();
        List<String> studentAnswers = new ArrayList<>();

        for (int i = 1; i < parts.length; i++) {
            studentAnswers.add(parts[i].trim());
        }

        List<String> correctAnswers = answerSheet.getCorrectAnswers();

        if (studentAnswers.size() != correctAnswers.size()) {
            throw new IllegalArgumentException("Answer count mismatch for " + studentName);
        }

        int score = calculateScore(studentAnswers, correctAnswers);

        scoreMap.put(studentName, score);

        System.out.println("Processed: " + studentName + " -> Score: " + score);
    }

    // 🔹 Compare answers
    private int calculateScore(List<String> studentAnswers, List<String> correctAnswers) {

        int score = 0;

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (studentAnswers.get(i).equals(correctAnswers.get(i))) {
                score++;
            }
        }

        return score;
    }

    // 🔹 Display sorted results using PriorityQueue
    public void displayRankList() {

        PriorityQueue<StudentResult> pq = new PriorityQueue<>();

        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            pq.add(new StudentResult(entry.getKey(), entry.getValue()));
        }

        System.out.println("\n🏆 Rank List (" + answerSheet.getSubjectName() + "):");

        int rank = 1;
        while (!pq.isEmpty()) {
            StudentResult result = pq.poll();
            System.out.println("Rank " + rank + ": " + result);
            rank++;
        }
    }
}
