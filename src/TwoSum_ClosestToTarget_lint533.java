import java.util.Arrays;

public class TwoSum_ClosestToTarget_lint533 {
    //sol1, my, O(n), O(1)
    public int twoSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, res = Integer.MAX_VALUE;
        while(l < r){
            if(nums[l] + nums[r] > target){
                res = Math.min(res, nums[l] + nums[r] - target);
                r--;
            }else{
                res = Math.min(res, target - (nums[l] + nums[r]));
                l++;
            }
        }
        return res;
    }
}
