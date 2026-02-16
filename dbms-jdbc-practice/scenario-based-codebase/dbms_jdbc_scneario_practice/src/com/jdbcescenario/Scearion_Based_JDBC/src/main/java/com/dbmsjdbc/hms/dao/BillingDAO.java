package com.dbmsjdbc.hms.dao;

import com.dbmsjdbc.hms.config.DBConnection;
import com.dbmsjdbc.hms.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.*;
import java.util.Scanner;

public class BillingDAO {

    Scanner sc = new Scanner(System.in);

    public void generateBill() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Visit ID: ");
            int visitId = sc.nextInt();

            System.out.print("Amount: ");
            double amount = sc.nextDouble();

            String sql =
                    "INSERT INTO bills(visit_id,amount,payment_status) VALUES(?,?, 'UNPAID')";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, visitId);
            ps.setDouble(2, amount);
            ps.executeUpdate();

            System.out.println("Bill Generated ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recordPayment() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Bill ID: ");
            int billId = sc.nextInt();

            String sql =
                    "UPDATE bills SET payment_status='PAID' WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, billId);
            ps.executeUpdate();

            System.out.println("Payment Recorded ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

