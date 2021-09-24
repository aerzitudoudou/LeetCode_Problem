import java.util.Arrays;

public class CoinChange_LC322 {
    //sol1, my, O(n * m), O(n * m)
    public int coinChange(int[] coins, int m) {
        //dp[i][j]: first i coins , amount = j, fewest number of coins taken
        int[][] dp = new int[coins.length + 1][m + 1];

        for(int i = 0; i < coins.length + 1; i++){
            for(int j = 0; j < m + 1; j++){
                if(i == 0) {
                    dp[i][j] = -1;
                }
                else if(j == 0){
                    dp[i][j] = 0;
                }else{
                    //doesn't have
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

    //!!!sol2, from jh, use Max value to avoid analysis on -1 case, O(nm), O(nm)
    public int coinChange2(int[] coins, int m) {
        //dp[i][j]: first i coins , amount = j, fewest number of coins taken

        int[][] dp = new int[coins.length + 1][m + 1];
        for(int i = 0; i < coins.length + 1; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for(int i = 1; i < coins.length + 1; i++){
            for(int j = 0; j < m + 1; j++){
                //init: value = 0, need 0 coins
                if(j == 0){
                    dp[i][j] = 0;
                }else{
                    //doesn't have
                    dp[i][j] = dp[i - 1][j];
                    //have i'th item(check index out of bound and integer overflow!!)
                    if(j - coins[i - 1] >= 0 && dp[i][j - coins[i - 1]] != Integer.MAX_VALUE){
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                    }
                }
            }
        }
        return dp[coins.length][m] == Integer.MAX_VALUE ? -1 : dp[coins.length][m];
    }

    //!!!sol3, decrease the space dimension based on sol2, O(nm), O(m)
    public int coinChange4(int[] coins, int m) {

        int[] dp1 = new int[m + 1];

        Arrays.fill(dp1, Integer.MAX_VALUE);
        for(int i = 1; i < coins.length + 1; i++){
            int[] dp2 = new int[m + 1];
            for(int j = 0; j < m + 1; j++){
                if(j == 0){
                    dp2[j] = 0;
                }else{
                    //doesn't have
                    dp2[j] = dp1[j];
                    if(j - coins[i - 1] >= 0 && dp2[j - coins[i - 1]] != Integer.MAX_VALUE){
                        //have i'th item
                        dp2[j] = Math.min(dp1[j], dp2[j - coins[i - 1]] + 1);
                    }
                }
            }
            dp1 = dp2;
        }

        return dp1[m] == Integer.MAX_VALUE ? -1 : dp1[m];

    }




}
