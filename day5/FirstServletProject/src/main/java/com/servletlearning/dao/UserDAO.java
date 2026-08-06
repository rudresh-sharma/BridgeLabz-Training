package com.servletlearning.dao;

import com.servletlearning.model.User;
import com.servletlearning.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final String INSERT_USER =
            "INSERT INTO users(name,email,password) VALUES(?,?,?)";

    public boolean register(User user) {

        try (
                Connection conn = DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(INSERT_USER)
        ) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
    
    
    public User login(String email, String password) {
    	
    	
    	String LOGIN_USER = "SELECT id, name, email, password FROM users WHERE email = ? AND password = ?";
    	
    	
    	
        try (
                Connection conn = DBConnection.getConnection();

                PreparedStatement ps =
                        conn.prepareStatement(LOGIN_USER)
        ) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}