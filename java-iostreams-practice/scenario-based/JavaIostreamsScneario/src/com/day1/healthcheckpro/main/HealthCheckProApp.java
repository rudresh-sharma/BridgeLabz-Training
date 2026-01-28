package com.day1.healthcheckpro.main;


import com.day1.healthcheckpro.*;

import com.day1.healthcheckpro.controllers.LabTestController;
import com.day1.healthcheckpro.scanner.ApiScanner;

public class HealthCheckProApp {

    public static void main(String[] args) {

        System.out.println("🏥 HealthCheckPro – API Metadata Validator");
        System.out.println("=========================================");

        ApiScanner.scanController(LabTestController.class);
    }
}
