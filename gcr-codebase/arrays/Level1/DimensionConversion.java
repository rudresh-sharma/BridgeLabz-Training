/*

Working with Multi-Dimensional Arrays. Write a Java program to create a 2D Array and Copy the 2D Array into a single dimension array
Hint => 
Take user input for rows and columns, create a 2D array (Matrix), and take the user input 
Copy the elements of the matrix to a 1D array. For this create a 1D array of size rows*columns as in int[] array = new int[rows * columns];
Define the index variable and Loop through the 2D array. Copy every element of the 2D array into the 1D array and increment the index
Note: For looping through the 2D array, you will need Nested for loop, Outer for loop for rows, and the inner for loops to access each element

*/


import java.util.Scanner;
public class DimensionConversion{
	public static void main(String[] args){
	
	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the no. of rows: ");
	int rows = in.nextInt();
	System.out.print("Enter the no. of columns: ");
	int columns = in.nextInt();
	System.out.print("Enter the elements: ");
	
	int[][] twoD = new int[rows][columns];
		for(int i=0; i<rows; i++){
			for(int j=0; j<rows; j++){
				
				twoD[i][j] = in.nextInt();
			}
		}
	
	// Copying elements
	int index = 0;
	int[] oneD = new int[rows*columns];
		for(int i=0; i<rows; i++){
			for(int j=0; j<rows; j++){
				oneD[index] = twoD[i][j];
				index++;
			}
		}

	

	// Printing elements
	System.out.println("Elements of Two Dimensional are as: ");
		for(int i=0; i<oneD.length; i++){
			System.out.println(oneD[i]);			
		}
	

	in.close();
	
	}

}


