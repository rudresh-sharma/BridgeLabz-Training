package com.day2.medInventory;

public class LowStockException extends Exception {

    public LowStockException(String message) {
        super(message);
    }
}
