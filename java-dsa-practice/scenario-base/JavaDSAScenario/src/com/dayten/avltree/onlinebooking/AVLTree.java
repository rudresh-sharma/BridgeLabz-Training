package com.dayten.avltree.onlinebooking;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private AVLNode root;

    // Insert event
    public void insert(Event event) {
        root = insert(root, event);
    }

    // Remove event
    public void remove(Event event) {
        root = remove(root, event);
    }

    // Get events in order
    public List<Event> getEventsInOrder() {
        List<Event> events = new ArrayList<>();
        inOrder(root, events);
        return events;
    }

    // ---------- Private AVL helpers ----------

    private AVLNode insert(AVLNode node, Event event) {
        if (node == null) return new AVLNode(event);

        if (event.compareTo(node.event) < 0) {
            node.left = insert(node.left, event);
        } else if (event.compareTo(node.event) > 0) {
            node.right = insert(node.right, event);
        } else {
            // Duplicate times are not allowed, skip
            System.out.println("❌ Event with same start time exists.");
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private AVLNode remove(AVLNode node, Event event) {
        if (node == null) return null;

        if (event.compareTo(node.event) < 0) {
            node.left = remove(node.left, event);
        } else if (event.compareTo(node.event) > 0) {
            node.right = remove(node.right, event);
        } else {
            // Node to delete
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                AVLNode minNode = getMin(node.right);
                node.event = minNode.event;
                node.right = remove(node.right, minNode.event);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balance(node);
    }

    private void inOrder(AVLNode node, List<Event> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.event);
        inOrder(node.right, list);
    }

    // AVL Rotations
    private int height(AVLNode node) { return node == null ? 0 : node.height; }
    private void updateHeight(AVLNode node) { node.height = 1 + Math.max(height(node.left), height(node.right)); }
    private int getBalance(AVLNode node) { return node == null ? 0 : height(node.left) - height(node.right); }

    private AVLNode balance(AVLNode node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) return rotateRight(node);
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return rotateLeft(node);
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private AVLNode rotateLeft(AVLNode y) {
        AVLNode x = y.right;
        AVLNode T2 = x.left;
        x.left = y;
        y.right = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private AVLNode getMin(AVLNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
