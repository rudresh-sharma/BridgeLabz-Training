package com.datastructure.graphs.network;

import java.util.*;

public class NetworkGraph {

    private Map<String, List<String>> adjList;
    private Map<String, Integer> indexMap;
    private int[][] adjMatrix;
    private int size;

    public NetworkGraph(int size) {
        this.size = size;
        adjList = new HashMap<>();
        indexMap = new HashMap<>();
        adjMatrix = new int[size][size];
    }

    // Add router
    public void addRouter(String router, int index) {
        adjList.put(router, new ArrayList<>());
        indexMap.put(router, index);
    }

    // Add undirected connection
    public void addConnection(String r1, String r2) {
        adjList.get(r1).add(r2);
        adjList.get(r2).add(r1);

        int i = indexMap.get(r1);
        int j = indexMap.get(r2);
        adjMatrix[i][j] = 1;
        adjMatrix[j][i] = 1;
    }

    // ---------------- Connectivity Check ----------------
    public boolean isConnected() {
        Set<String> visited = new HashSet<>();
        dfs(adjList.keySet().iterator().next(), visited);
        return visited.size() == adjList.size();
    }

    private void dfs(String node, Set<String> visited) {
        visited.add(node);
        for (String nei : adjList.get(node)) {
            if (!visited.contains(nei)) {
                dfs(nei, visited);
            }
        }
    }

    // ---------------- BFS: Minimum Hops ----------------
    public int minHops(String start, String end) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.add(start);
        visited.add(start);
        int hops = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (curr.equals(end)) return hops;

                for (String nei : adjList.get(curr)) {
                    if (!visited.contains(nei)) {
                        visited.add(nei);
                        q.add(nei);
                    }
                }
            }
            hops++;
        }
        return -1;
    }

    // ---------------- Alternative Path (Avoid Edge) ----------------
    public List<String> alternativePath(String start, String end,
                                        String blockedA, String blockedB) {

        Queue<String> q = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        q.add(start);
        visited.add(start);
        parent.put(start, null);

        while (!q.isEmpty()) {
            String curr = q.poll();
            if (curr.equals(end)) break;

            for (String nei : adjList.get(curr)) {

                // Skip failed connection
                if ((curr.equals(blockedA) && nei.equals(blockedB)) ||
                    (curr.equals(blockedB) && nei.equals(blockedA))) {
                    continue;
                }

                if (!visited.contains(nei)) {
                    visited.add(nei);
                    parent.put(nei, curr);
                    q.add(nei);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String curr = end;
        while (curr != null) {
            path.add(curr);
            curr = parent.get(curr);
        }
        Collections.reverse(path);
        return path;
    }

    // ---------------- Display Matrix ----------------
    public void printAdjMatrix() {
        System.out.println("\nAdjacency Matrix:");
        for (int[] row : adjMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    // ---------------- Display List ----------------
    public void printAdjList() {
        System.out.println("\nAdjacency List:");
        for (String key : adjList.keySet()) {
            System.out.println(key + " -> " + adjList.get(key));
        }
    }
}
