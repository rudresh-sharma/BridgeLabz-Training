/*

Write a program to find the sum of numbers until the user enters 0
Hint => 
Create a variable total of type double initialize to 0.0. Also, create a variable to store the double value the user enters
Use the while loop to check if the user entered is 0
If the user entered value is not 0 then inside the while block add user entered value to the total and ask the user to input again
The loop will continue till the user enters zero and outside the loop display the total value


*/



import java.util.Scanner;
public class SumOfNumbersTillZero{
	public static void main(String [] args){

	// Taking inputs
	Scanner input =  new Scanner(System.in);
	System.out.println("Enter the numbers");
	double num = input.nextDouble();
	double sum = 0;
	
	
	// Taking numbers untill 0 is not enter
		while((num != 0)){
			sum += num;
			num = input.nextDouble();
		}


	// Printing the sum of numbers
	System.out.println(sum);

	input.close();

	}

}