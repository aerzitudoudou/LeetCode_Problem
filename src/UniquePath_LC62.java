public class UniquePath_LC62 {
    //sol1, from acwing, O(m * n), O(m * n)
     public int uniquePaths1(int m, int n) {
         int[][] dp = new int[m][n];
         for(int i = 0; i < m; i++){
             for(int j = 0 ; j < n; j++){
                 if(i == 0 && j == 0){
                     dp[i][j] = 1;
                 }
                 if(i != 0){
                     dp[i][j] += dp[i - 1][j];
                 }
                 if(j != 0){
                     dp[i][j] += dp[i][j - 1];
                 }


             }
         }
         return dp[m - 1][n - 1];
     }

    //!!!sol1.1 simpler code
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0 ; j < n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 1;
                }else{

                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }


            }
        }
        return dp[m - 1][n - 1];
    }
}
