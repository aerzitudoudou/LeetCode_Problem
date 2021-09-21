public class CoinChange_LC322 {
    //sol1, my, O(n * m), O(n * m)
    public int coinChange(int[] coins, int m) {
        int[][] dp = new int[coins.length + 1][m + 1];

        for(int i = 0; i < coins.length + 1; i++){
            for(int j = 0; j < m + 1; j++){
                if(i == 0) {
                    dp[i][j] = -1;
                }
                else if(j == 0){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i - 1][j];
                    if(j - coins[i - 1] >= 0){
                        if(dp[i - 1][j] == -1 && dp[i][j - coins[i - 1]] == -1){
                            dp[i][j] = -1;
                        }else if(dp[i - 1][j] == -1 && dp[i][j - coins[i - 1]] != -1){
                            dp[i][j] = dp[i][j - coins[i - 1]] + 1;
                        }else if(dp[i - 1][j] != -1 && dp[i][j - coins[i - 1]] == -1){
                            dp[i][j] = dp[i - 1][j];
                        }else{
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                        }
                    }
                }



            }
        }

        return dp[coins.length][m];

    }


}
