/*

Create a program to take input marks of students in 3 subjects physics, chemistry, and maths. Compute the percentage and then calculate the grade  as per the following guidelines 

Hint => 
Take input for the number of students
Create arrays to store marks, percentages, and grades of the students
Take input for marks of students in physics, chemistry, and maths. If the marks are negative, ask the user to enter positive values and decrement the index
Calculate the percentage and grade of the students based on the percentage
Display the marks, percentages, and grades of each studen


*/


import java.util.Scanner;
public class MarksDistribution{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of students: ");
	int n = in.nextInt();
	int[] physics   = new int[n];
	int[] chemistry = new int[n];
	int[] maths 	= new int[n];
	float[] percentages = new float[n];
	char[] grades = new char[n];		
		for(int i=0; i<n; i++){
			System.out.println("Enter the marks of student "+ (i+1) +": ");
			
			System.out.print("Enter the marks of Physics: ");
			physics[i] = in.nextInt();
			System.out.print("Enter the marks of Chemistry: ");
			chemistry[i] = in.nextInt();	
			System.out.print("Enter the marks of Maths: ");
			maths[i] = in.nextInt();
			float totalMarks = physics[i] + chemistry[i] + maths[i];
			float percentage = totalMarks/3;
			percentages[i] = percentage;
			System.out.println();

		}
		for(int i=0; i<n; i++){
			grades[i] = findingGrades(percentages[i]);
		}

	 // Printing the marks, percentages, and grades of each student	

		for(int i=0; i<n; i++){
			System.out.println("Details of student " + (i+1) +" : ");
			System.out.println("Marks in physics = " + physics[i]);
			System.out.println("Marks in chemistry = " + chemistry[i]);
			System.out.println("Marks in maths = " + maths[i] );
			System.out.println("Pecentages = " + percentages[i]);
			System.out.println("Grade = " + grades[i]);
			System.out.println();

		}


	in.close();


	}

	// Method for findigGrades
	public static char findingGrades(float percentage){
		if(percentage>=80){
 			return 'A';
		}
		else if(percentage>=70 && percentage<=79){
			return 'B';
		}
		else if(percentage>=60 && percentage<=69){
			return 'C';
		}
		else if(percentage>=50 && percentage<=59){
			return 'D';
		}
		else if(percentage>=40 && percentage<=49){
			return 'E';
		}
		else {
			return 'R';
			}
	}


}






