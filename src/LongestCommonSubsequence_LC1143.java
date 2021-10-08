
public class LongestCommonSubsequence_LC1143 {
    //!!!sol1, from acwing, O(mn), O(nm)
     public int longestCommonSubsequence(String text1, String text2) {
         int l1 = text1.length();
         int l2 = text2.length();
         int[][] dp = new int[l1 + 1][l2 + 1];
         for(int i = 1; i < l1 + 1; i++){
             for(int j = 1; j < l2 + 1; j++){
                 dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                 if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                     dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                 }
             }
         }

         return dp[l1][l2];
     }


    //!!!sol2, 基于sol1的空间降维， O(nm), O(m) ,text1 length = n, text2 length = m
    public int longestCommonSubsequence2(String text1, String text2) {

        int l1 = text1.length();
        int l2 = text2.length();
        int[] dp1 = new int[l2 + 1];

        for(int i = 1; i < l1 + 1; i++){
            int[] dp2 = new int[l2 + 1];

            for(int j = 1; j < l2 + 1; j++){
                dp2[j] = Math.max(dp1[j], dp2[j - 1]);
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp2[j] = Math.max(dp2[j], dp1[j - 1] + 1);
                }
            }
            dp1 = dp2;
        }

        return dp1[l2];
    }

    //sol3, 基于sol2的再次优化， O(nm), O(min(nm))
    public int longestCommonSubsequence3(String text1, String text2) {

        int l1 = text1.length();
        int l2 = text2.length();
        //!!! let dp1 always has the shorter length
        if(l1 < l2) return longestCommonSubsequence(text2, text1);
        int[] dp1 = new int[l2 + 1];

        for(int i = 1; i < l1 + 1; i++){
            int[] dp2 = new int[l2 + 1];

            for(int j = 1; j < l2 + 1; j++){
                dp2[j] = Math.max(dp1[j], dp2[j - 1]);
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp2[j] = Math.max(dp2[j], dp1[j - 1] + 1);
                }
            }
            dp1 = dp2;
        }

        return dp1[l2];
    }



    // TODO: 还能再降维吗 为啥不对??
//     public int longestCommonSubsequence(String text1, String text2) {
//         int l1 = text1.length();
//         int l2 = text2.length();
//         //int[] dp1 = new int[l2 + 1];
//         //a1 = dp1[j - 1], b1 = dp1[j]
//         int a1 = 0, b1 = 0;
//         for(int i = 1; i < l1 + 1; i++){
//             //int[] dp2 = new int[l2 + 1];
//             //a2 = dp2[j - 1], b2 = dp2[j]
//             int a2 = 0, b2 = 0;
//             for(int j = 1; j < l2 + 1; j++){
//                 b2 = Math.max(b1, a2);
//                 if(text1.charAt(i - 1) == text2.charAt(j - 1)){
//                     b2 = Math.max(b2, a1 + 1);
//                 }
//             }
//             a1 = a2;
//             b1 = b2;
//         }

//         return b1;
//     }



}
