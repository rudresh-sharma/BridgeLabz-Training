class Solution {
    public int[] numberGame(int[] nums) {
        
        int[] nums2 = nums.clone();
        Arrays.sort(nums2);

       
        for(int i=0; i<nums2.length; i+=2){
            int temp = nums2[i];
            nums2[i] = nums2[i+1];
            nums2[i+1] = temp;
        }

        return nums2;
    }


   
}