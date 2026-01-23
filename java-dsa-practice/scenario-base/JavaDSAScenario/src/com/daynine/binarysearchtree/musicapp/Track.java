package com.daynine.binarysearchtree.musicapp;

public class Track {

    private String trackId;
    private String title;
    private Track left;
    private Track right;

    public Track(String trackId, String title) {
        this.trackId = trackId;
        this.title = title;
        this.left = null;
        this.right = null;
    }

    // Copy constructor
    public Track(Track p) {
        this.trackId = p.trackId;
        this.title = p.title;
        this.left = p.left;
        this.right = p.right;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Track getLeft() {
        return left;
    }

    public void setLeft(Track left) {
        this.left = left;
    }

    public Track getRight() {
        return right;
    }

    public void setRight(Track right) {
        this.right = right;
    }
}
