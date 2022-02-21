import java.util.Arrays;

public class ThreeSumClosest_LC16 {
    //!!!sol1, from hf, https://leetcode.com/problems/3sum-closest/, O(n^2), O(1)
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE, sum = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            int curTarget = target - nums[i];
            int l = i + 1, r = nums.length - 1;
            while(l < r){
                int tmp = nums[l] + nums[r];
                if(Math.abs(tmp - curTarget) < diff){
                    diff = Math.abs(tmp - curTarget);
                    sum = tmp + nums[i];
                }
                if(tmp == curTarget) return sum;
                else if(tmp < curTarget) l++;
                else r--;
            }
        }


        return sum;
    }
}
