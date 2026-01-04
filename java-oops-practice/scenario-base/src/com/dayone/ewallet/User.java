package com.dayone.ewallet;

/**
 * User class represents a wallet owner
 */
public class User {
    private String userId;
    private String name;
    private Wallet wallet;

    public User(String userId, String name, Wallet wallet) {
        this.userId = userId;
        this.name = name;
        this.wallet = wallet;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public Wallet getWallet() { return wallet; }

    public void showBalance() {
        System.out.println(name + " balance: $" + wallet.getBalance());
    }
}
