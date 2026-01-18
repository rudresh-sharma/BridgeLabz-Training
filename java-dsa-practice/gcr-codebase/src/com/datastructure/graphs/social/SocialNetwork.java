package com.datastructure.graphs.social;

import java.util.List;

public class SocialNetwork {

    private Graph graph;

    public SocialNetwork() {
        graph = new Graph();
    }

    public void setupNetwork() {
        // Users
        graph.addUser("Alice");
        graph.addUser("Bob");
        graph.addUser("Charlie");
        graph.addUser("David");
        graph.addUser("Eve");

        // Friendships
        graph.addFriendship("Alice", "Bob");
        graph.addFriendship("Alice", "Charlie");
        graph.addFriendship("Bob", "David");
        graph.addFriendship("Charlie", "Eve");
        graph.addFriendship("David", "Eve");
    }

    public void printFriends(String user) {
        List<String> friends = graph.getFriends(user);
        System.out.println("Friends of " + user + ": " + friends);
    }

    public void checkDirectConnection(String u1, String u2) {
        System.out.println(u1 + " and " + u2 + " directly connected: "
                + graph.isDirectlyConnected(u1, u2));
    }

    public void findDegreeOfSeparation(String u1, String u2) {
        int degree = graph.shortestPath(u1, u2);
        System.out.println("Degree of separation between "
                + u1 + " and " + u2 + ": " + degree);
    }
}
