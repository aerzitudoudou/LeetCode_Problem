public class BackPackIV_Lint562 {
    //sol1, my, O(nm), O(nm)
    public int backPackIV(int[] nums, int target) {
        /*dp[i][j] represents: take first ith items, backpack size = j, number of ways to fill mthe backpack
        dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i - 1]]
        init:
              dp[0][j] = 0
              dp[i][0] = 1
        */


        int[][] dp = new int[nums.length + 1][target + 1];

        for(int i = 0; i < nums.length + 1; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i < nums.length + 1; i++){
            for(int j = 0; j < target + 1; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - nums[i - 1] >= 0){
                    dp[i][j] += dp[i][j - nums[i - 1]];
                }

            }
        }

        return dp[nums.length][target];
    }

    //sol2, my, O(nm), O(m) 等价转换
    public int backPackIV2(int[] nums, int target) {
        /*dp[i][j] represents: take first ith items, backpack size = j, number of ways to fill mthe backpack
        dp[i][j] = dp[i - 1][j] + dp[i][j - nums[i - 1]]
        init:
              dp[0][j] = 0
              dp[i][0] = 1
        */


        int[] dp1 = new int[target + 1];

        dp1[0] = 1;
        for(int i = 1; i < nums.length + 1; i++){
            int[] dp2 = new int[target + 1];
            for(int j = 0; j < target + 1; j++){
                dp2[j] = dp1[j];
                if(j - nums[i - 1] >= 0){
                    dp2[j] += dp2[j - nums[i - 1]];
                }

            }
            dp1 = dp2;
        }

        return dp1[target];
    }
}
