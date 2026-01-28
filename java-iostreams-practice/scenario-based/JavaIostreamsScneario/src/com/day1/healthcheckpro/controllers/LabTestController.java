package com.day1.healthcheckpro.controllers;

import com.day1.healthcheckpro.annotations.*;
import com.day1.healthcheckpro.*;

public class LabTestController {

    @PublicAPI
    @ApiInfo(
        endpoint = "/api/labs/tests",
        description = "Get list of available lab tests"
    )
    public void getAllLabTests() {
    }

    @RequiresAuth
    @ApiInfo(
        endpoint = "/api/labs/book",
        description = "Book a lab test for a patient"
    )
    public void bookLabTest() {
    }

    // ❌ Missing annotations (HealthCheckPro should flag this)
    public void deleteLabTest() {
    }
}
