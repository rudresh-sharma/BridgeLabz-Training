package com.dayfour.edumentor;

import java.util.ArrayList;
public class Quiz extends Instructor {
	protected  ArrayList<String> questions;
	protected ArrayList<String> answers;
	private int score;
	private int noOfQuestions;

	public Quiz(int n) {
		super();
		questions = new ArrayList<>();
		answers = new ArrayList<>();
		this.noOfQuestions = n;
	
	}
	
	
	
	// Getter and Setter
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getNoOfQuestions() {
		return noOfQuestions;
	}
	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
	}
	
	
	
	public int generateScore(Learner learner, Quiz quiz) {
		
		int score = 0;
		
		for(int i=0; i<noOfQuestions; i++) {
			String userAns = learner.learnerAnswers.get(i);
			String correctAns = quiz.answers.get(i);
			
			if(userAns.equals(correctAns)) {
				score++;
			}
		}
		
		
		return score;
	}
	
	
	
	
	
	
}
