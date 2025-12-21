/*

Extend or Create a UnitConvertor utility class similar to the one shown in the notes to do the following.  Please define static methods for all the UnitConvertor class methods. E.g. 
public static double convertYardsToFeet(double yards) => 
Method to convert yards to feet and return the value. Use following code to convert  double yards2feet = 3;
Method to convert feet to yards and return the value. Use following code to convert  double feet2yards = 0.333333;
Method to convert meters to inches and return the value. Use following code to convert  double meters2inches = 39.3701;
Method to convert inches to meters and return the value. Use following code to convert  double inches2meters = 0.0254;
Method to convert inches to centimeters and return the value. Use the following code  double inches2cm = 2.54;

*/


 import java.util.Scanner;
 class KmToMiles {

    // Method To convert kilometers to miles and return the value
    public double convertKmToMiles(double km) {
        // Convert km to miles
        double km2miles = 0.621371;
        double miles = km * km2miles;

        // return the value
        return miles;
    }

     
}




public class UnitConverter2 extends KmToMiles{
	public static void main(String[] args) {
		// Converting distance in multiple units and taking also
        	// Create a Scanner object
        	Scanner sc = new Scanner(System.in);

        	// Take input for km and printing distance in miles
        	System.out.print("Enter the distance in kilometers: ");
        	double km1 = sc.nextDouble();
        	KmToMiles kilometer = new KmToMiles();
        	double miles1 = kilometer.convertKmToMiles(km1); 
        	System.out.println("Distance in miles: " + miles1);

		// Take input for yards and printing distance in feet
        	System.out.print("Enter the distance in yards: ");
        	double yards1 = sc.nextDouble();
        	double feet1 = UnitConverter2.convertYardsToFeet(yard1); 
        	System.out.println("Distance in Feet: " + feet1);


		// Take input for feet and printing distance in yards
        	System.out.print("Enter the distance in Meters: ");
        	double feet2 = sc.nextDouble();
        	double yards2 = UnitConverter2.convertFeetToYards(feet2); 
        	System.out.println("Distance in Feet: " + yards2);




		// Take input for meter and printing distance in inches
        	System.out.print("Enter the distance in Meter: ");
        	double meter1 = sc.nextDouble();
        	double inches1 = UnitConverter2.convertMeterToInches(meter1); 
        	System.out.println("Distance in inches : " + inches1);



	// Take input for meter and printing distance in inches
        	System.out.print("Enter the distance in Inches: ");
        	double inches2 = sc.nextDouble();
        	double meter2 = UnitConverter2.convertInchesToMeter(inches2); 
        	System.out.println("Distance in meter : " + inches1);


	// Take input for meter and printing distance in inches
        	System.out.print("Enter the distance in Inches: ");
        	double inches3 = sc.nextDouble();
        	double cm = UnitConverter2.convertInchesToCM(inches3); 
        	System.out.println("Distance in Centimeter: " + cm);



        // Close the Scanner object
        sc.close();
    }



	// Method To convert kilometers to miles and return the value
    	public static double convertYardsToFeet(yard1) {
        	// Convert km to miles
        	double yards2feet  = 3;
        	double feet = yard1 * yards2feet;

        	// return the value
        	return feet;
	}


	// Method To convert kilometers to miles and return the value
    	public static double convertFeetToYards(double feet2) {
        	// Convert km to miles
        	double feet2yards = 0.333333;
        	double yards =  feet2 * feet2yards;

        	// return the value
        	return yards;
	}

	// Method To convert kilometers to miles and return the value
    	public static double convertMeterToInches(double meter1){
        	// Convert km to miles
        	double meters2inches = 39.3701;
        	double inches = meter1 * meters2inches;

        	// return the value
        	return inches;
	}


	// Method To convert kilometers to miles and return the value
    	public static double convertInchesToMeter(double inches2){
        	// Convert km to miles
        	double inches2meters = 0.0254;
        	double meter = inches2 * inches2meters ;

        	// return the value
        	return meter;
	}



	// Method To convert kilometers to miles and return the value
    	public static double convertInchesToCM(double inches3){
        	// Convert km to miles
        	double inches2cm = 2.54;
        	double cm = inches3 * inches2cm;

        	// return the value
        	return cm;
	}





}






