/*

20. Festival Lucky Draw 🎉
At Diwali mela, each visitor draws a number.
● If the number is divisible by 3 and 5, they win a gift.
● Use if, modulus, and loop for multiple visitors.
● continue if input is invalid.

*/

import  java.util.Scanner;
public class LuckyDraw{
	public static void main(String[] args){

	// Taking input
	Scanner in = new Scanner(System.in);
	int moreVisitor = 1;


	// User drawing a number and getting gift on basis of number
		while(moreVisitor == 1){

			System.out.print("Draw a number ");
			int num = in.nextInt();
			
			if(num<=0){
				System.out.println("Invalid number");
				continue;
			}
			else if((num%3 == 0) && (num%5 == 0)){
				System.out.println("Congrats you got a gift");
			}
			else{
				System.out.println("So..Sorry!!!");
			}

			System.out.println("Is there a more visitor, 1 for more else 0 for exit");
			moreVisitor = in.nextInt();
		}


	in.close();

	}

}