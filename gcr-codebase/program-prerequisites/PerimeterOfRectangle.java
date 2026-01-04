/*

 Perimeter of a Rectangle

Write a program to calculate the perimeter of a rectangle. Take the length
and width as inputs and use the formula:
Perimeter = 2 * (length + width).

*/



import java.util.Scanner;

public class PerimeterOfRectangle {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the length: ");
        int length = sc.nextInt();

        System.out.print("Enter the width: ");
        int width = sc.nextInt();


        int perimeter = 2 * (length+width);

        System.out.println("Perimeter = " + perimeter);

        sc.close();
    }
}