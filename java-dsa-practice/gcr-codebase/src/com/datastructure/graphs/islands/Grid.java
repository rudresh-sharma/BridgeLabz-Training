package com.datastructure.graphs.islands;

public class Grid {

    int[][] grid;
    int rows, cols;

    public Grid(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }
}
