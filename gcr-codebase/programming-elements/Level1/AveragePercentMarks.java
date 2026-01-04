/*

Sam’s mark in Maths is 94, Physics is 95 and Chemistry is 96 out of 100. Find the average percent mark in PCM

I/P => NONE
O/P => Sam’s average mark in PCM is ___

*/

 

// Main class to calculate average percentage of marks
public class AveragePercentMarks {

    // Main method – program execution starts here
    public static void main(String[] args) {

	// Marks obtained in individual subjects
        int MathsMarks = 94;
        int PhysicsMarks = 95;
        int ChemistryMarks = 96;

        // Calculating average percentage
        // Type casting to float is done to get decimal value
        float Average = (float) (MathsMarks + PhysicsMarks + ChemistryMarks) / 3;


	        // Printing the final average percentage
        System.out.println("Sam’s average mark in PCM is " + Average + " %");

    }
}

