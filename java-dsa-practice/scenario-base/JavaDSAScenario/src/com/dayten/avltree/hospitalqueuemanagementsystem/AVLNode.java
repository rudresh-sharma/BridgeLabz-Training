package com.dayten.avltree.hospitalqueuemanagementsystem;

public class AVLNode {
    Patient patient;
    AVLNode left, right;
    int height;

    public AVLNode(Patient patient) {
        this.patient = patient;
        this.height = 1;
    }
}
