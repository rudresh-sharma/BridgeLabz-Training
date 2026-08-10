package com.springmvc.controller;

import com.springmvc.model.User;
import com.springmvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

   
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =====================================================================
    // ROOT — redirect to login
    // =====================================================================

   
    @GetMapping("/")
    public String root() {
        return "redirect:/user/login";
    }

    // =====================================================================
    // LOGIN — GET (Show the Login Form)
    // =====================================================================

    @GetMapping("/login")
    public String showLoginPage() {
        log.debug("Showing login page");
        return "login";
    }

    // =====================================================================
    // LOGIN — POST (Process the Login Form)
    // =====================================================================

   
    @PostMapping("/login")
    public String processLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        log.info("Login attempt for email: {}", email);

        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email and password are required.");
            return "redirect:/user/login";
        }

        Optional<User> userOpt = userService.authenticate(email, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            session.setAttribute("loggedInUser", user);
            log.info("Login successful for: {}", email);
            return "redirect:/greeting";
        }

        // ---------------------------------------------------------------
        // Distinguish "no such account" from "wrong password"
        // ---------------------------------------------------------------
        if (!userService.userExists(email)) {
            log.warn("Login failed — no account found for: {}", email);
            redirectAttributes.addFlashAttribute("errorMessage",
                "No account found with this email. Please register first.");
        } else {
            log.warn("Login failed — wrong password for: {}", email);
            redirectAttributes.addFlashAttribute("errorMessage",
                "Incorrect password. Please try again.");
        }

        return "redirect:/user/login";
    }

    // =====================================================================
    // SIGNUP — GET (Show the Signup Form)
    // =====================================================================

  
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        log.debug("Showing signup page");
        model.addAttribute("user", new User());
        return "signup";
    }

    // =====================================================================
    // SIGNUP — POST (Process the Signup Form)
    // =====================================================================

   
    @PostMapping("/signup")
    public String processSignup(
            @ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes) {

        log.info("Signup attempt for email: {}", user.getEmail());

        // Basic server-side validation
        if (user.getName() == null || user.getName().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Name is required.");
            return "redirect:/user/signup";
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email is required.");
            return "redirect:/user/signup";
        }
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password must be at least 6 characters.");
            return "redirect:/user/signup";
        }

        boolean registered = userService.registerUser(user);

        if (registered) {
            log.info("Signup successful for: {}", user.getEmail());
            redirectAttributes.addFlashAttribute("successMessage",
                "Account created successfully! Please login.");
            return "redirect:/user/login";
        } else {
            log.warn("Signup failed — duplicate email: {}", user.getEmail());
            redirectAttributes.addFlashAttribute("errorMessage",
                "An account with this email already exists. Please login instead.");
            return "redirect:/user/signup";
        }
    }

    // =====================================================================
    // LOGOUT
    // =====================================================================

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("User logging out");
        session.invalidate(); // Destroys the session and all its data
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully.");
        return "redirect:/user/login";
    }
    
    
 // =====================================================================
    // EDIT PROFILE — GET (Show pre-filled edit form)
    // =====================================================================

    @GetMapping("/edit")
    public String showEditPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", loggedInUser);
        return "thymeleaf/edit";   // <-- was "edit"
    }

    // =====================================================================
    // EDIT PROFILE — POST (Process the update)
    // =====================================================================

    @PutMapping("/edit")
    public String processEdit(
            @ModelAttribute("user") User formUser,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/user/login";
        }

        // Preserve the id from the session — never trust a hidden form field alone
        formUser.setId(loggedInUser.getId());

        if (formUser.getName() == null || formUser.getName().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Name is required.");
            return "redirect:/user/edit";
        }
        if (formUser.getPassword() == null || formUser.getPassword().length() < 8) {
            redirectAttributes.addFlashAttribute("errorMessage", "Password must be at least 8 characters.");
            return "redirect:/user/edit";
        }

        boolean updated = userService.updateUser(formUser);

        if (updated) {
            // Refresh the session copy so the greeting page shows new data
            session.setAttribute("loggedInUser", formUser);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully.");
            return "redirect:/greeting";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage",
                "Update failed. That email may already be in use.");
            return "redirect:/user/edit";
        }
    }

    // =====================================================================
    // DELETE ACCOUNT — GET (Show confirmation page)
    // =====================================================================

    @GetMapping("/delete")
    public String showDeleteConfirmPage(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/user/login";
        }
        return "thymeleaf/delete-confirm";   // <-- was "delete-confirm"
    }

    // =====================================================================
    // DELETE ACCOUNT — POST (Process the deletion)
    // =====================================================================
    @DeleteMapping("/delete")
    public String processDelete(HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/user/login";
        }

        boolean deleted = userService.deleteUser(loggedInUser.getId());
        session.invalidate();

        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Your account was deleted.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong deleting your account.");
        }
        return "redirect:/user/login";
    
    }
}
