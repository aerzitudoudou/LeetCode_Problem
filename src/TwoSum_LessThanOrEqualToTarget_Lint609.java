import java.util.Arrays;

public class TwoSum_LessThanOrEqualToTarget_Lint609 {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for(int i = nums.length - 1; i >= 1; i--){
            res += findLessOrEqual(nums, i - 1, nums[i],target);
        }
        return res;
    }

    private int findLessOrEqual(int[] nums, int k, int cur, int target){
        int l = 0, r = k;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] <= target - cur){
                l = mid;
            }else r = mid - 1;
        }

        return r + 1;
    }

}
