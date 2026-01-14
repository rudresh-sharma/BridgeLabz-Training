package com.daythree.examproctor;
public class Question {
    int id;
    String question;
    String correctAnswer;

    public Question(int id, String question, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
}
