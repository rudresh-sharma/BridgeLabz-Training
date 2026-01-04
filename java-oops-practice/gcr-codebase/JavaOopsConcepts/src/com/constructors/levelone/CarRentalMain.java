package com.constructors.levelone;
import java.util.Scanner;
public class CarRentalMain{
	 public static void main(String[] args) {
		 
		 
		 	// Taking inputs
		 	Scanner in = new Scanner(System.in);
	        System.out.print("Enter customer name: ");
	        String name = in.nextLine();
	        System.out.print("Enter model (Tiago, Alto, XUV700): ");
	        String model = in.nextLine();
	        System.out.print("Enter number of rental days: ");
	        int days = in.nextInt();

	        // Constructor is used here
	        CarRental c1 = new CarRental(name, model, days);
	        c1.display();
	    }
}
