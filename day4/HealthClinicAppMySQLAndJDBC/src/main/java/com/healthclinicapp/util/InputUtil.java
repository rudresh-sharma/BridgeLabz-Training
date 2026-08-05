package com.healthclinicapp.util;

import java.util.Scanner;

/**
 * Console input helpers — safe reads with validation loops.
 * A single Scanner is shared across the entire application.
 * All methods are static; no instantiation needed.
 */
public class InputUtil {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputUtil() {}

    // ── Basic String reads ────────────────────────────────────────────────────

    /** Read a non-blank string. Keeps prompting until a non-blank value is entered. */
    public static String readString(String prompt) {
        while (true) {
            System.out.print(ColorUtil.CYAN + prompt + ColorUtil.RESET);
            String line = SCANNER.nextLine().trim();
            // Allow empty for optional prompts — caller checks
            return line;
        }
    }

    /**
     * Read an optional string — returns empty string if the user just presses Enter.
     * Used for "keep existing value" update flows.
     */
    public static String readStringOptional(String prompt) {
        System.out.print(ColorUtil.CYAN + prompt + ColorUtil.RESET);
        return SCANNER.nextLine().trim();
    }

    // ── Numeric reads ─────────────────────────────────────────────────────────

    /** Read an integer in [min, max]. Loops on invalid input. */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(ColorUtil.CYAN + prompt + ColorUtil.RESET);
            String line = SCANNER.nextLine().trim();
            try {
                int val = Integer.parseInt(line);
                if (val >= min && val <= max) return val;
                System.out.println(ColorUtil.RED + "  Enter a number between " + min + " and " + max + ColorUtil.RESET);
            } catch (NumberFormatException e) {
                System.out.println(ColorUtil.RED + "  Invalid number. Try again." + ColorUtil.RESET);
            }
        }
    }

    /** Read a non-negative double. Loops on invalid input. */
    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(ColorUtil.CYAN + prompt + ColorUtil.RESET);
            String line = SCANNER.nextLine().trim();
            try {
                double val = Double.parseDouble(line);
                if (val >= 0) return val;
                System.out.println(ColorUtil.RED + "  Value must be >= 0." + ColorUtil.RESET);
            } catch (NumberFormatException e) {
                System.out.println(ColorUtil.RED + "  Invalid number. Try again." + ColorUtil.RESET);
            }
        }
    }

    // ── Date read ─────────────────────────────────────────────────────────────

    /**
     * Prompt for a date in yyyy-MM-dd format.
     * Returns blank string if the user pressed Enter (optional date).
     */
    public static String readDate(String prompt) {
        while (true) {
            System.out.print(ColorUtil.CYAN + prompt + ColorUtil.RESET);
            String line = SCANNER.nextLine().trim();
            if (line.isBlank()) return "";
            if (DateUtil.isValidDate(line)) return line;
            System.out.println(ColorUtil.RED + "  Invalid date format. Use yyyy-MM-dd (e.g. 2024-03-15)." + ColorUtil.RESET);
        }
    }

    // ── Option selector ───────────────────────────────────────────────────────

    /**
     * Present a numbered list of options and return the chosen one.
     *
     * Example:
     *   readOption("Gender", new String[]{"Male","Female","Other"})
     */
    public static String readOption(String prompt, String[] options) {
        System.out.println(ColorUtil.CYAN + "  " + prompt + ":" + ColorUtil.RESET);
        for (int i = 0; i < options.length; i++) {
            System.out.println(ColorUtil.DIM + "    " + (i + 1) + ". " + options[i] + ColorUtil.RESET);
        }
        int choice = readInt("  Choose (1-" + options.length + "): ", 1, options.length);
        return options[choice - 1];
    }

    // ── Confirmation ──────────────────────────────────────────────────────────

    /** Ask yes/no. Returns true only if user types Y or y. */
    public static boolean confirm(String prompt) {
        System.out.print(ColorUtil.BOLD_YELLOW + prompt + " [Y/n]: " + ColorUtil.RESET);
        String ans = SCANNER.nextLine().trim().toLowerCase();
        return ans.equals("y") || ans.equals("yes");
    }

    // ── Pause ─────────────────────────────────────────────────────────────────

    /** Press Enter to continue. */
    public static void pause() {
        System.out.print(ColorUtil.DIM + "\n  Press Enter to continue..." + ColorUtil.RESET);
        SCANNER.nextLine();
    }
}
