/* 

Write a new program similar to the program #6 but take user input for Student Fee and University Discount
Hint => 
Create a variable named fee and take user input for fee.
Create another variable discountPercent and take user input.
Compute the discount and assign it to the discount variable.
Compute and print the fee you have to pay by subtracting the discount from the fee.
I/P => fee, discountPrecent
O/P => The discount amount is INR ___ and final discounted fee is INR ___



*/


import java.util.Scanner;

import java.util.Scanner;

// Program to calculate discounted university fees based on user input
public class UniversityDiscountFeesInput {

    // main method
    public static void main(String[] args) {

        // ----- input -----
        // create Scanner object to get input from user
        Scanner input = new Scanner(System.in);

        // asking user to enter the original fee
        System.out.print("Enter the Fees : ");
        float fee = input.nextFloat();

        // asking user to enter the discount percentage
        System.out.print("Enter the University Discount : ");
        float discountPercent = input.nextFloat();

        // ----- calculation -----
        // calculating the discount amount
        float discount = (fee * discountPercent) / 100;

        // calculating the final fee after applying discount
        float discountedFee = fee - discount;

        // ----- output -----
        // printing the discount and final fee
        System.out.println(
            "The discount amount is INR " + discount +
            " and final discounted fee is INR " + discountedFee
        );
    }
}
