package com.dbmsjdbc.jdbc.practicequestion.librarymanagement;

import java.sql.*;

public class BookService {

    public void addBook(String title, String author, String category, int quantity) {

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO books(title,author,category,quantity) VALUES(?,?,?,?)");

            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, category);
            ps.setInt(4, quantity);

            ps.executeUpdate();
            System.out.println("Book Added Successfully ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {

        try (Connection conn = DBConnection.getConnection()) {

            ResultSet rs = conn.createStatement()
                    .executeQuery("SELECT * FROM books");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("book_id") + " | " +
                        rs.getString("title") + " | " +
                        rs.getString("author") + " | Qty: " +
                        rs.getInt("quantity"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
