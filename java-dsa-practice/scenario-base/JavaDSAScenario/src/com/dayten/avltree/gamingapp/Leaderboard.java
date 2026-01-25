package com.dayten.avltree.gamingapp;

import java.util.List;

public class Leaderboard {

    private AVLTree tree = new AVLTree();

    // Scenario 1: Insert/Update player
    public void addOrUpdatePlayer(String name, int points) {
        Player player = new Player(name, points);
        tree.insert(player);
    }

    // Scenario 2: Display top N players
    public void displayTopPlayers(int n) {
        List<Player> top = tree.getTopPlayers(n);
        System.out.println(" Top " + n + " Players:");
        for (int i = 0; i < top.size(); i++) {
            System.out.println((i+1) + ". " + top.get(i));
        }
    }

    // Scenario 3: Remove player
    public void removePlayer(String name, int points) {
        tree.remove(new Player(name, points));
    }
}
