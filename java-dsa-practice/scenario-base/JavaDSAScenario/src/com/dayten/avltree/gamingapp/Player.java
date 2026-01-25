package com.dayten.avltree.gamingapp;

public class Player implements Comparable<Player> {
    private String name;
    private int points;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() { return name; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    @Override
    public int compareTo(Player other) {
        // Descending order of points
        int cmp = Integer.compare(other.points, this.points);
        if (cmp == 0) {
            // Tie-breaker: alphabetical by name
            return this.name.compareTo(other.name);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return name + " (" + points + ")";
    }
}
