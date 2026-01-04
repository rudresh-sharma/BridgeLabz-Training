/*

Write a program to take user input for the age of all 10 students in a class and check whether the student can vote depending on his/her age is greater or equal to 18.
Hint => 
Create a class public class StudentVoteChecker and define a method public boolean canStudentVote(int age) which takes in age as a parameter and returns true or false
Inside the method firstly validate the age for a negative number, if a negative return is false cannot vote. For valid age check for age is 18 or above return true; else return false;
In the main function define an array of 10 integer elements, loop through the array by take user input for the student's age, call canStudentVote() and display the result


*/

import java.util.Scanner;
public class StudentVoteChecker{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
 	int[] studentsAge = new int[10];
	boolean canVote = false;
		for(int i=0; i<10; i++){
			System.out.print("Enter the age of student " + (i+1) +" : ");
			studentsAge[i] = in.nextInt();
			StudentVoteChecker obj = new StudentVoteChecker();
			canVote =        obj.canStudentVote(studentsAge[i]);
				if(canVote){
					System.out.println("Yes a student " + (i+1) + " can vote");
				}
				else{
					System.out.println("No a student "+ (i+1) + " can't vote");
				}
		}




	in.close();


	}



	// Method for checking student can vote or not
	 public boolean canStudentVote(int age){
		if(age<18){
			return false;
		}
		else{
			return true;
		}
	}


}

	





