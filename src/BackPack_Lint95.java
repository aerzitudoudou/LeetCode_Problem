public class BackPack_Lint95 {
    //sol1, my, O(mn), O(mn)
    public int backPack(int m, int[] A) {
        //dp[i][j] represents first i item, value <= j, the max value that it can be
        //init:dp[0][j] = 0, dp[i][0] = 0
        //not take i: dp[i][j] = dp[i - 1][j]
        //carful 1 item can only be chosen 1 time, so 0/1 back pack question
        //take i： dp[i][j] = dp[i - 1][j - A[i - 1]] + A[i - 1], where j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]] + A[i - 1] <= j
        //dp[i][j] = max (take i, not take i)

        int[][] dp = new int[A.length + 1][m + 1];
        for(int i = 1; i < A.length + 1; i++){
            for(int j = 0; j < m + 1; j++){
                dp[i][j] = dp[i - 1][j];
                if(j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]] + A[i - 1] <= j){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
                }
            }
        }
        return dp[A.length][m];
    }

    //sol2, decrease the space complexity by 1 dimension
    //O(nm) O(m)
    public int backPack2(int m, int[] A) {
        //dp[i][j] represents first i item, value <= j, the max value that it can be
        //init:dp[0][j] = 0, dp[i][0] = 0
        //not take i: dp[i][j] = dp[i - 1][j]
        //take i： dp[i][j] = dp[i - 1][j - A[i - 1]] + A[i - 1], where j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]] + A[i - 1] <= j
        //dp[i][j] = max (take i, not take i)

        int[] dp1 = new int[m + 1]; //old
        for(int i = 1; i < A.length + 1; i++){
            int[] dp2 = new int[m + 1];
            for(int j = 0; j < m + 1; j++){
                dp2[j] = dp1[j];
                if(j - A[i - 1] >= 0 && dp1[j - A[i - 1]] + A[i - 1] <= j){
                    dp2[j] = Math.max(dp2[j], dp1[j - A[i - 1]] + A[i - 1]);
                }
            }
            dp1 = dp2;
        }
        return dp1[m];
    }
}
