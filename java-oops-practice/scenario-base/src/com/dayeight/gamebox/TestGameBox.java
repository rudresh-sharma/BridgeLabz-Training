package com.dayeight.gamebox;

import java.util.Scanner;

public class TestGameBox {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        User user = new User("Rudresh");

        Game[] store = {
            new ArcadeGame("Speed Racer", 200, 4.5),
            new StrategyGame("War Tactics", 350, 4.8)
        };

        int choice;
        do {
            System.out.println("\n===== GAMEBOX MENU =====");
            System.out.println("1. View Store");
            System.out.println("2. Play Demo");
            System.out.println("3. Buy Game");
            System.out.println("4. Show My Games");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (int i = 0; i < store.length; i++) {
                        System.out.println(i + ". " + store[i].title + " - ₹" + store[i].price);
                    }
                    break;

                case 2:
                    System.out.print("Enter game index: ");
                    int d = sc.nextInt();
                    store[d].playDemo();   // polymorphism
                    break;

                case 3:
                    System.out.print("Enter game index: ");
                    int b = sc.nextInt();
                    double finalPrice = store[b].applyDiscount(20);  // seasonal offer
                    System.out.println("Discounted price: ₹" + finalPrice);
                    store[b].download();
                    user.buyGame(store[b]);
                    break;

                case 4:
                    user.showLibrary();
                    break;
            }
        } while (choice != 0);

        sc.close();
    }
}
