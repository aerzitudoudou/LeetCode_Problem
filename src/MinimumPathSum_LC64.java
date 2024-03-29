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

    //sol2, 1D dp, O(mn), O(n)
    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;


        int[] dp1 = new int[n + 1];

        for(int i = 1; i < m + 1; i++){
            int[] dp2 = new int[n + 1];
            for(int j = 1; j < n + 1; j++){
                if(i == 1){
                    dp2[j] = dp2[j - 1] + grid[i - 1][j - 1];
                }else if(j == 1){
                    dp2[j] = dp1[j] + grid[i - 1][j - 1];
                }else{
                    dp2[j] = Math.min(dp1[j], dp2[j - 1]) + grid[i - 1][j - 1];

                }
            }
            dp1 = dp2;
        }

        return dp1[n];

    }

    //sol3, re-use original grid, O(mn), O(1)
    public int minPathSum3(int[][] grid) {
        int m = grid.length, n = grid[0].length;



        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    continue;
                }else if(i == 0){
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                }else if(j == 0){
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                }else{
                    grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];

                }
            }
        }

        return grid[m - 1][n - 1];
    }

}
