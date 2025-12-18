/*
Rewrite the above program to print the greatest factor of a number beside itself using a while loop.
Hint => 
Get an integer input and assign it to the number variable. As well as define a greatestFactor variable and assign it to 1
Create a variable counter and assign counter = number - 1; Use the while loop till the counter is equal to 1.
Inside the loop, check if the number is perfectly divisible by the counter then assign the counter to greatestFactor variable and break the loop.
Display the greatestFactor variable outside the loop

*/


import java.util.Scanner;
public class GreatestFactorWhile{
	public static void main(String[] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = input.nextInt();
	int greatestFactor = 1;
	
	//Printing Greatest factor
	int counter = num-1;
		while(counter>=1){
			if(num%counter ==0) {
				greatestFactor = counter;
				break;
			}
			counter--;
		}

	System.out.print("Greatest Factor is: " + greatestFactor);

	}

}





