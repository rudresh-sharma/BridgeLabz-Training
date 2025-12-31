
/*
18. Currency Exchange Kiosk 💱
Design a currency converter:
● Take INR amount and target currency.
● Use a switch to apply the correct rate.
● Ask if the user wants another conversion (do-while).

*/


import java.util.Scanner;

public class CurrencyExchangeKiosk {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Base currency is INR");
        System.out.println("Target currencies are available:");
        System.out.println("USD, EUR, AUD, CNY, RUB, JPY, BRL, GBP");

        int choice = 1;

        do {
            System.out.print("Enter amount in INR: ");
            float amount = in.nextFloat();

            System.out.print("Enter target currency in capital: ");
            String targetCurrency = in.next();

            float amntInTarget = 0;

            switch (targetCurrency) {
                case "USD":
                    amntInTarget = amount / 89.86f;
                    break;
                case "EUR":
                    amntInTarget = amount / 105.36f;
                    break;
                case "AUD":
                    amntInTarget = amount / 60.01f;
                    break;
                case "CNY":
                    amntInTarget = amount / 12.84f;
                    break;
                case "RUB":
                    amntInTarget = amount / 1.11f;
                    break;
                case "JPY":
                    amntInTarget = amount / 0.57f;
                    break;
                case "BRL":
                    amntInTarget = amount / 16.40f;
                    break;
                case "GBP":
                    amntInTarget = amount / 120.60f;
                    break;
                default:
                    System.out.println("Invalid currency!");
                    continue;
            }

            System.out.println("Amount in INR = " + amount);
            System.out.println("Amount in " + targetCurrency + " = " + amntInTarget);

            System.out.print("Enter 1 to continue or 0 to exit: ");
            choice = in.nextInt();

        } while (choice == 1);

        in.close();
    }
}
