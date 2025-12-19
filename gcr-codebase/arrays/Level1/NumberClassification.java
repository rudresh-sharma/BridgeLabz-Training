/*

Write a program to take user input for 5 numbers and check whether a number is positive,  negative, or zero. Further for positive numbers check if the number is even or odd. Finally compare the first and last elements of the array and display if they equal, greater or less
Hint => 
Define an integer array of 5 elements and get user input to store in the array.
Loop through the array using the length If the number is positive, check for even or odd numbers and print accordingly
If the number is negative, print negative. Else if the number is zero, print zero. 
Finally compare the first and last element of the array and display if they equal, greater or less


*/




import java.util.Scanner;
public class NumberClassification{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the 5 numbers: ");
	int[] nums = new int[5];
		for(int i=0; i<5; i++){
			nums[i] = in.nextInt();
		}

	
	// Printing decision whether number is +,-,0 and even odd and last and first relation

		for(int i=0; i<5; i++){
			if(nums[i] == 0){
				System.out.println( "Number " + (i+1) + " is zero");
			}
			else if(nums[i]<0){
				System.out.println("Number "+ (i+1) + "  is negative");
			}
			else if(nums[i]%2 == 0){
				System.out.println("Number "+ (i+1) + " is even");
			}
			else {
				System.out.println("Number "+ (i+1) + "  is odd");
			}
		}

		if(nums[0]>nums[4]){
			System.out.println("First number is greater than last numberr");
		}
		else if(nums[0] < nums[4]){
			System.out.println("First number is less than last numberr");

		}
		else{
			System.out.println("First number is equal to last numberr");
		}



	in.close();
	
	}

}







