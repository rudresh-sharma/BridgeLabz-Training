/*

Rewrite the program to find all the multiples of a number below 100 using while loop.
Hint => 
Get the input value for a variable named number. Check the number is a positive integer and less than 100.
Create a counter variable and assign counter = number - 1; Use a while till the counter is > 1
Inside the loop, check if the counter perfectly divides the number. If true, print the number and continue the loop.

*/



import java.util.Scanner;
public class MultiplesFor{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = in.nextInt();


	// Printing all multples
	System.out.println("Multiples of " + num + " below 100 are");
	int counter = num;
		while(counter<100){
			System.out.println(counter);
			counter += num;
		}

	in.close();

	}

}



