/*

Rewrite the above program to store the marks of the students in physics, chemistry, and maths in a 2D array and then compute the percentage and grade
Hint => 
All the steps are the same as the problem 8 except the marks are stored in a 2D array
Use the 2D array to calculate the percentages, and grades of the students


*/



import java.util.Scanner;
public class MarksDistribution{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number of students: ");
	int num = in.nextInt();
	float[][] marks = new float[num][3]; 
	float[] percentages = new float[num];
	char[] grades = new char[num];
		for(int i=0; i<num; i++){
			System.out.print("Enter the marks of student "+ (i+1)+ " order by PCM"  +": ");
			for(int j=0; j<3; j++){
				marks[i][j] = in.nextFloat();
			}
			float totalMarks = marks[i][0] + marks[i][1]+ marks[i][2];
			float percentage = totalMarks/3;
			percentages[i] = percentage;
			System.out.println();

		}
		for(int i=0; i<num; i++){
			grades[i] = grades(percentages[i]);
		}

	 // Printing the marks, percentages, and grades of each student	

		for(int i=0; i<num; i++){
			System.out.println("Details of student " + (i+1) +" : ");
			System.out.println("Marks in physics = " + marks[i][0]);
			System.out.println("Marks in chemistry = " + marks[i][1]);
			System.out.println("Marks in maths = " + marks[i][2]);
			System.out.println("Pecentages = " + percentages[i]);
			System.out.println("Grade = " + grades[i]);
			System.out.println();

		}


	in.close();


	}


	public static char grades(float percentage){
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

