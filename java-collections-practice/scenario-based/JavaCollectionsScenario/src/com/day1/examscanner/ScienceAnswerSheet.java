package com.day1.examscanner;

import java.util.Arrays;
import java.util.List;

public class ScienceAnswerSheet implements AnswerSheet {

    private List<String> correctAnswers =
            Arrays.asList("B", "C", "A", "D", "B", "A", "C");

    @Override
    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public String getSubjectName() {
        return "Science";
    }
}
