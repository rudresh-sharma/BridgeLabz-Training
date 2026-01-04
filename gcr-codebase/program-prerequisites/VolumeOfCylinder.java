/*
  Volume of a Cylinder

Write a program to calculate the volume of a cylinder. Take the radius and
height as inputs and use the formula:
Volume = π * radius^2 * height.

*/



import java.util.Scanner;

public class VolumeOfCylinder {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the radius: ");
        
        float radius = sc.nextFloat();

        System.out.print("Enter the height: ");
        
        float height = sc.nextFloat();


        float volume = (float)Math.PI * radius * radius * height;

        System.out.print("Volume = " + volume);

        sc.close();

    }
}
