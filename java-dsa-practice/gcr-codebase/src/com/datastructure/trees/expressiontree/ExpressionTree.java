package com.datastructure.trees.expressiontree;
import java.util.*;

class ExpressionTree {

    static class Node {
        String val;
        Node left, right;

        Node(String val) {
            this.val = val;
        }
    }

    static void inorder(Node root) {
        if (root == null) return;
        if (root.left != null) System.out.print("(");
        inorder(root.left);
        System.out.print(root.val);
        inorder(root.right);
        if (root.right != null) System.out.print(")");
    }

    static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    static int evaluate(Node root) {
        Stack<Integer> stack = new Stack<>();
        evalPost(root, stack);
        return stack.pop();
    }

    static void evalPost(Node root, Stack<Integer> stack) {
        if (root.left == null && root.right == null) {
            stack.push(Integer.parseInt(root.val));
            return;
        }

        evalPost(root.left, stack);
        evalPost(root.right, stack);

        int b = stack.pop();
        int a = stack.pop();

        if (root.val.equals("+")) stack.push(a + b);
        else if (root.val.equals("-")) stack.push(a - b);
        else if (root.val.equals("*")) stack.push(a * b);
    }

    public static void main(String[] args) {

        Node root = new Node("*");
        root.left = new Node("+");
        root.right = new Node("-");

        root.left.left = new Node("3");
        root.left.right = new Node("5");

        root.right.left = new Node("8");
        root.right.right = new Node("2");

        System.out.print("Postorder: ");
        postorder(root);

        System.out.print("\nPreorder: ");
        preorder(root);

        System.out.print("\nInorder: ");
        inorder(root);

        System.out.println("\n\nEvaluated Result: " + evaluate(root));
    }
}