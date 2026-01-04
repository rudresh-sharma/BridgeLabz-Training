/*


Create a program to find the factors of a number taken as user input, store the factors in an array, and display the factors. Also find the sum, sum of square of factors and product of the factors and display the results
Hint => 
Take the input for a number
Write a static Method to find the factors of the number and save them in an array and return the array. 
To find factors and save to array will have two loops. The first loop to find the count and initialize the array with the count. And the second loop save the factors into the array
Write a method to find the sum of the factors using factors array
Write a method to find the product of the factors using factors array
Write a method to find the sum of square of the factors using Math.pow() method


*/

import java.util.Scanner;
public class FactorsComputation{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();


	// Generating factors and doing computation on them
	long[] factors = calculateFactors(number);
	int count=0;
		for(int i=0; i<factors.length; i++){
			if(factors[i] != 0){
				count++;
			}
			else{
				break;
			}
		}
			System.out.print(count);

	long sum  = findFactorsSum(factors, count);
	long product = findProduct(factors, count);
	long squareSum = findSquareSum(factors, count);

	System.out.println("Factors of given number " + number + " are ");
		for(int i=0; i<count; i++){
			System.out.println("Factor " + (i+1) + " = " + factors[i]);
		}

	System.out.println("Sum of all the factors is: " + sum);
	System.out.println("Product of all factors is:" + product);
	System.out.println("Sum of square each  factors is:" + squareSum);

	in.close();

	}


	// Method for getting factors 
	public static long[] calculateFactors(int number){
		int maxIndex = 10;
		long[] factorsInside = new long[maxIndex];
		int count =0;
		for(int i=1; i<=number/2; i++){
			if (count == maxIndex) {
               			maxIndex *= 2;
                		long[] temp = new long[maxIndex];	
               			for (int j = 0; j < count; j++) {
                    			temp[j] = factorsInside[j];
               			 }

               			 factorsInside = temp;
           		 }

            		if (number % i == 0) {
				factorsInside[count] = i;
              		        count++;
          		 }
		}
		
		return factorsInside;

	}


	// Method for getting factor sum
	public static long findFactorsSum(long[] factors, int count){
		long sum = 0;
		for(int i=0; i<count; i++){
			sum += factors[i];
		}

		return sum;
	}


	// Method for getting product of factors
	public static long findProduct(long[] factors, int count){
		long product = 1;
		for(int i=0; i<count; i++){
			product *= factors[i];
		}

		return product;
	}


	// Method for getting square sum of factors
	public static long findSquareSum(long[] factors, int count){
		long sum = 0;
		for(int i=0; i<count; i++){
			sum += Math.pow(factors[i],2);
		}

		return sum;
	}



}










	
