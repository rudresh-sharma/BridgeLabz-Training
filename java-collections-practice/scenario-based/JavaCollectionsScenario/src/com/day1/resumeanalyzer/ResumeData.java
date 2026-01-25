package com.day1.resumeanalyzer;

import java.util.List;

public class ResumeData {
    private String name;
    private String email;
    private String phone;
    private List<String> skills;
    private int keywordMatchCount;

    public ResumeData(String name, String email, String phone, List<String> skills) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.keywordMatchCount = skills.size();
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<String> getSkills() { return skills; }
    public int getKeywordMatchCount() { return keywordMatchCount; }

    @Override
    public String toString() {
        return "Name: " + name +
               "\nEmail: " + email +
               "\nPhone: " + phone +
               "\nSkills: " + skills +
               "\nKeyword Matches: " + keywordMatchCount;
    }
}
