package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController — handles the application root URL.
 *
 * WHY THIS CLASS EXISTS
 * When a user navigates to http://localhost:8080/GreetingApp/
 * we want to redirect them to the login page instead of showing a 404.
 *
 * This tiny controller handles that root URL mapping.
 */
@Controller
public class HomeController {

    /**
     * GET /
     * Redirects the browser to the login page.
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/user/login";
    }
}
