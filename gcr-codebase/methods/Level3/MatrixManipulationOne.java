/*

Write a program to perform matrix manipulation operations like addition, subtraction, and multiplication. The program should take random matrices as input and display the result of the operations.
Hint => 
Write a Method to create a random matrix, taking rows and columns as parameters
Write a Method to add two matrices
Write a Method to subtract two matrices
Write a Method to multiply two matrices


*/

import java.util.Scanner;
public class MatrixManipulationOne{
	public static void main(String[] args){

	// Taking input for 2D matrix
	Scanner in = new Scanner(System.in);
	int[][] matrix1 = generateRandomMatrix();
	int[][] matrix2 = generateRandomMatrix();
 

	int[][] matrixAddition = matrixAddition(matrix1, matrix2);
	int[][] matrixSubtraction = matrixSubtraction(matrix1, matrix2);
	int[][] matrixMultiplication = matrixMultiplication(matrix1,matrix2);


	System.out.println("Matrix 1: ");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.print(matrix1[i][j] + " ");
			}
			System.out.println();
		}

	System.out.println("Matrix 2: ");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.print(matrix2[i][j] + " ");
			}
			System.out.println();
		}	


	System.out.println("Matrixes Addition: ");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.print(matrixAddition[i][j] + " ");
			}
			System.out.println();
		}

	System.out.println("Matrixes Subtraction: ");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.print(matrixSubtraction[i][j] + " ");
			}
			System.out.println();
		}

	System.out.println("Matrixes Multiplication: ");
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				System.out.print(matrixMultiplication[i][j] + " ");
			}
			System.out.println();
		}
	
	in.close();
	
	}



	// Method to generate Random matrix
	public static int[][] generateRandomMatrix(){
		int[][] matrix = new int[3][3];
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				matrix[i][j] = (int) (Math.random ()* 10); 
			}
 
		}

		return matrix;

	}

	

	// Method to do matrixAddition

	public static int[][] matrixAddition(int[][]matrix1, int[][]matrix2){
		int[][] matrix = new int[3][3];
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				matrix[i][j] = matrix1[i][j] + matrix2[i][j]; 
			}
 
		}

		return matrix;

	}
	
	
	// Method to do matrixMultiplication
	public static int[][] matrixMultiplication(int[][]matrix1, int[][]matrix2){
		int[][] matrix = new int[3][3];
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					matrix[i][j] += matrix1[i][k] * matrix2[k][j]; 
				}
			}
 
		}

		return matrix;

	}


	// Method to do matrixAddition

	public static int[][] matrixSubtraction (int[][]matrix1, int[][]matrix2){
		int[][] matrix = new int[3][3];
		
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				matrix[i][j] = matrix1[i][j] - matrix2[i][j]; 
			}
 
		}

		return matrix;

	}


}










