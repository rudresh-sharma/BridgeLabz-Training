package com.day2.chatlogparser;

import java.util.Scanner;

public class ChatLogDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChatLogManager manager = new ChatLogManager();
        MessageFilter<ChatMessage> filter = new IdleChatFilter();

        int choice = -1;

        System.out.println(" Welcome to ChatLogParser");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Process Chat Log File");
            System.out.println("2️ Display Messages Grouped by User");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter chat log file path: ");
                    String path = sc.nextLine();
                    manager.processChatFile(path, filter);
                    break;

                case 2:
                    manager.displayByUser();
                    break;

                case 0:
                    System.out.println("👋 Exiting ChatLogParser. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }

        sc.close();
    }
}
