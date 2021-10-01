public class BackPackV_Lint563 {
    //sol1,my, 0/1 backpack O(nm), O(nm)
    public int backPackV(int[] nums, int target) {
        /*
        dp[i][j] represents take first i items, number of ways to fill the back pack with size j
        dp[0][j] = 0
        dp[i][0] = 1
        dp[i][j] = dp[i - 1][j] + (if j - nums[i - 1] >= 0)dp[i - 1][j - nums[i - 1]]
        */

        int[][] dp = new int[nums.length + 1][target + 1];
        for(int i = 0; i < nums.length + 1; i++){
            dp[i][0] = 1;
        }

        for(int i = 1; i < nums.length + 1; i++){
            for(int j = 0; j < target + 1; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - nums[i - 1] >= 0){
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];

    }

    //sol2. my. 01backpack, O(nm), O(m)
    public int backPackV2(int[] nums, int target) {
        /*
        dp[i][j] represents take first i items, number of ways to fill the back pack with size j
        dp[0][j] = 0
        dp[i][0] = 1
        dp[i][j] = dp[i - 1][j] + (if j - nums[i - 1] >= 0)dp[i - 1][j - nums[i - 1]]
        */

        int[] dp1 = new int[target + 1];
        for(int i = 0; i < nums.length + 1; i++){
            dp1[0] = 1;
        }

        for(int i = 1; i < nums.length + 1; i++){
            int[] dp2 = new int[target + 1];
            for(int j = 0; j < target + 1; j++){
                dp2[j] = dp1[j];
                if(j - nums[i - 1] >= 0){
                    dp2[j] += dp1[j - nums[i - 1]];
                }
            }
            dp1 = dp2;
        }

        return dp1[target];

    }
}
