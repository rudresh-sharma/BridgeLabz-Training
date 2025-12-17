/* 

Write a program to find the age of Harry if the birth year is 2000. Assume the Current Year is 2024
I/P => NONE
O/P => Harry's age in 2024 is ___ 

*/



// Program to find Harry's age
public class FindAgeOfHarry {

    // main method
    public static void main(String[] args) {

        // Harry's birth year
        int birthYear = 2000;

        // present year
        int currentYear = 2024;

        // calculating age
        int age = currentYear - birthYear;

        // printing the result
        System.out.println("Harry's age in 2024 is " + age);
    }
}
