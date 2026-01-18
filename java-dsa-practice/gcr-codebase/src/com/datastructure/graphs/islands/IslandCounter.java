package com.datastructure.graphs.islands;

import java.util.*;

public class IslandCounter {

    // Directions: up, down, left, right
    private static final int[][] DIRS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    // ---------------- DFS ----------------
    public int countIslandsDFS(Grid g) {
        boolean[][] visited = new boolean[g.rows][g.cols];
        int count = 0;

        for (int i = 0; i < g.rows; i++) {
            for (int j = 0; j < g.cols; j++) {
                if (g.grid[i][j] == 1 && !visited[i][j]) {
                    dfs(g, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(Grid g, int r, int c, boolean[][] visited) {
        if (r < 0 || c < 0 || r >= g.rows || c >= g.cols)
            return;
        if (visited[r][c] || g.grid[r][c] == 0)
            return;

        visited[r][c] = true;

        for (int[] d : DIRS) {
            dfs(g, r + d[0], c + d[1], visited);
        }
    }

    // ---------------- BFS ----------------
    public int countIslandsBFS(Grid g) {
        boolean[][] visited = new boolean[g.rows][g.cols];
        int count = 0;

        for (int i = 0; i < g.rows; i++) {
            for (int j = 0; j < g.cols; j++) {
                if (g.grid[i][j] == 1 && !visited[i][j]) {
                    bfs(g, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(Grid g, int r, int c, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cell = q.poll();

            for (int[] d : DIRS) {
                int nr = cell[0] + d[0];
                int nc = cell[1] + d[1];

                if (nr >= 0 && nc >= 0 &&
                    nr < g.rows && nc < g.cols &&
                    g.grid[nr][nc] == 1 &&
                    !visited[nr][nc]) {

                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}
