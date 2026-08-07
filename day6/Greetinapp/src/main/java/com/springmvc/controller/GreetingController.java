package com.springmvc.controller;

import com.springmvc.model.User;
import com.springmvc.service.GreetingService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * GreetingController — handles the greeting page after successful login.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * Separation of concerns:
 *  - UserController  → login, signup, logout (JSP views)
 *  - GreetingController → greeting page (Thymeleaf view)
 *
 * Having separate controllers makes the code easier to read and maintain.
 * Each controller has a single, clear responsibility.
 *
 * =====================================================================
 * HOW THE DUAL VIEW SYSTEM WORKS HERE
 * =====================================================================
 * This controller returns "thymeleaf/greeting" as the view name.
 *
 *  1. DispatcherServlet receives GET /greeting
 *  2. It passes "thymeleaf/greeting" to all ViewResolvers in ORDER:
 *  3. ThymeleafViewResolver (order=1) checks: does the name match
 *     my viewNames pattern "thymeleaf/*"? YES → it resolves the view.
 *  4. SpringTemplateEngine reads:
 *     /WEB-INF/templates/thymeleaf/greeting.html
 *  5. Renders and sends HTML response.
 *
 * If a controller returns "login" instead:
 *  3. ThymeleafViewResolver checks: "thymeleaf/*"? NO → returns null.
 *  4. InternalResourceViewResolver (order=2) resolves it to:
 *     /WEB-INF/views/login.jsp
 *
 * =====================================================================
 * SESSION GUARD (security)
 * =====================================================================
 * The greeting page should only be visible to logged-in users.
 * We check if loggedInUser is in the session.
 * If not → redirect to login.
 *
 * (In production use Spring Security for this instead.)
 */
@Controller
@RequestMapping("/greeting")
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private final GreetingService greetingService;

    /**
     * Constructor injection.
     * Spring provides the GreetingService bean automatically.
     */
    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    /**
     * GET /greeting
     *
     * Shows the greeting page for the logged-in user.
     *
     * @param session the current HTTP session (Spring injects it)
     * @param model   Spring's Model — a key-value store that passes
     *                data from the controller to the Thymeleaf template.
     *                In Thymeleaf: ${greeting}, ${user}, ${emoji}, etc.
     *
     * WHY Model and not ModelAndView?
     * Model is simpler — we just add attributes and return the view name
     * as a String. Spring wraps it into a ModelAndView internally.
     *
     * @return view name "thymeleaf/greeting" → resolved by ThymeleafViewResolver
     *         or "redirect:/user/login" if not authenticated
     */
    @GetMapping
    public String showGreeting(HttpSession session, Model model) {

        // ---------------------------------------------------------------
        // SESSION GUARD: Is the user logged in?
        // ---------------------------------------------------------------
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            log.warn("Unauthenticated access to /greeting — redirecting to login");
            return "redirect:/user/login";
        }

        log.info("Showing greeting page for user: {}", loggedInUser.getEmail());

        // ---------------------------------------------------------------
        // BUILD THE MODEL — all data the Thymeleaf template needs
        // ---------------------------------------------------------------

        // The greeting string: "Good Morning", "Good Afternoon", etc.
        String greeting = greetingService.getGreeting();

        // The matching emoji for the time of day
        String emoji = greetingService.getEmoji();

        // CSS class for the gradient background (morning/afternoon/evening/night)
        String timeOfDay = greetingService.getTimeOfDay();

        // Add everything to the model
        model.addAttribute("user", loggedInUser);        // User object: user.name, user.email
        model.addAttribute("greeting", greeting);         // "Good Morning"
        model.addAttribute("emoji", emoji);               // "☀️"
        model.addAttribute("timeOfDay", timeOfDay);       // "morning"

        // ---------------------------------------------------------------
        // RETURN VIEW NAME
        // "thymeleaf/greeting" → ThymeleafViewResolver picks this up
        // and renders /WEB-INF/templates/thymeleaf/greeting.html
        // ---------------------------------------------------------------
        return "thymeleaf/greeting";
    }
}
