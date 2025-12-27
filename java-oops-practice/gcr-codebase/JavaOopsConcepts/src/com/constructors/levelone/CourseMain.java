package com.constructors.levelone;

public class CourseMain {
	public static void main(String[] args) {
		
		// Creating course object
		Course c1 = new Course("AI For Everyone", 3, 2000.0f);
		Course c2 = new Course("Introduction to generative AI", 3, 1000.0f);
		
		// displaying course details
		c1.displayCourseDetails();
		c2.displayCourseDetails();
		
		// Updating institute name 
		Course.updateInstituteName();
		
		// Again printng course details with new institue
		c1.displayCourseDetails();
		c2.displayCourseDetails();
	}
}
