/*

Write a program to generate a six-digit OTP number using Math.random() method. Validate the numbers are unique by generating the OTP number 10 times and ensuring all the 10 OTPs are not the same
Hint => 
Write a method to generate a 6-digit OTP number using Math.random() 
Create an array to save the OTP numbers generated 10 times
Write a method to ensure that the OTP numbers generated are unique. If unique return true else return false


*/

import java.util.Scanner;
public class OTPGeneration{
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	

	// Printing 10 otps of 6 digits each
	int[] otpS = getAllOTPS();
	System.out.println("All the otps generated are: ");
		for(int i=0; i<10; i++){
			System.out.println(otpS[i]);
		}
	boolean allDifferent = isOTPSDifferent(otpS);
	System.out.println("Generated otps are different = " + allDifferent);


	in.close();

	}


	// Method to generate 6 digit otp
	public static int generateOTP(){
		int n = 6; // number of digits	
		int randomNumber = (int)(Math.random() * 9 * Math.pow(10, n - 1)) + (int)Math.pow(10, n - 1);

		return randomNumber;

	}



	// Method to get 10 OTPS
	public static int[] getAllOTPS(){
		int[] otpS = new int[10];
		for(int i=0; i<10; i++){
			otpS[i] = generateOTP();
		}

		return otpS;
	}


	// Is all different OTPS
	public static boolean isOTPSDifferent(int[] otpS){
		for(int i=0; i<10; i++){
			for(int j=i+1; j<10; j++){
				if(otpS[i] == otpS[j]){
					return false;
				}
			}
		}
		
		return true;

	}


}




