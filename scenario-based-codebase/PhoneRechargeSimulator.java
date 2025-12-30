/*
10. Phone Recharge Simulator 📱
Take the user's mobile operator and amount.
● Use a switch to display offers.
● Loop to allow repeated recharges.
● Show balance after each recharge.


*/
//created class named 
import java.util.Scanner;
public class PhoneRechargeSimulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double balance = 0.0;
        boolean repeat = true;
        while (repeat){
            System.out.print("Enter Mobile Operator (Jio / Airtel / VI): ");
            String operator = input.next();
            System.out.print("Enter Recharge Amount: ");
            double amount = input.nextDouble();
            double bonus = 0;
			
            // Switch for offers
            switch (operator) {
                case "Jio":
                    bonus = amount * 0.10;
                    System.out.println("Jio Offer: 10% bonus added!");
                    break;

                case "Airtel":
                    bonus = amount * 0.15;   
                    System.out.println("Airtel Offer: 15% bonus added!");
                    break;

                case "Vi":
                    bonus = amount * 0.05; 
                    System.out.println("VI Offer: 5% bonus added!");
                    break;

                default:
                    System.out.println("Invalid Operator! No bonus added.");
            }

            balance += amount + bonus;

            System.out.println("Recharge Successful!");
            System.out.println("Amount Added  : " + amount);
            System.out.println("Bonus Added   : " + bonus);
            System.out.println("Current Balance : " + balance);

            // ask to continue
            System.out.print("Do you want to recharge again? (yes/no): ");
            String choice = input.next();

            if (!choice.equalsIgnoreCase("yes")) {
                repeat = false;
            }
        }
    }
}