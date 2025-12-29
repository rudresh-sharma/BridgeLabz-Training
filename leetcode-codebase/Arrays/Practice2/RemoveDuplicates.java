import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] nums) {

        int first = 0;
        int second = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[second] != nums[first]) {
                first++;
                nums[first] = nums[second];
            }
            second++;
        }

        return first + 1;
    }
}

public class Main {
    public static void main(String[] args) {

        int[] nums = {1, 1, 2, 2, 3, 4, 4};

        Solution obj = new Solution();
        int k = obj.removeDuplicates(nums);

        System.out.println("Number of unique elements: " + k);

        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
