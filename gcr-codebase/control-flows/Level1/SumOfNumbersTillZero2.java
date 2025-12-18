/*

Rewrite the program 10 to find the sum until the user enters 0 or a negative number using while loop and break statement
Hint => 
Use infinite while loop as in while (true)
Take the user entry and check if the user entered 0 or a negative number to break the loop using break;


*/

import java.util.Scanner;
public class SumOfNumbersTillZero{
	public static void main(String [] args){

	// Taking inputs
	Scanner input =  new Scanner(System.in);
	System.out.println("Enter the numbers");
	double sum = 0;
	
	
	// Taking numbers untill 0 and negative number is enter
		while(true){
			double num = input.nextDouble();
			if(num <= 0)
				break;

			sum += num;
					
		}


	// Printing the sum of numbers
	System.out.println(sum);
	input.close();

	}

}