/*

Write a program to find the smallest and the largest of the 3 numbers.
Hint => 
Take user input for 3 numbers
Write a single method to find the smallest and largest of the three numbers


*/



import java.util.Scanner;
public class SmallestAndLargest{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number 1: ");
	int num1 = in.nextInt();
	System.out.print("Enter the number 2: ");
	int num2 = in.nextInt();	
	System.out.print("Enter the number 3: ");
	int num3 = in.nextInt();



	//Printing smallest and largest
	int[] smallestAndLargest = findSmallestAndLargest(num1, num2, num3);


	System.out.println("Largest number in 3 numbers is :" + smallestAndLargest[1]);
	System.out.println("Smallest number in 3 numbers is :" + smallestAndLargest[0]);



	in.close();

	}



	// Method for getting smallest and largest
	public static int[] findSmallestAndLargest( int num1, int num2, int num3){	
		int[] temp = new int[2];		
		
			//  getting smallest
			if((num1<num2) && (num1<num3))
				temp[0] = num1 ;
			else if((num2<num1) && (num2<num3))
				temp[0] = num2;
			else 
				temp[0] = num3;

			//  getting larest
			if((num1>num2) && (num1>num3))
				temp[1] = num1 ;
			else if((num2>num1) && (num2>num3))
				temp[1] = num2;
			else 
				temp[1] = num3;

			return temp;		

	}

}






