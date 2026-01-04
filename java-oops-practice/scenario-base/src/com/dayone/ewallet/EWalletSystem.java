package com.dayone.ewallet;

import java.util.*;

/**
 * Main class for E-Wallet System
 * Allows user registration, wallet operations, fund transfers, and history
 */
public class EWalletSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<User> users = new ArrayList<>();

        while(true) {
            System.out.println("\n=== E-Wallet Menu ===");
            System.out.println("1. Register User");
            System.out.println("2. Load Money");
            System.out.println("3. Transfer Funds");
            System.out.println("4. Show Transaction History");
            System.out.println("5. Show Balance");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Wallet Type (P=Personal, B=Business): ");
                    String type = sc.nextLine().toUpperCase();
                    Wallet wallet = type.equals("B") ? new BusinessWallet(0) : new PersonalWallet(0);
                    users.add(new User(id, name, wallet));
                    System.out.println("User registered successfully!");
                    break;

                case 2:
                    System.out.print("Select User (index starting from 1): ");
                    int idx = sc.nextInt(); sc.nextLine();
                    User u = users.get(idx-1);
                    System.out.print("Enter amount to load: ");
                    double amt = sc.nextDouble(); sc.nextLine();
                    u.getWallet().balance += amt; // only inside same package, okay for demo
                    System.out.println("Money loaded successfully!");
                    break;

                case 3:
                    System.out.print("Select Sender (index): ");
                    int sIdx = sc.nextInt(); sc.nextLine();
                    System.out.print("Select Receiver (index): ");
                    int rIdx = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter amount: ");
                    double a = sc.nextDouble(); sc.nextLine();
                    users.get(sIdx-1).getWallet().transferTo(users.get(rIdx-1), a);
                    break;

                case 4:
                    System.out.print("Select User (index): ");
                    int hIdx = sc.nextInt(); sc.nextLine();
                    users.get(hIdx-1).getWallet().showHistory();
                    break;

                case 5:
                    System.out.print("Select User (index): ");
                    int bIdx = sc.nextInt(); sc.nextLine();
                    users.get(bIdx-1).showBalance();
                    break;

                case 6:
                    System.out.println("Exiting system.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
