/*

Write a program to find the factors of a number and perform various tasks using the factors array
Hint => 
Method to find factors of a number and return them as an array. Note there are 2 for loops: one for the count and another for finding the factor and storing in the array
Method to find the greatest factor of a Number using the factors array
Method to find the sum of the factors using factors array and return the sum
Method to find the product of the factors using factors array and return the product
Method to find the product of the cube of the factors using the factors array. Use Math.pow() 

*/

import java.util.Scanner;
public class FactorsManipulation {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number:");
	long num = in.nextLong();


	// Printing factors of number,greatest factor, their sum, product of factors , product of their cubes;
	long[] factors = getFactors(num);
    int countFactors = factors.length;
	long greatestFactor = getGreatestFactor(factors,countFactors);
	long sumOfFactors = sumOfFactors(factors, countFactors);
	long productOfFactors = productOfFactors(factors, countFactors);
	long cubeProductOfFactors = cubeProductOfFactors(factors, countFactors);
	System.out.println("Factors of given number " + num + " are");
		for(int i=0; i<countFactors; i++)
			System.out.println("Factors " + (i+1) + " = " + factors[i]);
	System.out.println("Sum of all Factors of " + num + " is " + sumOfFactors);
	System.out.println("Product of all Factors of " + num + " is " + productOfFactors );
	System.out.println("Product of cube of all Factors of " + num + " is " + cubeProductOfFactors);

	in.close();

	}


	// Method for getting factors of num
	public static long[] getFactors(long num){
    if(num <= 0) return new long[0];

    int count = 0;
    for(long i = 1; i <= num; i++){
        if(num % i == 0) count++;
    }

    long[] factors = new long[count];
    int index = 0;
    for(long i = 1; i <= num; i++){
        if(num % i == 0){
            factors[index++] = i;
        }
    }
    return factors;
}



  


	// Method for finding greatest factor
	public static long getGreatestFactor(long[]factors, int countFactors){
		long max = factors[0];
		for(int i=1; i<countFactors; i++){
			if(factors[i]>max){
				max = factors[i];
			}
		}
		
		return max;
	}


	// Method to Find the sum of factors
	public static long sumOfFactors(long[] factors, int countFactors){
		long sum = 0;
		for(int i=0; i<countFactors; i++){
			 sum += factors[i];
		}

		return sum;
	}


	// Method to Find Product of factors
	public static long productOfFactors(long[] factors, int countFactors){
		long prod = 1;
		for(int i=0; i<countFactors; i++){
			 prod *= factors[i];
		}

		return prod;
	}


	// Method to Find Product of cubes of factors
	public static long cubeProductOfFactors(long[] factors, int countFactors){
		long prod = 1;
		for(int i=0; i<countFactors; i++){
			 prod *= (long) Math.pow(factors[i],3);
		}

		return prod;
	}


}




