package com.daynine.binarysearchtree.musicapp;

public class MusicBST {

    // Insert track into BST
    public static Track insert(Track root, Track p) {
        if (root == null) {
            return new Track(p);
        }

        int cmp = root.getTrackId().compareToIgnoreCase(p.getTrackId());

        if (cmp > 0) {
            root.setLeft(insert(root.getLeft(), p));
        } else if (cmp < 0) {
            root.setRight(insert(root.getRight(), p));
        }
        // if equal TrackId, do nothing (no duplicates)

        return root;
    }

    // Search track by TrackId
    public static Track search(Track root, String trackId) {
        if (root == null) return null;

        int cmp = trackId.compareToIgnoreCase(root.getTrackId());

        if (cmp == 0) {
            return root;
        } else if (cmp < 0) {
            return search(root.getLeft(), trackId);
        } else {
            return search(root.getRight(), trackId);
        }
    }

    // Display tracks in sorted order (Inorder traversal)
    public static void displaySorted(Track root) {
        if (root == null) return;

        displaySorted(root.getLeft());

        System.out.printf("%-15s %-20s%n",
                root.getTrackId(),
                root.getTitle()
        );

        displaySorted(root.getRight());
    }
}
