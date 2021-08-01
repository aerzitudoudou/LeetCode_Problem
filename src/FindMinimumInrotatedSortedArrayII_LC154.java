public class FindMinimumInrotatedSortedArrayII_LC154 {
    //O(logn) worst O(n), O(1)
    public int findMin(int[] nums) {
        int R = nums.length - 1;
        while(R >= 0 && nums[0] == nums[R]){
            R--;
        }
        if(R < 0) return nums[0];
        int l = 0, r = R;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] >= nums[0]){
                l = mid;
            }else r = mid - 1;
        }

        return r == nums.length - 1 ? nums[0] : nums[r + 1];

    }
}
