package com.daythree.examproctor;
import java.util.*;

public class ExamProctor {

    HashMap<Integer, Question> questions = new HashMap<>();

    // Add questions to exam
    public void addQuestion(int id, String text, String correct) {
        questions.put(id, new Question(id, text, correct));
    }

    // Display all questions
    public void showQuestions() {
        for (Question q : questions.values()) {
            System.out.println(q.id + ". " + q.question);
        }
    }

    // Evaluate student answers
    public int calculateScore(Student s) {
        int score = 0;

        for (int qid : s.answers.keySet()) {
            Question q = questions.get(qid);
            String studentAns = s.answers.get(qid);

            if (q != null && q.correctAnswer.equalsIgnoreCase(studentAns)) {
                score++;
            }
        }
        return score;
    }

    // Show navigation history
    public void showNavigation(Student s) {
        System.out.println("\nQuestion Navigation History:");
        Stack<Integer> temp = (Stack<Integer>) s.navigationStack.clone();

        while (!temp.isEmpty()) {
            System.out.println("Visited Question: " + temp.pop());
        }
    }
}
