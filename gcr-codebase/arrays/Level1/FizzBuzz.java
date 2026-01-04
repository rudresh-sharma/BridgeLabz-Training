/*

Write a program FizzBuzz, take a number as user input and if it is a positive integer loop from 0 to the number and save the number, but for multiples of 3 save "Fizz" instead of the number, for multiples of 5 save "Buzz", and for multiples of both save "FizzBuzz". Finally, print the array results for each index position in the format Position 1 = 1, …, Position 3 = Fizz,...
Hint => 
Create a String Array to save the results and 
Finally, loop again to show the results of the array based on the index position

*/


import java.util.Scanner;
public class FizzBuzz{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number :");
	int number = in.nextInt();
	String[] result = new String[number];			
	
																		// Printing fizzBuzz according to relation
		for(int i=1; i<=number; i++){
			if(i%3 == 0 && i%5 == 0){	
				result[i-1] = "FizzBuzz";
			}
			else if(i%3 == 0){
				result[i-1] = "Fizz";
			}
			else if(i%5==0){
				result[i-1] = "Buzz";
			}
			else{
				result[i-1] = String.valueOf(i);
			}
		}	
		for(int i=0; i<number; i++){
			System.out.println("Position " + (i+1) + " = " + result[i]);
		}

	in.close();
	}

}


