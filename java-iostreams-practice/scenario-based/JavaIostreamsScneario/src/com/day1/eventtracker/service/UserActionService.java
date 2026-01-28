package com.day1.eventtracker.service;

import com.day1.eventtracker.annotation.AuditTrail;

public class UserActionService {

    @AuditTrail(action = "USER_LOGIN")
    public void login() {
        System.out.println("User logged in");
    }

    @AuditTrail(action = "FILE_UPLOAD")
    public void uploadFile() {
        System.out.println("File uploaded");
    }

    @AuditTrail(action = "FILE_DELETE")
    public void deleteFile() {
        System.out.println("File deleted");
    }

    public void viewProfile() {
        System.out.println("Profile viewed (no audit)");
    }
}
