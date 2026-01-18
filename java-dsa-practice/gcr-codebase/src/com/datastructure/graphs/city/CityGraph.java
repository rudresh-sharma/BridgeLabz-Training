package com.datastructure.graphs.city;

import java.util.*;

public class CityGraph {

    private Map<String, List<Edge>> adjList;

    public CityGraph() {
        adjList = new HashMap<>();
    }

    // Add intersection
    public void addIntersection(String v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    // Add road (directed)
    public void addRoad(String from, String to, int distance) {
        adjList.get(from).add(new Edge(to, distance));
    }

    // ---------------- DFS: Reachable intersections ----------------
    public Set<String> reachableFrom(String source) {
        Set<String> visited = new HashSet<>();
        dfs(source, visited);
        return visited;
    }

    private void dfs(String current, Set<String> visited) {
        visited.add(current);

        for (Edge edge : adjList.get(current)) {
            if (!visited.contains(edge.destination)) {
                dfs(edge.destination, visited);
            }
        }
    }

    // ---------------- BFS: Fewest turns path ----------------
    public List<String> bfsShortestPath(String start, String end) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        parent.put(start, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) break;

            for (Edge edge : adjList.get(current)) {
                if (!visited.contains(edge.destination)) {
                    visited.add(edge.destination);
                    parent.put(edge.destination, current);
                    queue.add(edge.destination);
                }
            }
        }

        // Build path
        List<String> path = new ArrayList<>();
        String curr = end;

        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }

        Collections.reverse(path);
        return path;
    }
}
