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



    /*
   dp[i][j] represents the minimum path sum of all paths from traiangle top to a[i - 1][j - 1]
   dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j]) + a[i - 1][j - 1]
   res = min(dp[triangle.length][j])
   */
    //sol2, my, O(n^2), O(n)
    public int minimumTotal2(List<List<Integer>> triangle) {
        int res = Integer.MAX_VALUE;
        int n = triangle.size();
        int[] dp1 = new int[n + 1];

        //init
        Arrays.fill(dp1, Integer.MAX_VALUE);



        for(int i = 1; i < n + 1; i++){
            int[] dp2 = new int[n + 1];
            Arrays.fill(dp2, Integer.MAX_VALUE);


            for(int j = 1; j <= i; j++){
                if(i == 1 && j == 1){
                    dp2[j] = triangle.get(0).get(0);
                }else{
                    dp2[j] = Math.min(dp1[j - 1], dp1[j]) + triangle.get(i - 1).get(j - 1);
                }

            }
            dp1 = dp2;
        }

        for(int j = 1; j <= n; j++){
            res = Math.min(res, dp1[j]);
        }
        return res;
    }
}
