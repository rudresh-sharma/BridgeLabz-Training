/*

Create a program to find the factors of a number taken as user input, store the factors in an array, and display the factors
Hint => 
Take the input for a number
Find the factors of the number and save them in an array. For this create integer variable maxFactor and initialize to 10, factors array of size maxFactor and index variable to reflect the index of the array. 
To find factors loop through the numbers from 1 to the number, find the factors, and add them to the array element by incrementing the index. If the index is equal to maxIndex, then need factors array to store more elements
To store more elements, reset the maxIndex to twice its size, use the temp array to store the elements from the factors array, and eventually assign the factors array to the temp array
Finally, Display the factors of the number

*/


import java.util.Scanner;
public class Factors{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number: ");
	int number = in.nextInt();
	int maxIndex = 10;
	int[] factors = new int[maxIndex];
	
	
	// Collecting factors in array and printing them;	
	int count =0;
		for(int i=1; i<=number/2; i++){
			if (count == maxIndex) {
               			maxIndex *= 2;
                		int[] temp = new int[maxIndex];	
               			for (int j = 0; j < count; j++) {
                    			temp[j] = factors[j];
               			 }

               			 factors = temp;
           	 }

            if (number % i == 0) {
                factors[count] = i;
                count++;
            }
		}
		System.out.println("Factors of given number " + number + " are ");
		for(int i=0; i<count; i++){
			System.out.println("Factor " + (i+1) + " is " + factors[i]);
		}
		
	in.close();

	}

}




			