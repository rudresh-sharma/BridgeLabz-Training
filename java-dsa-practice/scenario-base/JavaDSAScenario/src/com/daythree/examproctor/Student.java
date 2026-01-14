package com.daythree.examproctor;
import java.util.HashMap;
import java.util.Stack;

public class Student {
    String name;
    Stack<Integer> navigationStack = new Stack<>();   // tracks visited questions
    HashMap<Integer, String> answers = new HashMap<>(); // questionID → answer

    public Student(String name) {
        this.name = name;
    }

    public void visitQuestion(int qid) {
        navigationStack.push(qid);
    }

    public void answerQuestion(int qid, String ans) {
        answers.put(qid, ans);
    }
}
