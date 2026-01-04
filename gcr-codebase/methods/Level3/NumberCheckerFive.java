/*

Extend or Create a NumberChecker utility class and perform following task. Call from main() method the different methods and display results. Make sure all are static methods
Hint => 
Method to Check if a number is a perfect number. Perfect numbers are positive integers that are equal to the sum of their proper divisors
Method to find the number is an abundant number. A number is called an abundant number if the sum of its proper divisors is greater than the number itself
Method to find the number is a deficient number. A number is called a deficient number if the sum of its proper divisors is less than the number itself
Method to Check if a number is a strong number. A number is called a strong number if the sum of the factorial of its digits is equal to the number itself


*/


import java.util.Scanner;
public class NumberCheckerFive {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number:");
	long num = in.nextLong();




	// Printing is given number prime, spy, neon, automorphic, buzz
	boolean isPerfect = isPerfect(num);
	boolean isAbundant = isAbundant(num);
	boolean isDeficient = isDeficient(num);
 
	boolean isStrong = isStrong(num);
	System.out.println("Given number " + num + " is " +" Perfect = " + isPerfect);
	System.out.println("Given number " + num + " is " +" Abundant = " + isAbundant);
	System.out.println("Given number " + num + " is " +" Deficient = " + isDeficient);
	System.out.println("Given number " + num + " is " +" Strong = " + isStrong);
 

	in.close();

	}



	// Method for checking num is perfect or not
	public static boolean isPerfect(long num){
		long sum=0;
		if(num<=0){
			return false;
		}
		for(long i=1; i<=num/2; i++){
			if(num%i == 0){
				sum += i;
			}
		}

		if(sum == num) 
			return true;
		else
			return false;		

	}


	// Method for checking num is abundant or not
	public static boolean isAbundant(long num){
	    if(num <= 0) return false;
		long divisorsSum = 0;
		for(long i=1; i<=num/2; i++){
			if(num%i == 0){
				divisorsSum += i;
			}
		}


		if(divisorsSum > num){
			return true;
		}
		else{
			return false;
		}
	}



	// Method for checking num is deficient or not
	public static boolean isDeficient(long num){
	    if(num <= 0) return false;
		long divisorsSum = 0;
			for(long i=1; i<=num/2; i++){
				if(num%i == 0){
					divisorsSum += i;
				}
			}


			if(divisorsSum < num){
				return true;
			}
			else{
				return false;
			}
	}


	// Method for finding factorial
	public static long getFactorial(long digit){
		
		if(digit ==1 || digit == 0){
			return 1;
		}

		return digit*getFactorial(digit-1);
	}




	// Method for checking num is deficient or not
	public static boolean isStrong(long num){
	    if(num <= 0) return false;
		long sum = 0;
	    long original = num;

			while(num>0){
				long digit = num%10;
				sum += getFactorial(digit);
				num /= 10;
			}
			
			if(sum == original)
			    return true;
			 else
			    return false;
	}

}



