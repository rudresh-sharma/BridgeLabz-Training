package com.datastructure.trees.treeterminology;
import java.util.*;

class OrganizationTree {

    static class Node {
        String name;
        List<Node> children = new ArrayList<>();

        Node(String name) {
            this.name = name;
        }
    }

    static int height(Node root) {
        if (root.children.isEmpty()) return 0;
        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, height(child));
        }
        return max + 1;
    }

    static boolean findDepth(Node root, String target, int depth) {
        if (root.name.equals(target)) {
            System.out.println(depth);
            return true;
        }
        for (Node child : root.children) {
            if (findDepth(child, target, depth + 1)) return true;
        }
        return false;
    }

    static boolean findAncestors(Node root, String target) {
        if (root.name.equals(target)) return true;
        for (Node child : root.children) {
            if (findAncestors(child, target)) {
                System.out.println(root.name);
                return true;
            }
        }
        return false;
    }

    static void printLeafNodes(Node root) {
        if (root.children.isEmpty()) {
            System.out.println(root.name);
            return;
        }
        for (Node child : root.children) {
            printLeafNodes(child);
        }
    }

    static Node findNode(Node root, String name) {
        if (root.name.equals(name)) return root;
        for (Node child : root.children) {
            Node found = findNode(child, name);
            if (found != null) return found;
        }
        return null;
    }

    public static void main(String[] args) {

        Node CEO = new Node("CEO");
        Node CTO = new Node("CTO");
        Node CFO = new Node("CFO");
        Node devLead = new Node("Dev Lead");
        Node HR = new Node("HR");
        Node dev1 = new Node("Dev1");
        Node dev2 = new Node("Dev2");

        CEO.children.add(CTO);
        CEO.children.add(CFO);

        CTO.children.add(devLead);
        CTO.children.add(HR);

        devLead.children.add(dev1);
        devLead.children.add(dev2);

        System.out.println("Leaf Nodes:");
        printLeafNodes(CEO);

        System.out.println("\nHeight of Tree:");
        System.out.println(height(CEO));

        System.out.println("\nDepth of Dev Lead:");
        findDepth(CEO, "Dev Lead", 0);

        System.out.println("\nAncestors of Dev1:");
        findAncestors(CEO, "Dev1");

        System.out.println("\nDegree of CTO:");
        Node ctoNode = findNode(CEO, "CTO");
        System.out.println(ctoNode.children.size());
    }
}