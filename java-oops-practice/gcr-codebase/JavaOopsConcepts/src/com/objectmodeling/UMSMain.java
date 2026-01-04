package com.objectmodeling;

public class UMSMain {
	    public static void main(String[] args) {

	        Student s1 = new Student("Ravi");
	        Student s2 = new Student("Anita");

	        Course java = new Course("Java Programming");

	        Professor prof = new Professor("Dr Sharma");

	        // Professor teaches course
	        prof.assignProfessor(java);

	        // Students enroll
	        s1.enrollCourse(java);
	        s2.enrollCourse(java);

	        // Show result
	        System.out.println("\nCourse: " + java.getCourseName());
	        System.out.println("Professor: " + java.getProfessor().getName());
	        java.showStudents();
	    }
	

}
