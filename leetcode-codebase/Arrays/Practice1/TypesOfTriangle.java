class Solution {

    public String triangleType(int[] nums) {

        // First check if it's a valid triangle
        if (!isValidTriangle(nums)) {
            return "none";
        }

        if (isEquilateral(nums)) {
            return "equilateral";
        } 
        else if (isScalene(nums)) {
            return "scalene";
        } 
        else {   // only possible case left
            return "isosceles";
        }
    }

    public boolean isValidTriangle(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];

        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    public boolean isEquilateral(int[] nums) {
        return nums[0] == nums[1] && nums[1] == nums[2];
    }

    public boolean isIsosceles(int[] nums) {
        return nums[0] == nums[1] || nums[1] == nums[2] || nums[0] == nums[2];
    }

    public boolean isScalene(int[] nums) {
        return nums[0] != nums[1] && nums[1] != nums[2] && nums[0] != nums[2];
    }
}