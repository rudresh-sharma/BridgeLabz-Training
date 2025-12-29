import java.util.*;

class Solution {

    public boolean canSortArray(int[] nums) {
        int[] array = nums.clone();
        int[] arr = array.clone();
        int[] bits = new int[array.length];

        for (int i = 0; i < bits.length; i++) {
            bits[i] = Integer.bitCount(array[i]);
        }

        int start = 0, end = 0;
        for (int i = 1; i < bits.length; i++) {
            if (bits[i] == bits[i - 1]) {
                end = i;
            } else {
                Arrays.sort(arr, start, end + 1);
                start = i;
                end = i;
            }
        }

        // sort last segment
        Arrays.sort(arr, start, bits.length);

        // fully sorted array
        Arrays.sort(array);

        return Arrays.equals(array, arr);
    }

    // -------- DRIVER CODE --------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();      // size of array
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        Solution sol = new Solution();
        boolean result = sol.canSortArray(nums);

        System.out.println(result);
    }
}
