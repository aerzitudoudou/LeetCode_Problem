public class BurstBalloons_LC312 {
    public int maxCoins(int[] nums) {
        //sol1, from acwing, not copying array to avoid indexOutOfbound, less readable,O(n^3), O(n ^ 2)
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for(int len = 1; len <= nums.length; len++){
            for(int l = 1; l + len - 1 <= nums.length; l++){
                int r = l + len - 1;
                for(int k = l; k <= r; k++){

                    int leftCoin = l - 2 < 0 ? 1 : nums[l - 2];
                    int rightCoin = r > nums.length - 1 ? 1 : nums[r];
                    dp[l][r] = Math.max(dp[l][r], dp[l][k - 1] + dp[k + 1][r] + leftCoin * nums[k - 1]* rightCoin);
                }
            }
        }

        return dp[1][nums.length];
    }

    //!!sol2, more readable, from 416486188,https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations, O(n^3), O(n ^ 2)
    public int maxCoins2(int[] iNums) {
        int n = iNums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] nums = new int[n + 2];
        //copy the array like [1, a,b,c,d,....1]
        for(int i = 0; i < n; i++){
            nums[i + 1] = iNums[i];
        }
        nums[0] = 1;
        nums[n + 1] = 1;
        //[l, r] inclusive
        for(int len = 1; len <= n; len++){
            //start point for every len
            for(int l = 1; l + len - 1 <= n; l++){
                int r = l + len - 1; //end point
                for(int k = l; k <= r; k++){ //all positions from start to end
                    dp[l][r] = Math.max(dp[l][r], dp[l][k - 1] + dp[k + 1][r] + nums[l - 1] * nums[k]* nums[r + 1]);
                }
            }
        }

        return dp[1][n];
    }
}
