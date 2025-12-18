/*

Rewrite the program number 12 with the for loop instead of a while loop to find the sum of n Natural Numbers. 
Hint => 
Take the user input number and check whether it's a Natural number
If it's a natural number Compute using formulae as well as compute using for loop
Compare the two results and print the result


*/



import java.util.Scanner;

public class SumOfNaturalNumbersFor{
	public static void main(String [] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = input.nextInt();
	int sumByFormula = num*(num+1)/2;
	int sumByLoop = 0;

	
	// --- Calculation ---
		// checking number is natural or not
		if(num < 0){
		//
			System.out.println("Number is not a natural number ");
		}
		// Number is natural as condition is false
		else{
		// Calculating sum using loop
			for(int i=num; i>=1; i--){
				sumByLoop += num;
				num -= 1;
			}
		// comparing sum done by both
			if(sumByFormula == sumByLoop){
		// Printing results
				System.out.println("Result from both computation is correct");
			}
		}



	input.close();
	
	}


}

