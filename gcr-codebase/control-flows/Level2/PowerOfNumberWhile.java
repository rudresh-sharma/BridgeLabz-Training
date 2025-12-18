/*

Rewrite the above program to find the power of a number using a while loop.
Hint => 
Get integer input for two variables named number and power.
Create a result variable with an initial value of 1.
Create a temp variable counter and initialize to zero. Use the while loop till _**counter == power**_.
In each iteration of the loop, multiply the result by the number and assign the value to the result. Also, increment the counter.
Finally, print the result

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
			int counter =1;
			while(counter<=power){
				result *= num;
				counter++;
			} 
		}

	System.out.print("Power of number is " + result);


	in.close();

	}

}






