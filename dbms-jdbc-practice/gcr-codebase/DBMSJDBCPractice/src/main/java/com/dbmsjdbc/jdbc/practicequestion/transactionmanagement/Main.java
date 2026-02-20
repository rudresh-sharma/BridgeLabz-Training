package com.dbmsjdbc.jdbc.practicequestion.transactionmanagement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Transfer Money");
            System.out.println("2. Check Balance");
            System.out.println("3. Transaction History");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter From Account ID: ");
                    int from = sc.nextInt();

                    System.out.print("Enter To Account ID: ");
                    int to = sc.nextInt();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    BankService.transferMoney(from, to, amount);
                    break;

                case 2:
                    System.out.print("Enter Account ID: ");
                    int accId = sc.nextInt();
                    BankService.checkBalance(accId);
                    break;

                case 3:
                    System.out.print("Enter Account ID: ");
                    int id = sc.nextInt();
                    BankService.transactionHistory(id);
                    break;

                case 4:
                    System.out.println("Thank You! 👋");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
}
