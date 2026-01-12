package com.daytwo.texteditor;

import java.util.Scanner;

public class TestEditor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        int choice;

        do {
            System.out.println("\n=============================");
            System.out.println("        TEXT EDITOR");
            System.out.println("=============================");
            System.out.println("1. Insert Text");
            System.out.println("2. Delete Text");
            System.out.println("3. Format Text (Uppercase)");
            System.out.println("4. Undo");
            System.out.println("5. Redo");
            System.out.println("6. Show Current Text");
            System.out.println("0. Exit");
            System.out.println("=============================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String txt = sc.nextLine();
                    editor.insert(txt);
                    break;

                case 2:
                    System.out.print("How many characters to delete: ");
                    int n = sc.nextInt();
                    editor.delete(n);
                    break;

                case 3:
                    editor.formatUpper();
                    break;

                case 4:
                    editor.undo();
                    break;

                case 5:
                    editor.redo();
                    break;

                case 6:
                    editor.showText();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
