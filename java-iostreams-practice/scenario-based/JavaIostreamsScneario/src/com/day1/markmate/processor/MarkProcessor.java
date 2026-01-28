package com.day1.markmate.processor;

import com.day1.markmate.model.Student;

public class MarkProcessor {

    public static void process(Student student) {

        int total = 0;
        int subjects = student.getSubjectMarks().size();

        for (int mark : student.getSubjectMarks().values()) {
            total += mark;
        }

        double average = subjects > 0 ? (double) total / subjects : 0;

        student.setTotal(total);
        student.setAverage(average);
        student.setGrade(calculateGrade(average));
    }

    private static String calculateGrade(double avg) {

        if (avg >= 90) return "A+";
        if (avg >= 75) return "A";
        if (avg >= 60) return "B";
        if (avg >= 45) return "C";
        return "Fail";
    }
}
