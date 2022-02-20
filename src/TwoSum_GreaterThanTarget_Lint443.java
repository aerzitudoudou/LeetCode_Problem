import java.util.Arrays;

public class TwoSum_GreaterThanTarget_Lint443 {
    //sol1, my, same and lintcode 609, O(nlogn), O(1)
    //[l,r], [l + 1, r], [l + 2, r]...[r - 1, r] are all valid pairs
    public int twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, res = 0;
        while(l < r){
            if(nums[l] + nums[r] <= target) l++;
            else{
                res += r - l;
                r--;
            }
        }

        return res;
    }
}
