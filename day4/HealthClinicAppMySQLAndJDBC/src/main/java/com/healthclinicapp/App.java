package com.healthclinicapp;

import com.healthclinicapp.database.DatabaseConnection;
import com.healthclinicapp.database.DatabaseInitializer;
import com.healthclinicapp.menu.MainMenu;
import com.healthclinicapp.util.ColorUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Application entry point.
 *
 * Startup sequence:
 *  1. Test the database connection
 *  2. Run DatabaseInitializer (creates DB / tables / indexes / views /
 *     functions / triggers / events / sample data — all idempotent)
 *  3. Launch MainMenu
 */
public class App {

    public static void main(String[] args) {

        // 1. Verify connection
        System.out.println(ColorUtil.CYAN + "  Connecting to MySQL..." + ColorUtil.RESET);
        try (Connection conn = DatabaseConnection.getBaseConnection()) {
            System.out.println(ColorUtil.BOLD_GREEN + "  ✔ Connected to MySQL successfully." + ColorUtil.RESET);
        } catch (SQLException e) {
            System.out.println(ColorUtil.BOLD_RED +
                "\n  ✘ Cannot connect to MySQL!" +
                "\n    " + e.getMessage() +
                "\n\n  Please verify:" +
                "\n    • MySQL server is running" +
                "\n    • Host / Port are correct in DatabaseConnection.java" +
                "\n    • Username and password are correct" +
                ColorUtil.RESET);
            System.exit(1);
        }

        // 2. Initialize database (idempotent — safe to run every time)
        System.out.println(ColorUtil.CYAN + "  Initializing database schema and sample data..." + ColorUtil.RESET);
        DatabaseInitializer.initialize();
        System.out.println(ColorUtil.BOLD_GREEN + "  ✔ Database ready." + ColorUtil.RESET);

        // 3. Launch Main Menu
        new MainMenu().show();
    }
}

