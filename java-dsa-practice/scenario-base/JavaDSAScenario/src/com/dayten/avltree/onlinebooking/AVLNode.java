package com.dayten.avltree.onlinebooking;

public class AVLNode {
    Event event;
    AVLNode left, right;
    int height;

    public AVLNode(Event event) {
        this.event = event;
        this.height = 1;
    }
}
