/*

Create a program to save odd and even numbers into odd and even arrays between 1 to the number entered by the user. Finally, print the odd and even numbers array
Hint => 
Get an integer input from the user, assign it to a variable number, and check for Natural Number. If not a natural number then print an error and exit the program
Create an integer array for even and odd numbers with size = number / 2 + 1
Create index variables for odd and even numbers and initialize them to zero
Using a for loop, iterate from 1 to the number, and in each iteration of the loop, save the odd or even number into the corresponding array
Finally, print the odd and even numbers array using the odd and even index

*/


import static java.lang.System.exit;
import java.util.Scanner;
public class OddEven{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();
	if(number<0){
		System.err.println("You are enter negative number ");
		exit(-1);
	}
	int noOfOdds = (number-1)/2 + 1;
	int[] odds = new int[noOfOdds];
	int[] evens = new int[number-noOfOdds];

	
	// Collecting odd and even numbers and printing in range 1 to number
	int oddCounter =0, evenCounter =0;
	for(int i=1; i<=number; i++){
		if(i%2!=0){
			odds[oddCounter] = i;
			oddCounter++;
		}
		else{
			evens[evenCounter] = i;
			evenCounter++;
		}
	}
	System.out.println("Odd numbers in 1 to " + number + " are: ");
	for(int i=0; i<odds.length; i++){
		System.out.println("Odd number " + (i+1) + " is: " + odds[i]);
	}
	System.out.println();
	System.out.println("Even numbers in 1 to " + number + " are: ");
	for(int i=0; i<evens.length; i++){
		System.out.println("Even number " + (i+1) + " is: " + evens[i]);
	}

	in.close();
	}
}



	


		