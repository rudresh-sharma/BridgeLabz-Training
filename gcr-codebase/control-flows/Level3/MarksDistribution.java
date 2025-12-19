/* Write a program to input marks and 3 subjects physics, chemistry and maths. Compute the percentage and then calculate the grade as per the following guidelines 

Hint => 
Ensure the Output clearly shows the Average Mark as well as the Grade and Remarks


*/





import java.util.Scanner;
public class MarksDistribution{
	public static void main(String[] args){
	
	// Taking input
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the Physics marks: ");
	int physicsMarks = in.nextInt();
	System.out.print("Enter the Chemistry marks: ");
	int ChemistryMarks = in.nextInt();
	System.out.print("Enter the Maths marks: ");
	int mathsMarks = in.nextInt();



	// Printing grade of student according to total Marks
	int totalMarks = physicsMarks + ChemistryMarks + mathsMarks;
	double percentage = totalMarks/3;
	

			 if(percentage>=80){
				 	System.out.println("AverageMarks = " + percentage + " Grade = A, and Remarks = Level 4, above agency-normalized standards ");
			}
			 else if(percentage>=70 && percentage<=79){
				 	System.out.println("AverageMarks = " + percentage + " Grade = B, and Remarks = Level 3, at agency-normalized standards ");
			}
			 else if(percentage>=60 && percentage<=69){
				 	System.out.println("AverageMarks = " + percentage + " Grade = C, and Remarks = Level 2, below, but approaching agency-normalized standards ");
					
			}
			 else if(percentage>=50 && percentage<=59){
				 	System.out.println("AverageMarks = " + percentage + " Grade = D, and Remarks = Level 1, well below agency-normalized standards ");
			}
			 else if(percentage>=40 && percentage<=49){
				 	System.out.println("AverageMarks = " + percentage + " Grade = E, and Remarks = Level 1 , too below agency-normalized standars ");
								}
		
			else {
				 	System.out.println("AverageMarks = " + percentage + " Grade = R, and Remarks = Remedial standards ");
			}
			



		

	
	in.close();
	}

}




