/* 

Write a program to count down the number from the user input value to 1 using a for loop for a rocket launch
Hint => 
Create a variable counter to take user inputted value for the countdown.
Use the while loop to check if the counter is 1
Inside a while loop, print the value of the counter and decrement the counter.


*/


import java.util.Scanner;
public class CountDownForLoop{
	public static void main(String [] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = input.nextInt();
	int counter = number;
		

	// Printing countdowm till 1
		for(int i=number; i>=1; i--){
			System.out.println(i);
			counter = counter - 1;
		}


	input.close();

	}

}