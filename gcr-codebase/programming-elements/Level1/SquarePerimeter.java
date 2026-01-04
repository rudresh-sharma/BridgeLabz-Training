/*

Write a program to find the side of the square whose parameter you read from user 
Hint => Perimeter of Square is 4 times side
I/P => perimeter
O/P => The length of the side is ___ whose perimeter is ____


*/

import java.util.Scanner;

// Program to calculate the perimeter of a square
public class SquarePerimeter {
    public static void main(String[] args) {

        // ----- input -----
        // create Scanner object to read input from user
        Scanner input = new Scanner(System.in);

        // ask user to enter the length of the square's side
        System.out.print("Enter the side of square: ");
        float side = input.nextFloat();

        // ----- calculation -----
        // calculate perimeter (perimeter = 4 * side)
        float perimeter = 4.0f * side;

        // ----- output -----
        // print the side length and perimeter
        System.out.print(
            "The length of the side is " + side + " whose perimeter is " + perimeter
        );
    }
}
