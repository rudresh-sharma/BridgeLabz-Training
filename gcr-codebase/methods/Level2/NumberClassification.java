/*

Write a program to take user input for 5 numbers and check whether a number is positive or negative. Further for positive numbers check if the number is even or odd. Finally compare the first and last elements of the array and display if they are equal, greater, or less
Hint => 
Write a Method to Check whether the number is positive or negative
Write a Method to check whether the number is even or odd
Write a Method to compare two numbers and return 1 if number1 > number2 or 0 if both are equal or -1 if number1 < number2 
In the main program, Loop through the array using the length call the method isPositive() and if positive call method isEven() and print accordingly 
If the number is negative, print negative. 
Finally compare the first and last element of the array by calling the method compare() and display if they are equal, greater, or less

*/

import java.util.Scanner;
public class NumberClassification{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the 5 numbers: ");
	int[] nums = new int[5];
		for(int i=0; i<5; i++){
			nums[i] = in.nextInt();
		}



	// Classifying numbers
		for(int i=0; i<nums.length; i++){
			if(isPositive(nums[i])){
				if(isEven(nums[i])){
					System.out.println("Number " + nums[i] + " is even and positive both ");
				}
				else{
					System.out.println("Number " + nums[i] + " is odd and positive both ");
				}
			} 
			else if(isNegative(nums[i])){
					System.out.println("Number " + nums[i] + " is negative ");
			}
		}

	int indicator = compare(nums[0], nums[4]);
		if(indicator == 1){	
			System.out.println("First Number " + nums[0] + " is greater than last number " + nums[4]);
		}
		else if(indicator == 0){
			System.out.println("First Number " + nums[0] + " is equal to last number " + nums[4]);
		}
		else{
			System.out.println("First Number " + nums[0] + " is less than last number " + nums[4]);
		}
		
	
	in.close();

	}




	// Method for checking number isPositive
	public static boolean isPositive(int num){
		if(num>=0) return true;
		else 	  return false;
	}


	// Method for checking number isNegative
	public static boolean isNegative(int num){
		if(num<0) return true;
		else 	  return false;
	}


	// Method for checking number isEven
	public static boolean isEven(int num){
		if(num%2 == 0) return true;
		else 	  return false;
	}


	// Method for checking number isPositive
	public static int compare(int num1, int num2){
		if(num1>num2) 
			return 1;
		else if(num1<num2)	  
			return -1;
		else 
			return 0;

	}


}


