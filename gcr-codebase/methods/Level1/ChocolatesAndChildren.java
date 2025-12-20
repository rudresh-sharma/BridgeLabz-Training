/*

Create a program to divide N number of chocolates among M children. Print the number of chocolates each child will get and also the remaining chocolates
Hint => 
Get an integer value from user for the numberOfchocolates and numberOfChildren.
Write the method to find the number of chocolates each child gets and number of remaining chocolates
public static int[] findRemainderAndQuotient(int number, int divisor) 


*/


import java.util.Scanner;
public class ChocolatesAndChildren{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of Chocolates: ");
	int numberOfChocolates = in.nextInt();
	System.out.print("Enter the number of Childrens: ");
	int numberOfChildren = in.nextInt();


	// Printing no of chocolates each child will get and remaing chocolates
		if (numberOfChildren <= 0 || (numberOfChocolates<numberOfChildren)) {
           		System.out.println("We can't divide chocolates.");
        	} else {
			int[] chocolates = findRemainderAndQuotient(numberOfChocolates, numberOfChildren);
        	        System.out.println("Number of chocolates each child will get: " + chocolates[0]);
                        System.out.println("Number of remaining chocolates: " + chocolates[1]);
	        }


	in.close();


	}



	// Method for getting numberOfchocolates each child will get and remaining 
	public static int[] findRemainderAndQuotient(int number, int divisor){
		
		int[] temp = new int[2];
		temp[0] = number/divisor;
		temp[1] = number%divisor;
		
		return temp;

	}


}






	










