import java.util.*;

class Solution {

    // reverse subarray
    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // right rotation using reversal algorithm
    static void rightRotate(int[] arr, int k) {
        int n = arr.length;
        k %= n;

        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }

    // LeetCode-style method
    public void rotate(int[] nums, int k) {
        rightRotate(nums, k);
    }

    // ---------- DRIVER CODE ----------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();     // array size
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();     // rotation steps

        Solution sol = new Solution();
        sol.rotate(nums, k);

        for (int x : nums) {
            System.out.print(x + " ");
        }
    }
}
