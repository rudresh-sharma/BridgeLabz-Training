/*

Extend or Create a UnitConvertor utility class similar to the one shown in the notes to do the following.  Please define static methods for all the UnitConvertor class methods. E.g. 
public static double convertFarhenheitToCelsius(double farhenheit) => 
Method to convert Fahrenheit to Celsius and return the value. Use the following code  double farhenheit2celsius = (farhenheit - 32) * 5 / 9;
Method to convert Celsius to Fahrenheit and return the value. Use the following code  double celsius2farhenheit = (celsius * 9 / 5) + 32;
Method to convert pounds to kilograms and return the value. Use the following code  double pounds2kilograms = 0.453592;
Method to convert kilograms to pounds and return the value. Use the following code  double kilograms2pounds = 2.20462; 
Method to convert gallons to liters and return the value. Use following code to convert   double gallons2liters = 3.78541; 
Method to convert liters to gallons and return the value. Use following code to convert  double liters2gallons = 0.264172;

*/


public class UnitConverter3 {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	
	// Taking Farhenheit and converting to celsius
	System.out.print("Enter the Farhenheit: ");
	double farhenheit1 = in.nextDouble();
	double celsius1 = convertFarhenheitToCelsius(farhenheit1);
	System.out.println("Given farhenheit in celsius = " + celsius1 );




	// Taking Celsius and converting to Farhenheit
	System.out.print("Enter the Celsius: ");
	double celsius2 = in.nextDouble();
	double farhenheit2  = convertCelsiusToFarhenheit(celsius2);
	System.out.println("Given celsius   in farhenheit = " +  farhenheit2 );



	// Taking Pounds and converting to Kilogram
	System.out.print("Enter the Pounds: ");
	double pounds1 = in.nextDouble();
	double kilograms1 = convertPoundsTokilograms(pounds1);
	System.out.println("Given pounds in Kilograms = " +  kilograms1 );


	// Taking kilograms and converting to pounds
	System.out.print("Enter the kilogram : ");
	double kilograms2  = in.nextDouble();
	double pounds2 = convertKilogramsToPounds(kilograms2);
	System.out.println("Given kilograms in pounds = " + pounds2 );



	// Taking gallons in converting to Liters
	System.out.print("Enter the Galoons: ");
	double galoons1 = in.nextDouble();
	double liters1 = convertGallonsToLiters(galoons1);
	System.out.println("Given galoons in liters = " + liters1 );


	// Taking literes and converting to galoons
	System.out.print("Enter the Liters : ");
	double liters2 = in.nextDouble();
	double galoons2 = convertLiters2Gallons(liters2);
	System.out.println("Given literes in galoon = " + galoons2 );


	in.close();
	
	}

	
	// Method for converting farhenheit to celsius
	public static double convertFarhenheitToCelsius(double farhenheit1){
		return (farhenheit1 - 32) * 5 / 9;
	}


	// Method for converting  celsius to farhenheit 
	public static double convertCelsiusToFarhenheit(double celsius2){
		return (celsius2 * 9 / 5) + 32;
	}


	// Method for converting pounds to kilograms
	public static double convertPoundsTokilograms(double pounds1){
		double pounds2kilograms = 0.453592;
		double kilograms = pounds1 * pounds2kilograms;
		
		return kilograms;
	}

	// Method for converting kilograms to pounds
	public static double convertKilogramsToPounds(double kilograms2){
		double kilograms2pounds = 2.20462; 
		double pounds = kilograms2 * kilograms2pounds;
		
		return pounds ;
	}


	// Method for converting gallon to liters
	public static double convertGallonsToLiters(double galoons1){
		double gallons2liters = 3.78541; 
		double liters = galoons1*gallons2liters;
		
		return liters;
	}

	// Method for converting liters to galoon
	public static double convertLiters2Gallons(liters2){
		double liters2gallons = 0.264172; 
		double galoons = liters2 * liters2gallons;
	
		return galoons;
	}

