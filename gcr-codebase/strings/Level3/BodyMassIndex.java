/*


level 3 Practice Programs
An organization took up the exercise to find the Body Mass Index (BMI) of all the persons in a team of 10 members. For this create a program to find the BMI and display the height, weight, BMI, and status of each individual
Hint => 
Take user input for the person's weight (kg) and height (cm) and store it in the corresponding 2D array of 10 rows. The First Column stores the weight and the second column stores the height in cm
Create a Method to find the BMI and status of every person given the person's height and weight and return the 2D String array. Use the formula BMI = weight / (height * height). Note unit is kg/m^2. For this convert cm to meter
Create a Method that takes the 2D array of height and weight as parameters. Calls the user-defined method to compute the BMI and the BMI Status and stores in a 2D String array of height, weight, BMI, and status.
Create a method to display the 2D string array in a tabular format of Person's Height, Weight, BMI, and the Status
Finally, the main function takes user inputs, calls the user-defined methods, and displays the result.


*/
import java.util.Scanner;
public class BodyMassIndex {
    public static void main(String[] args) {

	// Taking inputs
        Scanner in = new Scanner(System.in);
        int noOfPerson = 10;	
        double[][] personData = getHeightAndWeight(noOfPerson, in);
        String[][] bmiAndStatus = getBmiAndStatus(personData, noOfPerson);

	// Printing weight, height, bmi, status
        System.out.println("Weight  Height  BMI   Status");
        for (int i = 0; i < bmiAndStatus.length; i++) {
            System.out.println(
                bmiAndStatus[i][0] + "   " +
                bmiAndStatus[i][1] + "   " +
                bmiAndStatus[i][2] + "   " +
                bmiAndStatus[i][3]
            );
        }

        in.close();
    }

    // Method for getting data
    public static double[][] getHeightAndWeight(int noOfPerson, Scanner in) {

        double[][] personData = new double[noOfPerson][2];

        for (int i = 0; i < noOfPerson; i++) {
            System.out.print("Enter weight(kg) and height(cm) of person " + (i + 1) + " : ");
            personData[i][0] = in.nextDouble();   // weight
            double heightInCm = in.nextDouble();
            personData[i][1] = heightInCm / 100; // convert to meters
        }

        return personData;
    }

    // Method for finding BMI
    public static String bodyMassIndex(double weight, double height) {

        double BMIIndex = weight / (height * height);
        return String.format("%.2f", BMIIndex);
    }

    // Method for finding weight status
    public static String weightStatus(String BMI) {

        double BMIIndex = Double.parseDouble(BMI);

        if (BMIIndex <= 18.4) {
            return "Underweight";
        } else if (BMIIndex <= 24.9) {
            return "Normal";
        } else if (BMIIndex <= 39.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // Method for calculating BMI and status
    public static String[][] getBmiAndStatus(double[][] personData, int noOfPerson) {

        String[][] bmiAndStatus = new String[noOfPerson][4];

        for (int i = 0; i < noOfPerson; i++) {
            bmiAndStatus[i][0] = String.valueOf(personData[i][0]); // weight
            bmiAndStatus[i][1] = String.valueOf(personData[i][1]); // height
            bmiAndStatus[i][2] = bodyMassIndex(personData[i][0], personData[i][1]);
            bmiAndStatus[i][3] = weightStatus(bmiAndStatus[i][2]);
        }

        return bmiAndStatus;
    }
}
