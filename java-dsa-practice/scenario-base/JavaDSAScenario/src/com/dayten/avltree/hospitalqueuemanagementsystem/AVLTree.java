package com.dayten.avltree.hospitalqueuemanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private AVLNode root;

    public void insert(Patient patient) {
        root = insert(root, patient);
    }

    public void remove(Patient patient) {
        root = remove(root, patient);
    }

    public List<Patient> getPatientsInOrder() {
        List<Patient> patients = new ArrayList<>();
        inOrder(root, patients);
        return patients;
    }

    // ---------- Private AVL helpers ----------

    private AVLNode insert(AVLNode node, Patient patient) {
        if (node == null) return new AVLNode(patient);

        if (patient.compareTo(node.patient) < 0) {
            node.left = insert(node.left, patient);
        } else if (patient.compareTo(node.patient) > 0) {
            node.right = insert(node.right, patient);
        } else {
            System.out.println("❌ Patient with same check-in time exists.");
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    private AVLNode remove(AVLNode node, Patient patient) {
        if (node == null) return null;

        if (patient.compareTo(node.patient) < 0) {
            node.left = remove(node.left, patient);
        } else if (patient.compareTo(node.patient) > 0) {
            node.right = remove(node.right, patient);
        } else {
            // Node to delete
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                AVLNode minNode = getMin(node.right);
                node.patient = minNode.patient;
                node.right = remove(node.right, minNode.patient);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balance(node);
    }

    private void inOrder(AVLNode node, List<Patient> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.patient);
        inOrder(node.right, list);
    }

    // ---------- AVL rotations ----------
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
