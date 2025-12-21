/*
Extend or Create a UnitConvertor utility class similar to the one shown in the notes to do the following.  Please define static methods for all the UnitConvertor class methods. E.g. 
public static double convertKmToMiles(double km) => 
Method To convert kilometers to miles and return the value. Use the following code  double km2miles = 0.621371;
Method to convert miles to kilometers and return the value. Use the following code  double miles2km = 1.60934;
Method to convert meters to feet and return the value. Use the following code to convert  double meters2feet = 3.28084;
Method to convert feet to meters and return the value. Use the following code to convert  double feet2meters = 0.3048;

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


public class UnitConverter1 extends KmToMiles{
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

		// Take input for miles and printing distance in km
        	System.out.print("Enter the distance in miles: ");
        	double miles2 = sc.nextDouble();
        	double km2 = UnitConverter1.convertMilesToKm(miles2); 
        	System.out.println("Distance in Kilometer: " + km2);


		// Take input for meter and printing distance in feet
        	System.out.print("Enter the distance in Meters: ");
        	double meter1 = sc.nextDouble();
        	double feet1 = UnitConverter1.convertMeterToFeet(meter1); 
        	System.out.println("Distance in Feet: " + feet1);




		// Take input for feet and printing distance in meter
        	System.out.print("Enter the distance in Feet: ");
        	double feet2 = sc.nextDouble();
        	UnitConverter unitConverter = new UnitConverter();
        	double meter2 = UnitConverter1.convertFeetToMeter(feet2); 
        	System.out.println("Distance in meter: " + meter2);


        // Close the Scanner object
        sc.close();
    }



	// Method To convert kilometers to miles and return the value
    	public static double convertMilesToKm(double miles) {
        	// Convert km to miles
        	double miles2km = 1.60934;
        	double km = miles * miles2km;

        	// return the value
        	return km;
	}


	// Method To convert kilometers to miles and return the value
    	public static double convertMeterToFeet(double meter) {
        	// Convert km to miles
        	double meter2Feet =  3.28084;
        	double feet =  meter * meter2Feet;

        	// return the value
        	return feet;
	}

	// Method To convert kilometers to miles and return the value
    	public static double convertFeetToMeter(double feet) {
        	// Convert km to miles
        	double feet2Meter =  0.3048;
        	double meter = feet * feet2Meter;

        	// return the value
        	return meter;
	}


}








