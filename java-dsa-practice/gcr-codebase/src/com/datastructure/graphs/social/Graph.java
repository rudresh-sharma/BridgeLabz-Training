package com.datastructure.graphs.social;

import java.util.*;

public class Graph {

    private Map<String, List<String>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    // Add a user (vertex)
    public void addUser(String user) {
        adjList.putIfAbsent(user, new ArrayList<>());
    }

    // Add friendship (undirected edge)
    public void addFriendship(String user1, String user2) {
        adjList.get(user1).add(user2);
        adjList.get(user2).add(user1);
    }

    // Get friends of a user
    public List<String> getFriends(String user) {
        return adjList.getOrDefault(user, new ArrayList<>());
    }

    // Check direct connection
    public boolean isDirectlyConnected(String user1, String user2) {
        return adjList.get(user1).contains(user2);
    }

    // BFS for shortest path (degree of separation)
    public int shortestPath(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        int degree = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            degree++;

            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                for (String neighbor : adjList.get(current)) {
                    if (neighbor.equals(end)) {
                        return degree;
                    }

                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return -1; // No connection
    }
}
