/*

Create a program to find the maximum number of handshakes among students.
Hint => 
Get integer input for the numberOfStudents variable.
Use the combination = (n * (n - 1)) / 2 formula to calculate the maximum number of possible handshakes.
Write a method to use the combination formulae to calculate the number of handshakes
Display the number of possible handshakes.

*/



import java.util.Scanner;
public class MaximumHandShakes{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of students");
	int numberOfStudents = in.nextInt();


	//Printing total number if handshakes bw in n students
	int numberOfHandShakes = numberOfHandShakes(numberOfStudents);
	System.out.println("Number of possible handshakes in " + numberOfStudents + " are " + numberOfHandShakes);


	in.close();

	}



	// Method for maximum number of handshakes in n peoples
	public static int numberOfHandShakes(int n){
		return (n * (n - 1)) / 2;
	}

}