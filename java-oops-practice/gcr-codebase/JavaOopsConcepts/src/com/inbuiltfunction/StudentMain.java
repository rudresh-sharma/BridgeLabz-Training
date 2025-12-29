package com.inbuiltfunction;

public class StudentMain {
	  public static void main(String[] args) {
	       // Set the university name (static variable)
	       Student.setUniversityName("Global University");
	       // Create new student instances
	       Student student1 = new Student("Hemashree", 101, 'A');
	       Student student2 = new Student("Sharmila", 102, 'B');
	       // Display total number of students enrolled
	       Student.displayTotalStudents();
	       // Display student details
	       student1.displayStudentDetails();
	       System.out.println();
	       student2.displayStudentDetails();
	       // Update student grade
	       student2.updateGrade('A');
	       System.out.println();
	       // Display updated student details
	       student2.displayStudentDetails();
	   }

}
