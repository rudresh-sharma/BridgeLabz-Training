package com.dayten.avltree.gamingapp;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private AVLNode root;

    // Public insert method
    public void insert(Player player) {
        root = insert(root, player);
    }

    // Public remove method
    public void remove(Player player) {
        root = remove(root, player);
    }

    // Get top N players
    public List<Player> getTopPlayers(int n) {
        List<Player> result = new ArrayList<>();
        inOrder(root, result, n);
        return result;
    }

    // ---------- Private AVL helpers ----------

    private AVLNode insert(AVLNode node, Player player) {
        if (node == null) return new AVLNode(player);

        if (player.compareTo(node.player) < 0) {
            node.left = insert(node.left, player);
        } else if (player.compareTo(node.player) > 0) {
            node.right = insert(node.right, player);
        } else {
            // If player exists, update points
            node.player.setPoints(player.getPoints());
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private AVLNode remove(AVLNode node, Player player) {
        if (node == null) return null;

        if (player.compareTo(node.player) < 0) {
            node.left = remove(node.left, player);
        } else if (player.compareTo(node.player) > 0) {
            node.right = remove(node.right, player);
        } else {
            // Node to delete
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                AVLNode minNode = getMin(node.right);
                node.player = minNode.player;
                node.right = remove(node.right, minNode.player);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balance(node);
    }

    // In-order traversal to collect top N players
    private void inOrder(AVLNode node, List<Player> result, int n) {
        if (node == null || result.size() >= n) return;
        inOrder(node.left, result, n);
        if (result.size() < n) result.add(node.player);
        inOrder(node.right, result, n);
    }

    // ---------- AVL rotations ----------

    private int height(AVLNode node) { return node == null ? 0 : node.height; }
    private void updateHeight(AVLNode node) { node.height = 1 + Math.max(height(node.left), height(node.right)); }
    private int getBalance(AVLNode node) { return node == null ? 0 : height(node.left) - height(node.right); }

    private AVLNode balance(AVLNode node) {
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) return rotateRight(node);  // Left Left
        if (balance > 1 && getBalance(node.left) < 0) {                         // Left Right
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0) return rotateLeft(node); // Right Right
        if (balance < -1 && getBalance(node.right) > 0) {                        // Right Left
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
