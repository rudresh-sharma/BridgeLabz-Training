/*
8. Shopkeeper’s Discount Dashboard 🛍️
A shopkeeper gives discounts based on total bill:
● Input item prices in a for-loop.
● Use if-else for discount logic.
● Use proper indentation, constants, and comments.



*/




//created class named DiscountDashboard
import java.util.Scanner;
public class DiscountDashboard{

    //constants for discount
	private static final double DISCOUNT_20 = 0.20;
    private static final double DISCOUNT_15 = 0.15;
    private static final double DISCOUNT_10 = 0.10;
    
	
	public static void main(String[]args){
	    Scanner input=new Scanner(System.in);
		System.out.print("Enter the number of items : ");
		int N=input.nextInt();
		double total=0.0;
		double finalPrice=0.0;
		for(int i=0;i<N;i++){
		    System.out.print("Enter the price of item "+(i+1)+" : ");
		    double price=input.nextDouble();
			total+=price;
		}
		if(total>=3000){
			finalPrice=total-(total*DISCOUNT_20);
		}
		else if(total>=2000){
			finalPrice=total-(total*DISCOUNT_15);
	    }
		else if(total>=1000){
			finalPrice=total-(total*DISCOUNT_10);
	    }
	    else{
	        finalPrice=total;
	    }
		System.out.println("Total bill : "+total);
		System.out.println("Discount : "+(total-finalPrice));
		System.out.println("Total payable : "+finalPrice);
	}
} 