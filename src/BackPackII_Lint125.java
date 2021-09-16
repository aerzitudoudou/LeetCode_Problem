public class BackPackII_Lint125 {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {

        //sol1, from acwing:https://www.acwing.com/video/34/, O(nm), O(nm)
        if(A == null || V == null || A.length == 0 || V.length == 0 || m == 0){
            return 0;
        }


        int[][] dp = new int[A.length + 1][m + 1];
        for(int i = 1; i <= A.length; i++){
            for(int j = 0; j <= m; j++){
                int s1 = 0, s2 = 0;
                //not take i
                dp[i][j] = dp[i - 1][j];
                //take i
                if(j - A[i - 1] >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]);


            }
        }

        return dp[A.length][m];
    }

    //todo: optimize memory to 1d dp
    
}
