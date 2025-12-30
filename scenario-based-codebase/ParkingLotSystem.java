/*
6. Parking Lot Gate System 🚗
Develop a smart parking system.
● Options: Park, Exit, Show Occupancy
● Use switch-case for the menu.
● while loop to continue until the parking lot is full or the user exits.


*/


//created class named ParkingLotSystem
import java.util.Scanner;
public class ParkingLotSystem{
    public static void main(String[]args){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter number of slots available : ");
        int slots=input.nextInt();
        int occupied=slots;
        input.nextLine();
        while(slots>0){
            System.out.print("Park, Exit, Show Occupancy, Quit : ");
            String choice=input.nextLine();
			
			//used switch cases for various Case
            switch(choice){
                case ("Park"):
                    slots--;
                    System.out.println("Parked , Empty slots available : "+slots);
                    break;
                case ("Exit"):
                    slots++;
                    System.out.println("Exited , Empty slots available : "+slots);
                    break;
                case ("Show Occupancy"):
                    System.out.println("Empty slots available : "+slots);
                    System.out.println("Slots Occupied : "+(occupied-slots));
                    break;
                case ("Quit"):
                    System.out.println("Quited Parking System");
                    return;
                default :
                   System.out.println("Invalid choice");
                   break;
            }
        }
    }
}


