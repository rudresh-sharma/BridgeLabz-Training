package com.datastructure.trees.bstoperation;
class AttendanceBST {

    static class Node {
        int roll;
        Node left, right;

        Node(int roll) {
            this.roll = roll;
        }
    }

    static Node insert(Node root, int roll) {
        if (root == null) return new Node(roll);
        if (roll < root.roll)
            root.left = insert(root.left, roll);
        else
            root.right = insert(root.right, roll);
        return root;
    }

    static Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.roll)
            root.left = delete(root.left, key);
        else if (key > root.roll)
            root.right = delete(root.right, key);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node succ = root.right;
            while (succ.left != null)
                succ = succ.left;

            root.roll = succ.roll;
            root.right = delete(root.right, succ.roll);
        }
        return root;
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.roll + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {

        int[] rolls = {15, 10, 20, 8, 12, 17, 25};
        Node root = null;

        for (int r : rolls)
            root = insert(root, r);

        root = delete(root, 10);
        root = insert(root, 14);
        root = insert(root, 9);

        inorder(root);
    }
}