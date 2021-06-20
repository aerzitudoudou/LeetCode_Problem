public class HouseRobber_LC198 {
    /*
    * !!!!!sol 2.1 O(n), O(1) decrease the space complexity by using variables
    *   pre1 pre2  cur  * * *
              pre1 pre2  cur
    * */
    public int rob2_2(int[] nums) {
        int pre1 = 0, pre2 = nums[0];

        for(int i = 2; i <= nums.length; i++){
            int cur = Math.max(nums[i - 1] + pre1, pre2);
            pre1 = pre2;
            pre2 = cur;
        }

        return pre2;
    }


    //dp[i] represents the max value robbed on the i'th number in nums with value nums[i - 1], no more corner case checking needed
    //nums   [1,2]
    //dp:  {0,1,2}
    //sol 2. dp has one more digit. O(n), O(1)
    public int rob2(int[] nums) {

        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];

        for(int i = 2; i < dp.length; i++){
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length];
    }


    /*
             i
         1 2 3 1    O(2^n)   2 states: rob or not rob
  dp[]   1 2
             i = 2
          Max  1. rob: dp[i] = nums[i] + dp[i -2]
               2. not rob: dp[i - 1]




  dp[i]: when index = i, maximum amount of money you can rob so far in the range [0, i]

  res: dp[nums.length - 1]
  */
    //Sol 1: from huahua: https://www.youtube.com/watch?v=H75Qp7ExCwo O(n), O(n)
    public int rob(int[] nums) {

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

    //sol1.1: decrease space to O(1)
    // pre1 pre2  cur  * * *
    //      pre1 pre2  cur
    public int rob1(int[] nums) {

        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0],nums[1]);
        int pre1 = nums[0];
        int pre2 = Math.max(nums[0],nums[1]);

        for(int i = 2; i < nums.length; i++){
            int cur = Math.max(nums[i] + pre1, pre2);
            pre1 = pre2;
            pre2 = cur;
        }

        return pre2;

    }
}
