package com.dayfour.edumentor;

import java.util.ArrayList;

public class Instructor extends User implements ICertifiable{
	private Instructor ins;
	private Learner learner;
	private ArrayList<Learner> learners;

	public Instructor() {
		super();
	}
	
	
	public Instructor(String name, String email, String id) {
		super(name, email, id);
	}
	
	
	
	
	
	
	public Instructor getIns() {
		return ins;
	}


	public void setIns(Instructor ins) {
		this.ins = ins;
	}


	public Learner getLearner() {
		return learner;
	}


	public void setLearner(Learner learner) {
		this.learner = learner;
	}


	@Override
	public void generateCertificate(int noOfQue, int score, Instructor in) {
		// TODO Auto-generated method stub
		
		System.out.println("=================================================");
        System.out.println("               QUIZ CERTIFICATE                  ");
        System.out.println("=================================================");
        System.out.println("Learner Name : " + learner.getName());
        System.out.println("Email        : " + learner.getEmail());
        System.out.println("User ID      : " + learner.getUserId());
        System.out.println("Course Type  : " + (learner.getTypeOfCourse() == 1 ? "Short Course" : "Full Time"));
        System.out.println("Score        : " + score + "/" + noOfQue);
        System.out.println("=================================================");
			
			

		
		
	}
	
	
	
	
}
