import java.util.Arrays;

public class TwoSum_LessThanOrEqualToTarget_Lint609 {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    //sol1, my, binary search, O(nlogn), O(1)
    //    i-1    cur
    //2,3,4,      5    target = 8
    public int twoSum5(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for(int i = nums.length - 1; i >= 1; i--){
            res += findLessOrEqual(nums, i - 1, nums[i],target) + 1;
        }
        return res;
    }

    //find the biggest index where the number is less or equal to (target - cur)
    private int findLessOrEqual(int[] nums, int k, int cur, int target){
        int l = 0, r = k;
        while(l < r){
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] <= target - cur){
                l = mid;
            }else r = mid - 1;
        }
        return nums[r] + cur > target ? -1 : r;
    }


    //!!!sol2, from https://www.jiuzhang.com/solution/two-sum-less-than-or-equal-to-target/, O(nlogn), O(1)
    //[l, r] represents the boundary where {[l, l + 1], [l, l + 2]...[l, r - 1], [l, r]} all pairs sum < target
    //[0, l) and (r, length - 1] 已探索区域
    public int twoSum5_1(int[] nums, int target) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1, res = 0;
        while(l < r){
            int cur = nums[l] + nums[r];

            if(cur > target){
                r--;
            }else{
                res += r - l;
                l++;
            }
        }

        return res;

    }

}
