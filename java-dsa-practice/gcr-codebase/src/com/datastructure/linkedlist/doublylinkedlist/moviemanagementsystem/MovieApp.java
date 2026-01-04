package com.datastructure.linkedlist.doublylinkedlist.moviemanagementsystem;

public class MovieApp {
    public static void main(String[] args) {

        MovieDoublyLinkedList list = new MovieDoublyLinkedList();

        list.addAtEnd(new Movie("Inception", "Nolan", 2010, 9.0));
        list.addAtEnd(new Movie("Avatar", "Cameron", 2009, 8.5));
        list.addAtBeginning(new Movie("Interstellar", "Nolan", 2014, 9.2));
        list.addAtPosition(new Movie("Titanic", "Cameron", 1997, 8.9), 2);

        System.out.println("Forward Display:");
        list.displayForward();

        System.out.println("\nReverse Display:");
        list.displayReverse();

        System.out.println("\nSearch Director Nolan:");
        list.searchByDirector("Nolan");

        System.out.println("\nUpdate Rating:");
        list.updateRating("Avatar", 9.1);

        System.out.println("\nAfter Update:");
        list.displayForward();

        System.out.println("\nRemove Titanic:");
        list.removeByTitle("Titanic");
        list.displayForward();
    }
}
