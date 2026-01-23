package com.daynine.binarysearchtree.universitysdigitalrecordsystem;

public class TreeConstruction {

    // 🔹 Insert into BST
    public static Student insertInToBST(Student root, Student s) {
        if (root == null) {
            return new Student(s);
        }

        int cmp = root.getRollNo().compareToIgnoreCase(s.getRollNo());

        if (cmp > 0) {
            root.setLeft(insertInToBST(root.getLeft(), s));
        } 
        else if (cmp < 0) {
            root.setRight(insertInToBST(root.getRight(), s));
        }
        // if equal, do nothing (no duplicate roll numbers)

        return root;
    }

    // 🔹 Delete a node from BST
    public static Student deleteNode(Student root, String target) {
        if (root == null) return null;

        int cmp = target.compareToIgnoreCase(root.getRollNo());

        if (cmp < 0) {
            root.setLeft(deleteNode(root.getLeft(), target));
        } 
        else if (cmp > 0) {
            root.setRight(deleteNode(root.getRight(), target));
        } 
        else {
            // Node found

            // Case 1: No child
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            }

            // Case 2: One child
            if (root.getLeft() == null) return root.getRight();
            if (root.getRight() == null) return root.getLeft();

            // Case 3: Two children
            Student successor = minNode(root.getRight());
            root.setName(successor.getName());
            root.setRollNo(successor.getRollNo());

            root.setRight(deleteNode(root.getRight(), successor.getRollNo()));
        }

        return root;
    }

    //  Find minimum node (used in deletion)
    public static Student minNode(Student root) {
        Student current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current;
    }

    // 🔹 Display sorted list (In-order Traversal)
    public static void display(Student root) {
        if (root == null) return;

        display(root.getLeft());
        System.out.printf("%-20s %-20s%n", root.getName(), root.getRollNo());
        display(root.getRight());
    }
}
