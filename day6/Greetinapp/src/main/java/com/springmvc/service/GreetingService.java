package com.springmvc.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * GreetingService — Business logic for calculating time-based greetings.
 *
 * =====================================================================
 * WHY THIS CLASS EXISTS
 * =====================================================================
 * The greeting logic (determining "Good Morning", "Good Afternoon", etc.)
 * is a business rule, not a controller responsibility.
 *
 * Keeping it in a separate service means:
 *  1. The controller (GreetingController) stays thin — it only
 *     coordinates between the service and the view.
 *  2. The logic is unit-testable without spinning up an HTTP context.
 *  3. Multiple controllers could reuse this service if needed.
 *
 * =====================================================================
 * WHY @Service?
 * =====================================================================
 * Marks this class as a Spring-managed bean containing business logic.
 * Spring registers it during @ComponentScan and injects it wherever needed.
 *
 * =====================================================================
 * WHY LocalTime (not Date/Calendar)?
 * =====================================================================
 * LocalTime is part of Java 8's java.time API:
 *  - Immutable and thread-safe (unlike java.util.Date).
 *  - Has no time zone complications (we want the SERVER's local time).
 *  - Clearly expresses intent: "just the time, not the date".
 *  - Provides clean comparison methods: isAfter(), isBefore().
 *
 * =====================================================================
 * GREETING RULES
 * =====================================================================
 *   05:00 AM – 11:59 AM  →  Good Morning   ☀
 *   12:00 PM – 04:59 PM  →  Good Afternoon  🌤
 *   05:00 PM – 08:59 PM  →  Good Evening    🌆
 *   09:00 PM – 04:59 AM  →  Good Night      🌙
 */
@Service
public class GreetingService {

    // =====================================================================
    // TIME BOUNDARY CONSTANTS
    // Named constants instead of magic numbers make the code self-documenting.
    // =====================================================================
    private static final LocalTime MORNING_START   = LocalTime.of(5,  0);
    private static final LocalTime AFTERNOON_START = LocalTime.of(12, 0);
    private static final LocalTime EVENING_START   = LocalTime.of(17, 0);
    private static final LocalTime NIGHT_START     = LocalTime.of(21, 0);

    /**
     * Returns the appropriate greeting string based on the current time.
     *
     * WHY return just the greeting string and not a full sentence?
     * The service returns data; the Thymeleaf template decides how to
     * display it (e.g., combining with the user's name and emoji).
     * This separation of concerns keeps the service reusable.
     *
     * @return one of: "Good Morning", "Good Afternoon",
     *                 "Good Evening", "Good Night"
     */
    public String getGreeting() {
        LocalTime now = LocalTime.now();

        if (!now.isBefore(MORNING_START) && now.isBefore(AFTERNOON_START)) {
            return "Good Morning";
        } else if (!now.isBefore(AFTERNOON_START) && now.isBefore(EVENING_START)) {
            return "Good Afternoon";
        } else if (!now.isBefore(EVENING_START) && now.isBefore(NIGHT_START)) {
            return "Good Evening";
        } else {
            // Covers 21:00 - 23:59 and 00:00 - 04:59
            return "Good Night";
        }
    }

    /**
     * Returns a Unicode emoji matching the time of day.
     *
     * WHY Unicode emoji instead of Java emoji methods?
     * Unicode literals (e.g., "\u2600") are universally supported in
     * Java 21 and work correctly in HTML without any library dependency.
     * The Thymeleaf template can also hardcode the emoji directly.
     *
     * We provide this as a service method so the controller can pass it
     * to the model — keeping the template logic-free.
     *
     * @return a Unicode emoji string for the current time of day
     */
    public String getEmoji() {
        LocalTime now = LocalTime.now();

        if (!now.isBefore(MORNING_START) && now.isBefore(AFTERNOON_START)) {
            return "☀️";      // Morning sun
        } else if (!now.isBefore(AFTERNOON_START) && now.isBefore(EVENING_START)) {
            return "🌤️";    // Afternoon partly cloudy
        } else if (!now.isBefore(EVENING_START) && now.isBefore(NIGHT_START)) {
            return "🌆";      // Evening city
        } else {
            return "🌙";      // Night moon
        }
    }

    /**
     * Returns the CSS gradient class name matching the time of day.
     * Used by the Thymeleaf template to apply a time-appropriate
     * background gradient.
     *
     * @return a CSS class name string
     */
    public String getTimeOfDay() {
        LocalTime now = LocalTime.now();

        if (!now.isBefore(MORNING_START) && now.isBefore(AFTERNOON_START)) {
            return "morning";
        } else if (!now.isBefore(AFTERNOON_START) && now.isBefore(EVENING_START)) {
            return "afternoon";
        } else if (!now.isBefore(EVENING_START) && now.isBefore(NIGHT_START)) {
            return "evening";
        } else {
            return "night";
        }
    }
}
