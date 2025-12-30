import java.util.Scanner;

class Solution {

    public String intToRoman(int num) {
        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL",
                                  "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; ++i) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }

        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Input from user
        System.out.print("Enter an integer (1 to 3999): ");
        int num = sc.nextInt();

        // Create Solution object
        Solution sol = new Solution();

        // Call method
        String roman = sol.intToRoman(num);

        // Print result
        System.out.println("Roman Numeral: " + roman);

        sc.close();
    }
}
