/*

14. Movie Ticket Booking App 🎬
Ask users for movie type, seat type (gold/silver), and snacks.
● Use switch and if together.
● Loop through multiple customers.
● Clean structure and helpful variable names.
15. Rohan’s Library Reminder App 📚

*/




import java.util.Scanner;
public class  MovieTicketBooking{
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
			
		String choice = "yes";
		long totalMoneyEarned = 0;
		System.out.println("Welcome to Radhe Movie Booking System\n");

		while(choice.equalsIgnoreCase("yes")){

			long price = 0, seatCost = 0, snackCost = 0;
			long totalBill = 0;

			System.out.println(" Available Movies Genre ");
			System.out.println(" 1. Bollywood (price : 200)");
			System.out.println(" 2. Hollywood (price : 300)");
			System.out.println(" 3. South Indian (price: 250)\n\n");
			int movieTyp = 0;

			int inOrOut = 1;
			while(inOrOut == 1){
				System.out.print("Enter a number of choice from above: ");
				int movieType = in.nextInt();
			switch(movieType){
				case 1: {
					price = 200; 
					inOrOut = 0; movieTyp = 1;
					break;
				}
				case 2: {
					price = 300; 
					inOrOut = 0;movieTyp=2;
					break;
				}
				case 3: {
					price = 250; 
					inOrOut = 0;movieTyp =3;
					break;
				}
				default:{
					System.out.println("Enter valid choice number.");
					inOrOut = 1;
					break;
				}
			}
			}
			System.out.print("\n\nAvailable seats Types: ");
			System.out.print(" 1. Gold,	");
			System.out.println(" 2. Silver");
			System.out.print("Enter a number of choice from above: ");
			int seatType = in.nextInt();
			switch(seatType){
				case 1:
					seatCost = 100; break;
				case 2:
					seatCost = 50; break;
				default:
					System.out.println("Enter valid choice number.");
					break;
			}

			System.out.print("Do you want snacks(100) (yes or no): ");
			String str = in.next();
			if(str.equalsIgnoreCase("yes")){
				snackCost = 100;
			}
			
			totalBill = price + seatCost + snackCost;
			System.out.println("Movie Type : " + getMovieType(movieTyp));
			System.out.println("Seat Type : " + getSeatType(seatType));
			System.out.println("Snacks : " + str );
			System.out.println("Total Amount : " + totalBill);
			System.out.println("Thankyou for booking\n");
			
			totalMoneyEarned += (long)totalBill;
			System.out.println("\n\n Do you want to book for another customer? (yes/no)");
			choice = in.next();

		}

		System.out.println("Todays Earning = " + totalMoneyEarned);

	in.close();

	}


	// Method to getMovieType By choice
	public static String getMovieType(int movieType){
		if(movieType == 1){
			return "Bollywood (200)";
		}
		else if(movieType == 2){
			return "Holywood (300)";
		}else{
			return "South Indian (250)";
		}
	}

	// Method to getSeatType By choice

	public static String getSeatType(int seatType){
		if(seatType == 1){
			return "Gold (100)";
		}
		else {
			return "Silver(50)";
		}
	}
}















		
