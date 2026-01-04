package com.dayone.ewallet;

/**
 * Business wallet with higher limits and optional surcharge
 */
public class BusinessWallet extends Wallet {

    public BusinessWallet(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void transferTo(User receiver, double amount) {
        double surcharge = amount * 0.02; // 2% surcharge for business transfers
        double total = amount + surcharge;
        if(total <= balance) {
            balance -= total;
            receiver.getWallet().balance += amount;
            Transaction tx = new Transaction("BusinessWallet", receiver.getName(), amount);
            this.addTransaction(tx);
            receiver.getWallet().addTransaction(tx);
            System.out.println("Transferred $" + amount + " to " + receiver.getName() + " (Surcharge: $" + surcharge + ")");
        } else {
            System.out.println("Insufficient balance including surcharge!");
        }
    }
}
