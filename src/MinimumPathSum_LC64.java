public class MinimumPathSum_LC64 {
    //sol1,my, O(mn), O(mn)
    public int minPathSum(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        //dp: minimum path sum for the i'th row and j'th column element
        int[][] dp = new int[m + 1][n + 1];


        for(int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(i == 1){
                    dp[i][j] = dp[i][j - 1] + grid[i - 1][j - 1];
                }else if(j == 1){
                    dp[i][j] = dp[i - 1][j] + grid[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];

                }
            }
        }

        return dp[m][n];
    }
}
