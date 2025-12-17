/*

Write a program that takes the base and height to find area of a triangle in square inches and square centimeters 
Hint => Area of a Triangle is ½ * base * height
I/P => base, height
O/P => Your Height in cm is ___ while in feet is ___ and inches is __

*/


import java.util.Scanner;

public class AreaOfTriangle {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // ----- input -----
        System.out.print("Enter the base and height of the triangle (in cm): ");
        float base = input.nextFloat();
        float height = input.nextFloat();

        // ----- calculation -----
        float areaInCentimeter = 0.5f * base * height;

        // convert cm² to in² (1 cm² ≈ 0.155 in²)
        float areaInInches = areaInCentimeter * 0.155f;

        // ----- output -----
        System.out.println(
            "Area of triangle with base " + base + " cm and height " + height +
            " cm is " + areaInCentimeter + " in square cm and " + areaInInches + " in square inches."
        );
    }
}
