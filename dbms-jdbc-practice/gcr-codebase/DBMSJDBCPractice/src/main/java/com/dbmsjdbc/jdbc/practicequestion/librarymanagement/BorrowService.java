package com.dbmsjdbc.jdbc.practicequestion.librarymanagement;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowService {

    public void borrowBook(int studentId, int bookId) {

        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement check = conn.prepareStatement(
                    "SELECT quantity FROM books WHERE book_id=?");
            check.setInt(1, bookId);

            ResultSet rs = check.executeQuery();

            if (!rs.next() || rs.getInt("quantity") <= 0) {
                System.out.println("Book Not Available ❌");
                return;
            }

            PreparedStatement update = conn.prepareStatement(
                    "UPDATE books SET quantity=quantity-1 WHERE book_id=?");
            update.setInt(1, bookId);
            update.executeUpdate();

            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO borrow_records(student_id,book_id,borrow_date) VALUES(?,?,?)");
            insert.setInt(1, studentId);
            insert.setInt(2, bookId);
            insert.setDate(3, Date.valueOf(LocalDate.now()));
            insert.executeUpdate();

            conn.commit();
            System.out.println("Book Borrowed Successfully ✅");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Transaction Failed ❌");
            e.printStackTrace();
        }
    }

    public void returnBook(int recordId) {

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT borrow_date, book_id FROM borrow_records WHERE record_id=?");
            ps.setInt(1, recordId);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid Record ID");
                return;
            }

            LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
            LocalDate today = LocalDate.now();
            long days = ChronoUnit.DAYS.between(borrowDate, today);

            double fine = 0;
            if (days > 7) fine = (days - 7) * 10;

            PreparedStatement update = conn.prepareStatement(
                    "UPDATE borrow_records SET return_date=?, fine=? WHERE record_id=?");
            update.setDate(1, Date.valueOf(today));
            update.setDouble(2, fine);
            update.setInt(3, recordId);
            update.executeUpdate();

            PreparedStatement qty = conn.prepareStatement(
                    "UPDATE books SET quantity=quantity+1 WHERE book_id=?");
            qty.setInt(1, rs.getInt("book_id"));
            qty.executeUpdate();

            System.out.println("Book Returned. Fine: ₹" + fine);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
