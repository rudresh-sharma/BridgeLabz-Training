/* 

Calculate Simple Interest

Write a program to calculate simple interest using the formula:
Simple Interest = (Principal * Rate * Time) / 100.
Take Principal, Rate, and Time as inputs from the user.

*/



import java.util.Scanner;

public class SimpleInterest {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter principal amount :");
        int principal = sc.nextInt();

        System.out.println();

        System.out.print("Enter rate :");
        int rate = sc.nextInt();

        System.out.println();

        System.out.print("Enter time :");
        int time = sc.nextInt();

        double simpleInterest = (principal*rate*time)/100;

        System.out.println("Principal interest =" + simpleInterest);

        sc.close();
    }    
}
