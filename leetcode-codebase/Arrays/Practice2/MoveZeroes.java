import java.util.*;

class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }
    }

    // ---- DRIVER CODE ----
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();          // size of array
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {  // input array
            nums[i] = sc.nextInt();
        }

        Solution sol = new Solution();
        sol.moveZeroes(nums);

        // output array
        for (int x : nums) {
            System.out.print(x + " ");
        }
    }
}
