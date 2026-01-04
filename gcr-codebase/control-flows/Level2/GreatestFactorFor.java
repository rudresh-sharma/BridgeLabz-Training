/*
Create a program to print the greatest factor of a number beside itself using a loop.
Hint => 
Get an integer input and assign it to the number variable. As well as define a greatestFactor variable and assign it to 1
Create a for loop that runs from last but one till 1 as in i = number - 1 to i = 1.
Inside the loop, check if the number is perfectly divisible by i then assign i to greatestFactor variable and break the loop.
Display the greatestFactor variable outside the loop


*/

import java.util.Scanner;
public class GreatestFactor{
	public static void main(String[] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = input.nextInt();
	int greatestFactor = 1;
	
	//Printing Greatest factor
		for(int i=num-1; i>=1; i--){
			if(num%i ==0) {
				greatestFactor = i;
				break;
			}
		}

	System.out.print("Greatest Factor is: " + greatestFactor);

	}

}





