package com.constructors.levelone;

public class Course {
	
	private String courseName;
	private int duration ;
	private float fees;
	
	private static String instituteName = "TIT Main" ;
	
	public Course() {
		super();
	}
	
	public Course(String courseName, int duration, float fees) {
		this.courseName = courseName;
		this.duration = duration;
		this.fees = fees;
	}
	
	public void displayCourseDetails() {
		System.out.println("Course Details:");
		System.out.println("---------------");
		
		System.out.println("Course Name = " + courseName);
		System.out.println("Duration (in months) = " + duration);
		System.out.println("Fees = " + fees);
		System.out.println("Institue Name = " + instituteName);

		
	}
	
	
	public static void updateInstituteName() {
		instituteName  = "TIT Excellence";
	}
}	
