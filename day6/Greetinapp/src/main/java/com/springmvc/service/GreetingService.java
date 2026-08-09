package com.springmvc.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;

/**

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

 
    public String getGreeting() {
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));

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

   
    public String getEmoji() {
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));

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

   
    public String getTimeOfDay() {
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Kolkata"));

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
