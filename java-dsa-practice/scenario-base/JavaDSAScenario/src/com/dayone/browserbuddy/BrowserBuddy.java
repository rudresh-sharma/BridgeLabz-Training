package com.dayone.browserbuddy;

import java.util.Scanner;

public class BrowserBuddy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Browser browser = new Browser();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== BrowserBuddy Menu =====");
            System.out.println("1. Open New Tab");
            System.out.println("2. Close Current Tab");
            System.out.println("3. Reopen Last Closed Tab");
            System.out.println("4. Visit URL");
            System.out.println("5. Back");
            System.out.println("6. Forward");
            System.out.println("7. Show Current Page");
            System.out.println("8. Show Tab History");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter tab name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter homepage URL: ");
                    String homepage = sc.nextLine();
                    browser.openTab(name, homepage);
                    break;
                case 2:
                    browser.closeCurrentTab();
                    break;
                case 3:
                    browser.reopenLastClosedTab();
                    break;
                case 4:
                    if (browser.getCurrentTab() != null) {
                        System.out.print("Enter URL to visit: ");
                        String url = sc.nextLine();
                        browser.getCurrentTab().visit(url);
                    } else {
                        System.out.println("No open tab!");
                    }
                    break;
                case 5:
                    if (browser.getCurrentTab() != null) browser.getCurrentTab().back();
                    else System.out.println("No open tab!");
                    break;
                case 6:
                    if (browser.getCurrentTab() != null) browser.getCurrentTab().forward();
                    else System.out.println("No open tab!");
                    break;
                case 7:
                    if (browser.getCurrentTab() != null) browser.getCurrentTab().showCurrent();
                    else System.out.println("No open tab!");
                    break;
                case 8:
                    if (browser.getCurrentTab() != null) browser.getCurrentTab().showHistory();
                    else System.out.println("No open tab!");
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting BrowserBuddy. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

        sc.close();
    }
}
