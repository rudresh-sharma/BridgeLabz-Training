package com.dayfour.edumentor;

import java.util.Scanner;

public class FullTimeCourseQuiz extends Quiz{

	private int noOfQuestions;
	private Quiz quiz;
	private static Scanner in = new Scanner(System.in);
	
	
	
	public FullTimeCourseQuiz(int noOfQuestions) {
		super(noOfQuestions);
	}
	
	
	public void givingQuestions() {
		System.out.println("Questions assignment");
		for(int i=1; i<= quiz.getNoOfQuestions(); i++) {
			System.out.println("Enter Question " + i + ": ");
			String question = in.nextLine();
			System.out.println("Enter answer");
			String answer = in.nextLine();
			
			questions.add(question);
			answers.add(answer);
		}
	}
	
	
	
	
	
}
