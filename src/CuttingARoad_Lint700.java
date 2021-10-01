public class CuttingARoad_Lint700 {
    //完全背包问题
    //sol1, my, O(nm), O(nm)
    public int cutting(int[] prices, int n) {
        //dp[i][j] 代表取前i段绳子， size <= j 能取到的最大价值
        //dp[i][0] = 0, dp[0][j] = 0
        //dp[i][j] = max(dp[i - 1][j] (不取)， dp[i][j - i] + prices[i - 1](取， given j - i >= 0))

        int[][] dp = new int[prices.length + 1][n + 1];
        for(int i = 1; i < prices.length + 1; i++){
            for(int j = 0; j < n + 1; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - i >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - i] + prices[i - 1]);
                }
            }
        }

        return dp[prices.length][n];
    }

    //sol2, my, O(nm), O(m)
    public int cutting2(int[] prices, int n) {
        //dp[i][j] 代表取前i段绳子， size <= j 能取到的最大价值
        //dp[i][0] = 0, dp[0][j] = 0
        //dp[i][j] = max(dp[i - 1][j] (不取)， dp[i][j - i] + prices[i - 1](取， given j - i >= 0))

        int[] dp1 = new int[n + 1];
        for(int i = 1; i < prices.length + 1; i++){
            int[] dp2 = new int[n + 1];
            for(int j = 0; j < n + 1; j++){
                dp2[j] = dp1[j];
                if(j - i >= 0){
                    dp2[j] = Math.max(dp2[j], dp2[j - i] + prices[i - 1]);
                }
            }
            dp1 = dp2;
        }

        return dp1[n];
    }
}
