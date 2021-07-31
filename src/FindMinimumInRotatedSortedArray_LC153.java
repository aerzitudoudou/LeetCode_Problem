public class FindMinimumInRotatedSortedArray_LC153 {
    //O(logn), O(1)
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] >= nums[0]){
                l = mid;
            }else r = mid - 1;


        }
        return r == nums.length - 1 ? nums[0] : nums[r + 1];

    }
}
