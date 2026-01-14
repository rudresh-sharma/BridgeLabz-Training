package com.daythree.examproctor;
import java.util.Scanner;

public class ExamProctorTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExamProctor exam = new ExamProctor();

        // Add exam questions
        exam.addQuestion(1, "What is JVM?", "Java Virtual Machine");
        exam.addQuestion(2, "Which keyword creates object?", "new");
        exam.addQuestion(3, "What is OOP?", "Object Oriented Programming");

        System.out.print("Enter student name: ");
        Student student = new Student(sc.nextLine());

        while (true) {
            System.out.println("\n--- Exam Menu ---");
            System.out.println("1. View Questions");
            System.out.println("2. Answer Question");
            System.out.println("3. Submit Exam");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                exam.showQuestions();
            }

            else if (choice == 2) {
                System.out.print("Enter Question ID: ");
                int qid = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter your answer: ");
                String ans = sc.nextLine();

                student.visitQuestion(qid);     // push into stack
                student.answerQuestion(qid, ans); // store in HashMap
            }

            else if (choice == 3) {
                int score = exam.calculateScore(student);
                System.out.println("\nExam Submitted!");
                System.out.println("Final Score: " + score + "/" + exam.questions.size());
                exam.showNavigation(student);
                break;
            }
        }
    }
}
