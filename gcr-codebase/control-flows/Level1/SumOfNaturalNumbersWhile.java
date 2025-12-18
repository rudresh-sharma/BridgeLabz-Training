/*

Write a program to find the sum of n natural numbers using while loop compare the result with the formulae n*(n+1)/2 and show the result from both computations was correct. 
Hint => 
Take the user input number and check whether it's a Natural number
If it's a natural number Compute using formulae as well as compute using while loop
Compare the two results and print the result


*/



import java.util.Scanner;
public class SumOfNaturalNumbersWhile{
	public static void main(String [] args){

	// Taking inputs
	Scanner input = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int num = input.nextInt();
	int sumByFormula = num*(num+1)/2;
	int sumByLoop = 0;

	
	// --- Calculation ---
		//
		if(num <= 0){
		//
			System.out.println("Number is not a natural number ");
		}
		//
		else{
		//
			while(num>=1){
				sumByLoop += num;
				num -= 1;
			}
		//
			if(sumByFormula == sumByLoop){
		//
				System.out.println("Result from both computation is correct");
			}
		}



	input.close();
	
	}


}

