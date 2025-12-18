/*

Write a program FizzBuzz, take a number as user input, and check for a positive integer. If positive integer, loop and print the number, but for multiples of 3 print "Fizz" instead of the number, for multiples of 5 print "Buzz", and for multiples of both print "FizzBuzz".
Hint => 
Take the user input number, check for a positive integer, and use for loop to display


*/


import java.util.Scanner;
public class  FizzBuzzFor{
	public static void main(String[] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the Number: ");
	int num = input.nextInt();


	// Printing fizzbuzzFor

		if(num > 0) {
		for(int i=1; i<=num; i++){
			if(i%3 == 0 && i%5 == 0){
				System.out.println(i + " = FizzBuzz");
			}
			else if (i%3 == 0){		
 				System.out.println(i+ " = Fizz");	
			}
			else if(i%5 == 0) {
				System.out.println(i+ " = Buzz");
			}
		}
		}

		else {
			System.out.println("Number is not positive");
		}

	input.close();	
	}

}











