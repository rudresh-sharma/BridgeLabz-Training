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

/**
 * UserController — handles all user-related HTTP requests.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * The Controller is the entry point for HTTP requests in Spring MVC.
 * It receives requests from the browser, calls the service layer,
 * and returns a view name that Spring resolves to a JSP or Thymeleaf page.
 *
 * =====================================================================
 * HOW SPRING USES IT
 * =====================================================================
 * Spring's DispatcherServlet receives every HTTP request.
 * It consults the HandlerMapping to find which controller method
 * should handle the request (based on @RequestMapping annotations).
 * Then HandlerAdapter calls that method.
 *
 *  Browser  →  DispatcherServlet  →  HandlerMapping  →  UserController
 *                                                            ↓
 *  Browser  ←  ViewResolver  ←  View (JSP)  ←  Model Data
 *
 * =====================================================================
 * ANNOTATIONS EXPLAINED
 * =====================================================================
 *
 * @Controller
 *   Marks this class as a Spring MVC controller.
 *   Different from @RestController — methods return VIEW NAMES,
 *   not JSON/XML data.
 *
 * @RequestMapping("/user")
 *   Base URL prefix for all methods in this controller.
 *   /user/login, /user/signup, etc.
 *
 * @GetMapping / @PostMapping
 *   Shorthand for @RequestMapping(method = GET / POST).
 *   Describes WHICH HTTP method triggers the handler.
 *
 * @ModelAttribute
 *   Binds HTTP POST form parameters to a Java object automatically.
 *   Spring calls the no-arg constructor, then sets each field
 *   by matching form field names to setter names.
 *
 * @SessionAttribute
 *   Reads a value previously stored in HttpSession.
 *   Used to read the logged-in user.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Constructor injection — no @Autowired field injection.
     * Spring finds the UserService bean and passes it here.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // =====================================================================
    // ROOT — redirect to login
    // =====================================================================

    /**
     * GET /
     * Redirects the browser to the login page.
     * "redirect:" prefix tells Spring to send HTTP 302 instead of
     * rendering a view. The browser then makes a new GET to /user/login.
     */
    @GetMapping("/")
    public String root() {
        return "redirect:/user/login";
    }

    // =====================================================================
    // LOGIN — GET (Show the Login Form)
    // =====================================================================

    /**
     * GET /user/login
     * Shows the login JSP page.
     *
     * View name "login" → resolved by InternalResourceViewResolver
     *   to /WEB-INF/views/login.jsp
     */
    @GetMapping("/login")
    public String showLoginPage() {
        log.debug("Showing login page");
        return "login";
    }

    // =====================================================================
    // LOGIN — POST (Process the Login Form)
    // =====================================================================

    /**
     * POST /user/login
     *
     * @param email              from the form field name="email"
     * @param password           from the form field name="password"
     * @param session            Spring injects the current HttpSession
     * @param redirectAttributes used to pass flash messages across redirects
     *
     * WHY RedirectAttributes?
     * After POST we do a "redirect:" response (POST-REDIRECT-GET pattern).
     * Regular Model attributes are lost on redirect.
     * RedirectAttributes stores data in the session temporarily so it
     * survives the redirect and is available to the next GET request.
     * Flash attributes are automatically removed after one use.
     */
	    @PostMapping("/login")
	    public String processLogin(
	            @RequestParam("email") String email,
	            @RequestParam("password") String password,
	            HttpSession session,
	            RedirectAttributes redirectAttributes) {
	
	        log.info("Login attempt for email: {}", email);
	
	        // Basic validation
	        if (email == null || email.isBlank() || password == null || password.isBlank()) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Email and password are required.");
	            return "redirect:/user/login";
	        }
	
	        Optional<User> userOpt = userService.authenticate(email, password);
	
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            // Store user in session — available on all subsequent requests
	            session.setAttribute("loggedInUser", user);
	            log.info("Login successful for: {}", email);
	            // Redirect to the greeting page (GreetingController)
	            return "redirect:/greeting";
	        } else {
	            log.warn("Login failed for: {}", email);
	            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password. Please try again.");
	            return "redirect:/user/login";
	        }
	    }

    // =====================================================================
    // SIGNUP — GET (Show the Signup Form)
    // =====================================================================

    /**
     * GET /user/signup
     * Shows the signup JSP page.
     *
     * We pass an empty User object to the model so the JSP can use
     * it as a form-backing object (spring:bind or c:out expressions).
     */
    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        log.debug("Showing signup page");
        model.addAttribute("user", new User());
        return "signup";
    }

    // =====================================================================
    // SIGNUP — POST (Process the Signup Form)
    // =====================================================================

    /**
     * POST /user/signup
     *
     * @param user @ModelAttribute binds all form fields to the User object.
     *             Spring reads name="name", name="email", name="password"
     *             from the POST body and calls user.setName(), etc.
     *
     * @param redirectAttributes pass flash messages across the redirect
     *
     * Flow:
     *  1. userService.registerUser(user) checks for duplicate email.
     *  2. If successful → redirect to login with a success message.
     *  3. If duplicate email → redirect back to signup with an error.
     */
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

    /**
     * GET /user/logout
     *
     * Invalidates the session (removes loggedInUser and all other data).
     * Redirects to the login page with a goodbye message.
     *
     * WHY GET and not POST for logout?
     * A POST requires an HTML form. A GET is simpler for a logout link.
     * In high-security applications, POST + CSRF token is preferred.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        log.info("User logging out");
        session.invalidate(); // Destroys the session and all its data
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully.");
        return "redirect:/user/login";
    }
}
