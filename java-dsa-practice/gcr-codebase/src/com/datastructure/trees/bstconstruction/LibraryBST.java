package com.datastructure.tree.bstconstruction;

import java.util.*;

class LibraryBST {

    static class Node {
        int isbn;
        Node left, right;

        Node(int isbn) {
            this.isbn = isbn;
        }
    }

    static Node insert(Node root, int isbn) {
        if (root == null) return new Node(isbn);
        if (isbn < root.isbn)
            root.left = insert(root.left, isbn);
        else
            root.right = insert(root.right, isbn);
        return root;
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.isbn + " ");
        inorder(root.right);
    }

    static void search(Node root, int key) {
        Node curr = root;
        while (curr != null) {
            System.out.print(curr.isbn + " ");
            if (key < curr.isbn)
                curr = curr.left;
            else if (key > curr.isbn)
                curr = curr.right;
            else {
                System.out.println("\nFound");
                return;
            }
        }
        System.out.println("\nNot Found");
    }

    static int height(Node root) {
        if (root == null) return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static void main(String[] args) {

        int[] isbnList = {50, 30, 70, 20, 40, 60, 80, 10, 25};
        Node root = null;

        for (int isbn : isbnList) {
            root = insert(root, isbn);
        }

        System.out.println("Inorder Traversal:");
        inorder(root);

        System.out.println("\n\nSearch path for ISBN 25:");
        search(root, 25);

        System.out.println("\nHeight of Balanced BST:");
        System.out.println(height(root));

        System.out.println("\nHeight of Skewed BST:");
        System.out.println(isbnList.length - 1);
    }
}