package com.datastructure.graphs.network;

import java.util.*;

public class NetworkService {

    private Scanner sc = new Scanner(System.in);
    private NetworkGraph graph;

    public void buildNetwork() {

        System.out.print("Enter number of routers: ");
        int n = sc.nextInt();
        sc.nextLine();

        graph = new NetworkGraph(n);

        String[] routers = new String[n];
        System.out.println("Enter router names:");
        for (int i = 0; i < n; i++) {
            routers[i] = sc.nextLine();
            graph.addRouter(routers[i], i);
        }

        System.out.print("Enter number of connections: ");
        int e = sc.nextInt();

        System.out.println("Enter connections (R1 R2):");
        for (int i = 0; i < e; i++) {
            String a = sc.next();
            String b = sc.next();
            graph.addConnection(a, b);
        }
    }

    public void process() {

        graph.printAdjList();
        graph.printAdjMatrix();

        // Task 3
        System.out.println("\nNetwork Connected: " + graph.isConnected());

        // Task 4
        System.out.println("\nAlternative path from R1 to R6 if R4-R5 fails:");
        System.out.println(graph.alternativePath("R1", "R6", "R4", "R5"));

        // Task 5
        System.out.println("\nMinimum hops from R1 to R6:");
        System.out.println(graph.minHops("R1", "R6"));
    }
}
