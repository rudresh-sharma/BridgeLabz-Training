package com.dayten.avltree.gamingapp;

public class AVLNode {
    Player player;
    AVLNode left, right;
    int height;

    public AVLNode(Player player) {
        this.player = player;
        this.height = 1;
    }
}
