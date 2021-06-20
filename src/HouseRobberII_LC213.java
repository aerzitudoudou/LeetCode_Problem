import java.util.Arrays;

public class HouseRobberII_LC213 {
    //!!!!!sol1: O(n), O(1)
    public int rob1(int[] nums) {
        //two cases,
        // 1.choose first item in nums and not the last one
        //2. not choose first and choose last
        //!!corner case check
        if(nums.length == 1) return nums[0];
        return Math.max(helper1(nums, 0, nums.length - 2), helper1(nums, 1, nums.length -1));
    }

    //dp[i] represents the max value robbed on the i'th number in nums with value nums[i - 1]
    //nums   [1,2]
    //dp:  {0,1,2}
    //start end is the index in nums
    private int helper1(int[] nums, int start, int end) {

        int pre1 = 0, pre2 = nums[start];

        for (int i = start + 2; i <= end + 1; i++) {
            int cur = Math.max(nums[i - 1] + pre1, pre2);
            pre1 = pre2;
            pre2 = cur;
        }

        return pre2;
    }



    /*
    * sol2: my, O(n), O(n)
    *
    * */
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0],nums[1]);
        int[] one = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] two = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(helper(one), helper(two));
    }

    private int helper(int[] nums){
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0],nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length; i++){
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }




}
