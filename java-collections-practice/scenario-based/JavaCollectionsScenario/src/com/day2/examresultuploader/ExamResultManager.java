package com.day2.examresultuploader;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ExamResultManager<T> {

    private Map<String, List<Integer>> subjectMarks = new HashMap<>();
    private List<StudentMark> allMarks = new ArrayList<>();

    // Regex: RollNo,Name,Subject,Marks
    private static final Pattern LINE_PATTERN =
            Pattern.compile("^\\s*(\\d+)\\s*,\\s*([\\w\\s]+)\\s*,\\s*([\\w\\s]+)\\s*,\\s*(\\d{1,3})\\s*$");

    // 🔹 Read CSV file
    public void processCSV(String filePath) {

        allMarks.clear();
        subjectMarks.clear();

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("❌ File does not exist: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            int lineNumber = 1;

            while ((line = br.readLine()) != null) {
                try {
                    parseLine(line);
                } catch (InvalidMarkFormatException e) {
                    System.out.println("⚠️ Line " + lineNumber + " skipped: " + e.getMessage());
                }
                lineNumber++;
            }

            System.out.println("\n✅ Finished processing file. Total valid entries: " + allMarks.size());

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // 🔹 Parse one CSV line
    private void parseLine(String line) throws InvalidMarkFormatException {

        Matcher matcher = LINE_PATTERN.matcher(line);

        if (!matcher.matches()) {
            throw new InvalidMarkFormatException("Invalid format -> " + line);
        }

        String rollNo = matcher.group(1);
        String name = matcher.group(2);
        String subject = matcher.group(3);
        int marks = Integer.parseInt(matcher.group(4));

        if (marks < 0 || marks > 100) {
            throw new InvalidMarkFormatException("Marks out of range (0-100) -> " + line);
        }

        StudentMark sm = new StudentMark(rollNo, name, subject, marks);
        allMarks.add(sm);

        subjectMarks.computeIfAbsent(subject, k -> new ArrayList<>()).add(marks);
    }

    // 🔹 Display all marks
    public void displayAll() {
        System.out.println("\n📊 All Marks:");
        allMarks.forEach(System.out::println);
    }

    // 🔹 Display subject-wise top scorer
    public void displayTopScorers() {
        System.out.println("\n🏆 Top Scorers Per Subject:");

        for (String subject : subjectMarks.keySet()) {

            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            pq.addAll(subjectMarks.get(subject));

            int topMark = pq.peek();

            List<StudentMark> topStudents = new ArrayList<>();
            for (StudentMark sm : allMarks) {
                if (sm.getSubject().equals(subject) && sm.getMarks() == topMark) {
                    topStudents.add(sm);
                }
            }

            System.out.println("\nSubject: " + subject);
            topStudents.forEach(s -> System.out.println(" - " + s.getName() + " (" + s.getMarks() + ")"));
        }
    }
}
