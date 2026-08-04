package com.mysqlwithjdbc;

import com.mysqlwithjdbc.database.DatabaseInitializer;
import com.mysqlwithjdbc.menu.DoctorMenu;

public class App {

    public static void main(String[] args) {

        // Create required database objects
        DatabaseInitializer.createDoctorTable();

        // Start application menu
        DoctorMenu doctorMenu = new DoctorMenu();

        doctorMenu.start();

    }
}