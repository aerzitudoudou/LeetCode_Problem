public class UniquePath_LC63 {
    //sol1, from acwing, O(m * n), O(m * n)
    public int uniquePathsWithObstacles(int[][] o) {
        int m = o.length, n = o[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(o[i][j] != 1){
                    if(i == 0 && j == 0){
                        dp[i][j] = 1;
                    }
                    if(i != 0){//including the first col
                        dp[i][j] += dp[i - 1][j];
                    }
                    if(j != 0){//including the first row
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}
