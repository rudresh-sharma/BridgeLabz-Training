package com.dayfour.edumentor;

import java.util.ArrayList;
public class Learner extends User {

		private int typeOfCourse;
		ArrayList<String> learnerAnswers;
			
		
		public Learner(String name, String email, String id, int lquizType) {
			super(name, email, id);
			this.typeOfCourse = lquizType;
			this.learnerAnswers = new ArrayList<>();
		}

		public int getTypeOfCourse() {
			return typeOfCourse;
		}

		public void setTypeOfCourse(int typeOfCourse) {
			this.typeOfCourse = typeOfCourse;
		}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
