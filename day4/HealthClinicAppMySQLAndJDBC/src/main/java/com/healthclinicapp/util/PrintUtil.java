package com.healthclinicapp.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Console print helpers — tables, headers, key-value pairs, messages.
 * All methods are static; no instantiation needed.
 */
public class PrintUtil {

    private static final int MAX_COL_WIDTH = 30;

    private PrintUtil() {}

    // ── Section headers ───────────────────────────────────────────────────────

    public static void subHeader(String title) {
        System.out.println();
        System.out.println(ColorUtil.BOLD_WHITE +
            "  ═══════════════════════════════════════════════════════" + ColorUtil.RESET);
        System.out.println(ColorUtil.BOLD_WHITE +
            "    " + title.toUpperCase() + ColorUtil.RESET);
        System.out.println(ColorUtil.BOLD_WHITE +
            "  ═══════════════════════════════════════════════════════" + ColorUtil.RESET);
    }

    // ── Status messages ───────────────────────────────────────────────────────

    public static void success(String msg) {
        System.out.println(ColorUtil.BOLD_GREEN + "\n  ✔ " + msg + ColorUtil.RESET);
    }

    public static void error(String msg) {
        System.out.println(ColorUtil.BOLD_RED + "\n  ✘ " + msg + ColorUtil.RESET);
    }

    public static void info(String msg) {
        System.out.println(ColorUtil.BOLD_YELLOW + "\n  ℹ " + msg + ColorUtil.RESET);
    }

    public static void warn(String msg) {
        System.out.println(ColorUtil.YELLOW + "\n  ⚠ " + msg + ColorUtil.RESET);
    }

    // ── Key-value display ─────────────────────────────────────────────────────

    public static void kv(String key, String value) {
        System.out.printf(ColorUtil.CYAN + "  %-20s" + ColorUtil.RESET +
                          ColorUtil.WHITE + ": %s%n" + ColorUtil.RESET,
                          key, value == null ? "-" : value);
    }

    // ── Table display ─────────────────────────────────────────────────────────

    /**
     * Prints a formatted ASCII table.
     *
     * @param headers Column headers
     * @param rows    2-D array of cell values; each inner array must match headers length
     */
    public static void table(String[] headers, String[][] rows) {
        if (headers == null || rows == null) return;

        int cols = headers.length;
        int[] widths = new int[cols];

        // Compute column widths
        for (int c = 0; c < cols; c++) {
            widths[c] = Math.min(headers[c].length(), MAX_COL_WIDTH);
        }
        for (String[] row : rows) {
            for (int c = 0; c < cols && c < row.length; c++) {
                String cell = row[c] == null ? "" : row[c];
                // Strip ANSI codes for width measurement
                String plain = cell.replaceAll("\u001B\\[[;\\d]*m", "");
                widths[c] = Math.min(Math.max(widths[c], plain.length()), MAX_COL_WIDTH);
            }
        }

        String sep = buildSep(widths);
        System.out.println(ColorUtil.DIM + "  " + sep + ColorUtil.RESET);

        // Header row
        System.out.print(ColorUtil.BOLD_WHITE + "  |");
        for (int c = 0; c < cols; c++) {
            System.out.printf(" %-" + widths[c] + "s |", trunc(headers[c], widths[c]));
        }
        System.out.println(ColorUtil.RESET);
        System.out.println(ColorUtil.DIM + "  " + sep + ColorUtil.RESET);

        // Data rows
        if (rows.length == 0) {
            System.out.println(ColorUtil.DIM + "  (no rows)" + ColorUtil.RESET);
        } else {
            for (int r = 0; r < rows.length; r++) {
                String rowColor = r % 2 == 0 ? ColorUtil.WHITE : ColorUtil.WHITE;
                System.out.print(rowColor + "  |");
                for (int c = 0; c < cols; c++) {
                    String cell = (rows[r] == null || c >= rows[r].length || rows[r][c] == null) ? "" : rows[r][c];
                    System.out.printf(" %-" + widths[c] + "s |", trunc(cell, widths[c]));
                }
                System.out.println(ColorUtil.RESET);
            }
        }

        System.out.println(ColorUtil.DIM + "  " + sep + ColorUtil.RESET);
        System.out.println(ColorUtil.DIM + "  Total: " + rows.length + " row(s)" + ColorUtil.RESET);
    }

    /**
     * Print a JDBC ResultSet as a formatted table.
     * Reads all columns from metadata automatically.
     */
    public static void resultSet(ResultSet rs) {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();

            String[] headers = new String[cols];
            for (int c = 1; c <= cols; c++) {
                headers[c-1] = meta.getColumnLabel(c);
            }

            java.util.List<String[]> rowList = new java.util.ArrayList<>();
            while (rs.next()) {
                String[] row = new String[cols];
                for (int c = 1; c <= cols; c++) {
                    Object val = rs.getObject(c);
                    row[c-1] = val == null ? "NULL" : val.toString();
                }
                rowList.add(row);
            }

            table(headers, rowList.toArray(new String[0][]));

        } catch (SQLException e) {
            error("Error reading ResultSet: " + e.getMessage());
        }
    }

    // ── Goodbye banner ────────────────────────────────────────────────────────

    public static void goodbye() {
        System.out.println(ColorUtil.BOLD_CYAN +
            "\n  ╔══════════════════════════════════════════════════════╗" +
            "\n  ║   Thank you for using Health Clinic JDBC Practice!  ║" +
            "\n  ║             Goodbye and happy coding! 🎉             ║" +
            "\n  ╚══════════════════════════════════════════════════════╝" +
            ColorUtil.RESET + "\n");
    }

    // ── Private helpers ───────────────────────────────────────────────────────

    private static String buildSep(int[] widths) {
        StringBuilder sb = new StringBuilder("+");
        for (int w : widths) sb.append("-".repeat(w + 2)).append("+");
        return sb.toString();
    }

    private static String trunc(String s, int maxLen) {
        if (s == null) return "";
        // Preserve ANSI codes but truncate visible content
        String plain = s.replaceAll("\u001B\\[[;\\d]*m", "");
        if (plain.length() <= maxLen) return s;
        return plain.substring(0, maxLen - 2) + "..";
    }
}
