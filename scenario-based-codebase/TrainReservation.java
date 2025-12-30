/*

12. Train Reservation Queue 🚆
Simulate a basic ticket booking system.
● Show menu with switch
● Allow multiple bookings using while-loop.
● Stop booking once seats reach zero (break).

*/


import java.util.Scanner;
public class TrainReservation{

	static	Scanner sc = new Scanner(System.in);

	 public static String displayMenu() {
        System.out.println("\nChoose seat (SL / 3AC / 2AC / 1AC): ");
        String seat = sc.next().trim().toLowerCase();

        switch (seat) {
            case "sl":
                System.out.println("It will cost 250");
                break;

            case "3ac":
                System.out.println("It will cost 500");
                break;

            case "2ac":
                System.out.println("It will cost 1000");
                break;

            case "1ac":
                System.out.println("It will cost 1500");
                break;

            default:
                System.out.println("Invalid seat choice");
        }
        return seat;
    }
	

	public static void main(String[] args){

	int slSeats = 10;
	int thirdAcSeats = 5;
	int secondAcSeats = 2;
	int firstAcSeats = 1;
	
	int totalSeats = slSeats+thirdAcSeats+secondAcSeats+firstAcSeats;

	// taking inputs
	System.out.println("Welcome to Train Seat Reservation system" );
	System.out.println("Route: Bhopal -> Ujjain" );
	

	while(totalSeats>0){

	String seatSelected = displayMenu();

	switch(seatSelected){
	
	case "sl" :
		if(slSeats>0){
			System.out.print("Pay 250.");
			System.out.println("Enter money:");
			sc.nextInt();
			slSeats--;
			totalSeats--;
			System.out.print("SL seat booked successfully");
		}
		else{
			System.out.println("Sorry SL seats are full");
		}
		break;
	
	case "3ac":
		if(thirdAcSeats >0){
			System.out.print("Pay 500.");
			System.out.println("Enter money:");
			sc.nextInt();
			thirdAcSeats--;
			totalSeats--;
			System.out.print("3AC seat booked successfully");
		}
		else{
			System.out.println("Sorry 3AC seats are full");
		}
		break;
	
	case "2ac":
		if(secondAcSeats >0){
			System.out.print("Pay 500.");
			System.out.println("Enter money:");
			sc.nextInt();
			secondAcSeats--;
			totalSeats--;
			System.out.print("2AC seat booked successfully");
		}
		else{
			System.out.println("Sorry 2AC seats are full");
		}
		break;

	case "1ac" :

		if(secondAcSeats >0){
			System.out.print("Pay 500.");
			System.out.println("Enter money:");
			sc.nextInt();
			secondAcSeats--;
			totalSeats--;
			System.out.print("2AC seat booked successfully");
		}
		else{
			System.out.println("Sorry 2AC seats are full");
		}
		break;

	default :
		System.out.println("\nPlease select a valid seat type");
	}

	if(totalSeats == 0){
		System.out.println("\nAll seats are booked, Booking close");
		break;
	}
	
	}
	
	sc.close();

	}

}

 

	