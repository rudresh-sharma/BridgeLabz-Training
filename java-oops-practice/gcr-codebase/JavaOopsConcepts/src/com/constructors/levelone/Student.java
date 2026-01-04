package com.constructors.levelone;

public class Student {
	
	// Instance variables
	public String rollNumber;
	protected String name;
	private float CGPA;
	
	
	// constructor configuration
	public Student() {
		super();
	}
	public Student(String rollNumber, String name, float CGPA) {
		this.rollNumber = rollNumber;
		this.name = name;
		this.CGPA = CGPA;
	}
	
	
	// Method to access CGPA i.e displaying
	public void displayDetails() {
		System.out.println("Your name is " + name);
		System.out.println("RollNumber is " + rollNumber);
		System.out.println("CGPA = " + CGPA);		
	}
	
	public void modifyCGPA(float newCGPA) {
		this.CGPA = newCGPA;
	}
	
	
	
	
}
