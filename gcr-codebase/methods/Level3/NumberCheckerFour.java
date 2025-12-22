/*
Extend or Create a NumberChecker utility class and perform following task. Call from main() method the different methods and display results. Make sure all are static methods
Hint => 
Method to Check if a number is prime number. A prime number is a number greater than 1 that has no positive divisors other than 1 and itself. 
x
Method to Check if a number is a spy number. A number is called a spy number if the sum of its digits is equal to the product of its digits
Method to Check if a number is an automorphic number. An automorphic number is a number whose square ends with the number itself. E.g. 5 is an automorphic number
Method to Check if a number is a buzz number. A buzz number is a number that is either divisible by 7 or ends with 7


*/




import java.util.Scanner;
public class NumberCheckerFour {
	public static void main(String[] args){

	// Taking inputs
	Scanner in = new Scanner(System.in);
	System.out.print("Enter the number:");
	long num = in.nextLong();

	// Printing is given number prime, spy, neon, automorphic, buzz
	boolean isPrime = isPrime(num);
	boolean isNeon = isNeon(num);
	boolean isSpy = isSpy(num);
	boolean isAutomorphic = isAutomorphic(num);
	boolean isBuzz = isBuzz(num);
	System.out.println("Given number " + num + " is " +" Prime = " + isPrime);
	System.out.println("Given number " + num + " is " +" Neon = " + isNeon);
	System.out.println("Given number " + num + " is " +" Spy = " + isSpy);
	System.out.println("Given number " + num + " is " +" Automorphic = " + isAutomorphic);
	System.out.println("Given number " + num + " is " +" Buzz = " + isBuzz);

	in.close();


	}



	// Method for checking num is prime or not
public static boolean isPrime(long num){
    if(num <= 1)
        return false;

    for(long i = 2; i <= Math.sqrt(num); i++){
        if(num % i == 0)
            return false;
    }
    return true;
}



	// Method for checking num is neon or not
	public static boolean isNeon(long num){
		long squareNum = (long) Math.pow(num,2);

		long sum = 0;
			while(squareNum>0){
				long digit = squareNum%10;
				sum += digit;
				squareNum /= 10;
			}

		if(sum == num)
			return true;
		else
			return false;

	}



	// Method for checking num is spy or not
	public static boolean isSpy(long num){
 
		long prod = 1;
		long sum = 0;
			while(num>0){
				long digit = num%10;
				sum += digit;
				prod *= digit;
				num /= 10;
			}

		if(sum == prod)
			return true;
		else
			return false;

	}


	// Method for checking num is automorphic or not
	public static boolean isAutomorphic(long num){
    long square = num * num;
    long temp = num;

      while(temp > 0){
         if(square % 10 != temp % 10)
             return false;
         square /= 10;
         temp /= 10;
    }
    return true;
}


	// Method for checking num is buzz or not
	public static boolean isBuzz(long num){
 
		long lastDigit = 0;

		lastDigit = num%10;

		if(lastDigit == 7 || num%7 == 0 )
			return true;
		else
			return false;

	}

}