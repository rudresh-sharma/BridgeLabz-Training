/*

Write a program to count down the number from the user input value to 1 using a while loop for a rocket launch
Hint => 
Create a variable counter to take user inputted value for the countdown.
Use the while loop to check if the counter is 1
Inside a while loop, print the value of the counter and decrement the counter.

*/


import java.util.Scanner;
public class CountDownWhileLoop{
	public static void main(String [] args){

	// Takind inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = input.nextInt();
	int counter = number;
	
	
	//Printing counter and decrementing untill it become 1
		while(counter>=1){
			System.out.println(counter);
			counter = counter -1;
		}

	input.close();

	}


}