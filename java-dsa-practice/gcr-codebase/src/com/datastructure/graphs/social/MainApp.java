package com.datastructure.graphs.social;

public class MainApp {

    public static void main(String[] args) {

        SocialNetwork network = new SocialNetwork();
        network.setupNetwork();

        // Task 2: Friends of Alice
        network.printFriends("Alice");

        // Task 3: Direct connection check
        network.checkDirectConnection("Bob", "Eve");

        // Task 4: Shortest path
        network.findDegreeOfSeparation("Alice", "Eve");
    }
}
