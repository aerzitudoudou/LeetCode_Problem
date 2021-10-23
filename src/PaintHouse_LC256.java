public class PaintHouse_LC256 {
    //sol1, my, O(m), O(m) m is the number of the houses
    //dp[i][j] represents the min cost from 0th house to ith house if ith house is painted as colour j
    //init: dp[0][j] = costs[0][j]
    //res = min(dp[m][0], dp[m][1], dp[m][2])
    public int minCost(int[][] costs) {
        int m = costs.length;
        int[][] dp = new int[m][3];
        //init dp[0]
        for(int j = 0; j < 3; j++){
            dp[0][j] = costs[0][j];
        }

        for(int i = 1; i < m; i++){
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        int res = Integer.MAX_VALUE;
        for(int j = 0; j < 3; j++){
            res = Math.min(dp[m - 1][j], res);
        }

        return res;
    }

}
