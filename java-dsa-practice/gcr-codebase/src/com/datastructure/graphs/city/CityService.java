package com.datastructure.graphs.city;

import java.util.*;

public class CityService {

    private CityGraph graph;
    private Scanner sc;

    public CityService() {
        graph = new CityGraph();
        sc = new Scanner(System.in);
    }

    public void buildCity() {

        System.out.print("Enter number of intersections: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter intersection names:");
        for (int i = 0; i < n; i++) {
            graph.addIntersection(sc.nextLine());
        }

        System.out.print("Enter number of roads: ");
        int r = sc.nextInt();

        System.out.println("Enter roads (FROM TO DISTANCE IS_TWO_WAY(true/false)):");
        for (int i = 0; i < r; i++) {
            String from = sc.next();
            String to = sc.next();
            int dist = sc.nextInt();
            boolean twoWay = sc.nextBoolean();

            graph.addRoad(from, to, dist);
            if (twoWay) {
                graph.addRoad(to, from, dist);
            }
        }
    }

    public void process() {

        // Task 2: Reachable intersections
        System.out.println("\nReachable from A:");
        System.out.println(graph.reachableFrom("A"));

        // Task 3: BFS shortest path
        System.out.println("\nFewest turns path from A to E:");
        System.out.println(graph.bfsShortestPath("A", "E"));
    }
}
