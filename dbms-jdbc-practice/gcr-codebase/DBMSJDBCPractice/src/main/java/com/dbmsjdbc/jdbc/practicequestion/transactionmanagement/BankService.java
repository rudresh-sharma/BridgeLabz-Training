package com.dbmsjdbc.jdbc.practicequestion.transactionmanagement;
import java.sql.*;

public class BankService {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_practice";
    private static final String USER = "root";
    private static final String PASSWORD = "Rudresh@2005";

    // Transfer Money with Transaction
    public static void transferMoney(int fromAcc, int toAcc, double amount) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false); // START TRANSACTION

            // Check balance first
            PreparedStatement checkStmt = conn.prepareStatement(
                    "SELECT balance FROM accounts WHERE account_id = ?");
            checkStmt.setInt(1, fromAcc);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Sender account not found!");
                return;
            }

            double balance = rs.getDouble("balance");

            if (balance < amount) {
                System.out.println("Insufficient Balance ❌");
                return;
            }

            // Debit
            PreparedStatement debit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE account_id = ?");
            debit.setDouble(1, amount);
            debit.setInt(2, fromAcc);
            debit.executeUpdate();

            // Credit
            PreparedStatement credit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE account_id = ?");
            credit.setDouble(1, amount);
            credit.setInt(2, toAcc);
            credit.executeUpdate();

            // Insert transaction record
            PreparedStatement txn = conn.prepareStatement(
                    "INSERT INTO transactions (from_account, to_account, amount) VALUES (?, ?, ?)");
            txn.setInt(1, fromAcc);
            txn.setInt(2, toAcc);
            txn.setDouble(3, amount);
            txn.executeUpdate();

            conn.commit(); // SUCCESS
            System.out.println("Transaction Successful ✅");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // ROLLBACK if error
                System.out.println("Transaction Failed ❌ Rolled Back");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Check Balance
    public static void checkBalance(int accountId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT balance FROM accounts WHERE account_id = ?");
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Current Balance: ₹ " + rs.getDouble("balance"));
            } else {
                System.out.println("Account not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Transaction History
    public static void transactionHistory(int accountId) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM transactions WHERE from_account = ? OR to_account = ?");
            ps.setInt(1, accountId);
            ps.setInt(2, accountId);

            ResultSet rs = ps.executeQuery();

            System.out.println("----- Transaction History -----");

            while (rs.next()) {
                System.out.println(
                        "TxnID: " + rs.getInt("txn_id") +
                        " | From: " + rs.getInt("from_account") +
                        " | To: " + rs.getInt("to_account") +
                        " | Amount: ₹" + rs.getDouble("amount") +
                        " | Date: " + rs.getTimestamp("txn_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
