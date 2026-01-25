package com.day1.examscanner;

import java.util.Arrays;
import java.util.List;

public class MathAnswerSheet implements AnswerSheet {

    private List<String> correctAnswers =
            Arrays.asList("A", "B", "C", "D", "A", "B", "C");

    @Override
    public List<String> getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public String getSubjectName() {
        return "Math";
    }
}
