import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Ask user for Roman number
        System.out.print("Enter a Roman numeral: ");
        String roman = sc.nextLine().toUpperCase();   // convert to uppercase for safety

        // Create object of Solution
        Solution sol = new Solution();

        // Call the method
        int result = sol.romanToInt(roman);

        // Print result
        System.out.println("Integer value: " + result);

        sc.close();
    }
}


class Solution {
    public int romanToInt(String s) {
        int res = 0;
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);

        for (int i = 0; i < s.length() - 1; i++) {
            if (roman.get(s.charAt(i)) < roman.get(s.charAt(i + 1))) {
                res -= roman.get(s.charAt(i));
            } else {
                res += roman.get(s.charAt(i));
            }
        }

        return res + roman.get(s.charAt(s.length() - 1));        
    }
}