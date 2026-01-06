package com.dayfour.edumentor;

import java.util.Scanner;
import java.util.ArrayList;

public class EduMentorApp {

    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Learner> l = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Press 1 to start system , 0 to stop");
        int choice = in.nextInt();
        in.nextLine();

        while (choice == 1) {

            int moreUser;
            Quiz quiz = null;
            Learner l = null;
            Instructor inst = null;

            do {
                System.out.println("Enter your role.");
                System.out.println("1. Instructor");
                System.out.println("2. Learner");

                int role = in.nextInt();
                in.nextLine();

                if (role == 1) {
                    System.out.println("Welcome to EduMentor System Sir!!!");
                    System.out.println("Enter what you want to do");
                    System.out.println("1. Make a Quiz");

                    int isQuiz = in.nextInt();

                    if (isQuiz == 1) {
                        int i = 0;

                        System.out.println("Complete your details first");
                        System.out.print("Name: ");
                        String insName = in.nextLine();
                        in.nextLine();
                        System.out.print("Email: ");
                        String insEmail = in.nextLine();
                        System.out.print("Id: ");
                        String insId = in.nextLine();

                        inst = new Instructor(insName, insEmail, insId);
                        inst.setIns(inst);
                        System.out.println("Enter type of Quiz: ");
                        System.out.println("1. Short Course Quiz");
                        System.out.println("2. Full Time Quiz");
                        int quizType = in.nextInt();
                        System.out.println("Enter number of questions");
                        int noOfQue = in.nextInt();

                        if (quizType == 1) {
                            quiz = new ShortCoursesQuiz(noOfQue);
                        } else if (quizType == 2) {
                            quiz = new FullTimeCourseQuiz(noOfQue);
                        }

                        while (noOfQue > 0) {
                            System.out.println("Enter Question " + (i + 1) + " : ");
                            String question = in.nextLine();
                            in.nextLine();
                            System.out.println("Enter Answer :");
                            String answer = in.nextLine();

                            quiz.questions.add(question);
                            quiz.answers.add(answer);

                            noOfQue--;
                            i++;
                        }
                    }
                } 
                else if (role == 2) {

                    System.out.println("What type of quiz you want.");
                    System.out.println("1. Short Course Quiz");
                    System.out.println("2. Full Time Quiz");
                    int lquizType = in.nextInt();
                    in.nextLine();
                    
                    
                    
                    System.out.println("Complete your details first");
                    System.out.print("Name: ");
                    String insName = in.nextLine();
                    in.nextLine();
                    System.out.print("Email: ");
                    String insEmail = in.nextLine();
                    System.out.print("Id: ");
                    String insId = in.nextLine();

                    l= new Learner(insName, insEmail, insId, lquizType);
                    inst.getIns().setLearner(l);
                    
                   

                    for (int i = 0; i < quiz.getNoOfQuestions(); i++) {

                        System.out.print("Question " + (i + 1) + ":");
                        System.out.println(quiz.questions.get(i));

                        System.out.print("Enter Answer: ");
                        String queAns = in.nextLine();

                        l.learnerAnswers.add(queAns);
                    }

                    System.out.println("Thank you for giving quiz , your score is calculating.....");
                    int score = quiz.generateScore(l, quiz);

                    System.out.println("Below is your certificate");
                    inst.generateCertificate(quiz.getNoOfQuestions(), score, inst);
                }

                System.out.println("1 for more user else 0");
                moreUser = in.nextInt();

            } while (moreUser != 0);

            System.out.println("Enter 0 to stop system.");
            choice = in.nextInt();
        }
    }
}
