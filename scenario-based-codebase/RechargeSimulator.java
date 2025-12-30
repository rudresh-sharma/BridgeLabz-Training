/*
10. Phone Recharge Simulator 📱
Take the user's mobile operator and amount.
● Use a switch to display offers.
● Loop to allow repeated recharges.
● Show balance after each recharge.

*/


import java.util.Scanner;

public class RechargeSimulator {
    public static void main(String[] args) {


	// Taking inputs
        Scanner sc = new Scanner(System.in);
        double balance = 0;
        String choice;

        do {
            System.out.println("\n--- Mobile Recharge Menu ---");
            System.out.println("1. Jio");
            System.out.println("2. Airtel");
            System.out.println("3. Vi");
            System.out.print("Select your mobile operator: ");
            int operator = sc.nextInt();

            System.out.print("Enter recharge amount: ₹");
            double amount = sc.nextDouble();

            double bonus = 0;

            switch (operator) {
                case 1:
                    System.out.println("Jio Offer: 10% extra balance");
                    bonus = amount * 0.10;
                    break;

                case 2:
                    System.out.println("Airtel Offer: ₹20 cashback");
                    bonus = 20;
                    break;

                case 3:
                    System.out.println("Vi Offer: 5% extra balance");
                    bonus = amount * 0.05;
                    break;

                default:
                    System.out.println("Invalid operator selected. No offer applied.");
            }

            balance = balance + amount + bonus;

            System.out.println("Recharge successful!");
            System.out.println("Bonus received: ₹" + bonus);
            System.out.println("Current Balance: ₹" + balance);

            System.out.print("\nDo you want to recharge again? (yes/no): ");
            choice = sc.next();

        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("\nThank you for using Phone Recharge Simulator 📱");
        System.out.println("Final Balance: ₹" + balance);

        sc.close();
    }
}



	