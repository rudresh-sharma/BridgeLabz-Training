/*

Sam’s mark in Maths is 94, Physics is 95 and Chemistry is 96 out of 100. Find the average percent mark in PCM

I/P => NONE
O/P => Sam’s average mark in PCM is ___

*/

 

public class AveragePercentMarks{
	
	public static void main(String [] args){

 
		
		int MathsMarks = 94;
		int PhysicsMarks = 95;
		int ChemistryMarks = 96;

		float Average = (float) (MathsMarks + PhysicsMarks + ChemistryMarks) / 3;

		System.out.println("Sam’s average mark in PCM is " + Average +" %");

	}

}