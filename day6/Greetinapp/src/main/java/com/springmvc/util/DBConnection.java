package com.springmvc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DBConnection {

    private static final Logger log = LoggerFactory.getLogger(DBConnection.class);

    private final DataSource dataSource;

    @Autowired
    public DBConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        try {
            Connection conn = dataSource.getConnection();
            log.debug("Database connection obtained: {}", conn);
            return conn;
        } catch (SQLException e) {
            log.error("Failed to obtain database connection", e);
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }
}
