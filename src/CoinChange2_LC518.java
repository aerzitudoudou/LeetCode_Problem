public class CoinChange2_LC518 {
    //sol1, my, O(nm), O(nm)
    public int change(int m, int[] A) {
        int[][] dp = new int[A.length + 1][m + 1];
        for(int i = 1; i < A.length + 1; i++){
            for(int j = 0; j < m + 1; j++){
                if(j == 0) dp[i][j] = 1;
                else{
                    dp[i][j] = dp[i - 1][j];
                    if(j - A[i - 1] >= 0){
                        dp[i][j] += dp[i][j - A[i - 1]];
                    }
                }
            }
        }
        return dp[A.length][m];
    }

    //sol2, my, O(nm), O(m)
    public int change2(int m, int[] A) {
        int[] dp1 = new int[m + 1];

        for(int i = 1; i < A.length + 1; i++){
            int[] dp2 = new int[m + 1];
            for(int j = 0; j < m + 1; j++){
                if(j == 0) dp2[j] = 1;
                else{
                    dp2[j] = dp1[j];
                    if(j - A[i - 1] >= 0){
                        dp2[j] += dp2[j - A[i - 1]];
                    }
                }
            }
            dp1 = dp2;
        }
        return dp1[m];
    }

}
