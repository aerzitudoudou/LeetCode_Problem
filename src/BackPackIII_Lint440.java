public class BackPackIII_Lint440 {
    //sol1, from acwing, https://www.acwing.com/video/34/, O(nmk), O(nm)
    public int backPackIII(int[] A, int[] V, int m) {
        if(V == null || A == null || V.length == 0 || A.length == 0 || m == 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][m + 1];
        for(int i = 1; i <= A.length; i++){
            for(int j = 0; j <= m; j++){
                for(int k = 0; j - k * A[i - 1] >= 0; k++){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * A[i - 1]] + k * V[i - 1]);
                }
            }
        }

        return dp[A.length][m];
    }

    //sol2: decrease the dimension of dp from sol1, i and i-1 are only 2 states, therefore can use 2 arrays to replace
    //O(nmk), O(m)
    public int backPackIII_2(int[] A, int[] V, int m) {
        if(V == null || A == null || V.length == 0 || A.length == 0 || m == 0){
            return 0;
        }

        int[] dp1 = new int[m + 1]; //old

        for(int i = 1; i <= A.length; i++){

            int[] dp2 = new int[m + 1]; //new
            for(int j = 0; j <= m; j++){
                for(int k = 0; j - k * A[i - 1] >= 0; k++){
                    dp2[j] = Math.max(dp2[j], dp1[j - k * A[i - 1]] + k * V[i - 1]);
                }
            }

            dp1 = dp2;
        }

        return dp1[m];
    }


    //sol3: rolling array, using one array to store both new and old states.
    //O(nmk), O(m)
    //TODO: why this is not correct?
    public int backPackIII_3(int[] A, int[] V, int m) {
        if(V == null || A == null || V.length == 0 || A.length == 0 || m == 0){
            return 0;
        }

        int[] dp = new int[m + 1]; //old

        for(int i = 1; i <= A.length; i++){
            for(int j = m; j >=0; j--){
                for(int k = 0; j - k * A[i - 1] >= 0; k++){
                    dp[j] = Math.max(dp[j], dp[j - k * A[i - 1]] + k * V[i - 1]);
                }
            }

        }

        return dp[m];
    }



    //sol4: remove the k time dimension by observation
    //O(nm), O(nm)
    //f[i][j] =        max(f[i - 1][j], f[i - 1][j - A[i]] + V[i], f[i - 1][j - 2A[i]] + 2V[i], f[i - 1][j - 3A[i]] + 3V[i]....)
    //f[i][j - A[i]] = max(             f[i - 1][j - A[i]],        f[i - 1][j - 2A[i]] + V[i],  f[i - 1][j - 3A[i]] + 2V[i])
    //f[i][j] = max(f[i - 1][j], f[i][j - A[i]] + V[i])
    public int backPackIII_4(int[] A, int[] V, int m) {
        if(V == null || A == null || V.length == 0 || A.length == 0 || m == 0){
            return 0;
        }
        int[][] dp = new int[A.length + 1][m + 1]; //old
        for(int i = 1; i <= A.length; i++){
            for(int j = 0; j <= m; j++){//todo: weisha budui?
                dp[i][j] = dp[i - 1][j];
                if(j - A[i - 1] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]);
                }
            }
        }

        return dp[A.length][m];
    }

    //sol 5: remove the i in space dimension from sol4
    //O(nm), O(m)
    public int backPackIII_5(int[] A, int[] V, int m) {
        if(V == null || A == null || V.length == 0 || A.length == 0 || m == 0){
            return 0;
        }

        int[] dp = new int[m + 1]; //old

        for(int i = 1; i <= A.length; i++){
            for(int j = A[i - 1]; j <= m; j++){//todo: weisha budui?
                dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);


            }


        }

        return dp[m];
    }



}
