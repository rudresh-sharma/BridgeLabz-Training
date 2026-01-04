/*

Create a program to find the power of a number.
Hint => 
Get integer input for two variables - number and power and check for positive integer
Create a result variable with an initial value of 1.
Run a for loop from i = 1 to i <= power. In each iteration of the loop, multiply the result by the number and assign the value to the result. Finally, print the result

*/


import java.util.Scanner;
public class PowerOfNumberFor{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = in.nextInt();
	System.out.print("Enter the Power: ");
	int power = in.nextInt();
	int result = 1;

	// Print the power of number
		if(power<0 || num<=0){
			System.out.print("Please enter a positive number and power greater than or equal to zero.");
			return;
		}
		else {
			for(int i=1; i<=power; i++){
				result *= num;
			} 
		}

	System.out.print("Power of number is " + result);


	in.close();

	}

}






