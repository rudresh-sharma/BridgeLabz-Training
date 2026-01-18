package com.datastructure.graphs.islands;

import java.util.Scanner;

public class IslandApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows: ");
        int r = sc.nextInt();
        System.out.print("Enter columns: ");
        int c = sc.nextInt();

        int[][] grid = new int[r][c];
        System.out.println("Enter grid values (0 or 1):");

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        Grid g = new Grid(grid);
        IslandCounter counter = new IslandCounter();

        System.out.println("\nIslands (DFS): " + counter.countIslandsDFS(g));
        System.out.println("Islands (BFS): " + counter.countIslandsBFS(g));
    }
}
