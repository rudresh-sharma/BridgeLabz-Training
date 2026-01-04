package com.dayone.ewallet;

/**
 * Personal wallet with normal limits
 */
public class PersonalWallet extends Wallet {

    public PersonalWallet(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void transferTo(User receiver, double amount) {
        if(amount <= balance) {
            balance -= amount;
            receiver.getWallet().balance += amount;
            Transaction tx = new Transaction("PersonalWallet", receiver.getName(), amount);
            this.addTransaction(tx);
            receiver.getWallet().addTransaction(tx);
            System.out.println("Transferred $" + amount + " to " + receiver.getName());
        } else {
            System.out.println("Insufficient balance!");
        }
    }
}
