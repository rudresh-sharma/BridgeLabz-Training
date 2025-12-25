class Solution {
    public int differenceOfSum(int[] nums) {

        int elementSum = 0;
        int digitSum = 0;

        for (int num : nums) {
            elementSum += num;

            int n = num;
            while (n > 0) {
                digitSum += n % 10;
                n /= 10;
            }
        }

        return Math.abs(elementSum - digitSum);
    }
}