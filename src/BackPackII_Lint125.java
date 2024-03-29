public class BackPackII_Lint125 {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {

        //!!!sol1, from acwing:https://www.acwing.com/video/34/, O(nm), O(nm)
        if(A == null || V == null || A.length == 0 || V.length == 0 || m == 0){
            return 0;
        }


        int[][] dp = new int[A.length + 1][m + 1]; //max with first i item having size j
        for(int i = 1; i <= A.length; i++){
            for(int j = 0; j <= m; j++){
                //not take i
                dp[i][j] = dp[i - 1][j];
                //take i
                if(j - A[i - 1] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);
            }
        }

        return dp[A.length][m];
    }

    //!!!sol2.0: from jh, O(nm), O(m)
    public int backPackII2(int m, int[] A, int[] V) {
        if(A == null || V == null || A.length == 0 || V.length == 0 || m == 0){
            return 0;
        }

        //i only has relation with i - 1, can be represented with 2 dp 1D arrays

        int[] dp1 = new int[m + 1]; //old
        for(int i = 1; i <= A.length; i++){

            int[] dp2 = new int[m + 1]; //new

            for(int j = 0; j <= m; j++){ //why big to small here? even if using dp1 and dp2
                dp2[j] = dp1[j];
                if(j - A[i - 1] >= 0) dp2[j] = Math.max(dp2[j], dp1[j - A[i - 1]] + V[i - 1]);

            }
            dp1 = dp2;
        }
        return dp1[m];

    }
    //!!!Sol2.1, 这个方法更好的说明的dp1和2 swap的交替使用
    public int backPackII2_1(int m, int[] A, int[] V) {
        if(A == null || V == null || A.length == 0 || V.length == 0 || m == 0){
            return 0;
        }

        //i only has relation with i - 1, can be represented with 2 dp 1D arrays

        int[] dp1 = new int[m + 1]; //old
        int[] dp2 = new int[m + 1]; //new
        for(int i = 1; i <= A.length; i++){

            for(int j = 0; j <= m; j++){
                dp2[j] = dp1[j];
                if(j - A[i - 1] >= 0) dp2[j] = Math.max(dp2[j], dp1[j - A[i - 1]] + V[i - 1]);

            }
            int[] tmp = dp1;
            //new becomes old in the next loop
            dp1 = dp2;
            dp2 = tmp;
        }
        return dp1[m];

    }

    //sol3, from acwing: https://www.acwing.com/video/34/ O(nm), O(m)， 不推荐物理意义不明确只是等价转化
    public int backPackII3(int m, int[] A, int[] V) {
        if(A == null || V == null || A.length == 0 || V.length == 0 || m == 0){
            return 0;
        }

        //i only has relation with i - 1

        int[] dp = new int[m + 1];
        for(int i = 1; i <= A.length; i++){
            for(int j = m; j >= 0; j--){//dp[j - a[i - 1]] 看的是[i - 1]也就是上一轮的值 如果从小到大则看的是这一轮的值，从大到小可以避免这个问题
                //dp[j] = dp[j];
                if(j - A[i - 1] >= 0) dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
            }
        }
        return dp[m];
    }
}
