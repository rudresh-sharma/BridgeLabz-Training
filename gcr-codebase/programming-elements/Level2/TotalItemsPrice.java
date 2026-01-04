/*

Write a program to input the unit price of an item and the quantity to be bought. Then, calculate the total price.
Hint => NA
I/P => unitPrice, quantity
O/P => The total purchase price is INR ___ if the quantity ___ and unit price is INR ___


*/


import java.util.Scanner;

// Program to calculate total price of purchased items
public class TotalItemsPrice {
    public static void main(String[] args) {

        // ----- input -----
        // create Scanner object to read input from user
        Scanner input = new Scanner(System.in);

        // ask user to enter the unit price of an item
        System.out.print("Enter the unit Price: ");
        float unitPrice = input.nextFloat();

        // ask user to enter the quantity bought
        System.out.print("Enter the Quantity bought: ");
        int quantity = input.nextInt();

        // ----- calculation -----
        // calculate total price = unit price * quantity
        float totalPrice = unitPrice * quantity;

        // ----- output -----
        // print total price along with quantity and unit price
        System.out.println(
            "The total purchase price is INR " + totalPrice +
            " if the quantity " + quantity +
            " and unit price is INR " + unitPrice
        );
    }
}

