import java.util.Arrays;
import java.util.List;

public class Triangle_LC120 {
    /*
    dp[i][j] represents the minimum path sum of all paths from traiangle top to a[i - 1][j - 1]
    dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j]) + a[i - 1][j - 1]
    res = min(dp[triangle.length][j])
    */
    //sol1, acwing, O(n^2), O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        int res = Integer.MAX_VALUE;
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1];
        //init
        for(int i = 0; i < n + 1; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j <= i; j++){
                if(i == 1 && j == 1){
                    dp[i][j] = triangle.get(0).get(0);
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i - 1).get(j - 1);
                }

            }
        }

        for(int j = 1; j <= n; j++){
            res = Math.min(res, dp[n][j]);
        }
        return res;
    }
}
