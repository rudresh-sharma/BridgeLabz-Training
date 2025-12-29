import java.util.*;

class Solution {
    public int missingNumber(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i <= nums.length; i++) {
            sum1 += i;
        }

        for (int num : nums) {
            sum2 += num;
        }

        return sum1 - sum2;
    }

    // -------- DRIVER CODE --------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();      // size of array (n numbers from 0..n)
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Solution sol = new Solution();
        int missing = sol.missingNumber(nums);

        System.out.println(missing);
    }
}
