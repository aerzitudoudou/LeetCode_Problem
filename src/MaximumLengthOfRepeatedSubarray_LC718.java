public class MaximumLengthOfRepeatedSubarray_LC718 {
    /*

    dp[i][j] represent maximum length of a the subarray from 1st to ith in nums1 and 1st to jth in nums2

    return dp[nums1.length][nums2.length]

    1 2 3 2 1

    3 2 1 4 7

    if nums1[i] != nums2[j]
        dp[i][j] = max(dp[i - 1][j] , dp[i][j - 1])
      if nums1[i] == nums2[j]
          if(nums1[i - 1] == nums2[j - 1])
            dp[i][j] = max(dp[i - 1][j] , dp[i][j - 1], dp[i - 1][j - 1] + 1)
          else
             dp[i][j] = max(dp[i - 1][j] , dp[i][j - 1])

  i 0 1 2 3 4 5   length = n

j 0 0 0 0 0 0 0
  1 0 0 0 1 1 1
  2 0 0 1 1 2 2
  3 0 1 1 1 2 3
  4 0 1 1 1 2 3
  5 0 1 1 1 2 3



     e.g2

     0 1  1  1   1
     1 0  1  0   1

     0 1 2 3 4 5
  0  0 0 0 0 0 0
  1  0 0 1 1 1 1
  2  0 1 1 1 1 1
  3  0 1 2 2 2 2
  4  0 1 2 2 2 2
  5  0 1 2 2 2 2


length = m


    */
    public int findLength(int[] nums1, int[] nums2) {
        int n = nums1.length + 1, m = nums2.length + 1;

        int[][] dp = new int[n][m];

        // for(int i = 1; i < n ; i++ ){
        //     for(int j  = 1; j < m ; j++){
        //         int tmp = Math.max(dp[i - 1][j] , dp[i][j - 1]);
        //         if(nums1[i - 1] != nums2[j - 1]){
        //             dp[i][j] = tmp;
        //         }else{
        //             if(i == 1 || j == 1){
        //               dp[i][j] = 1;
        //             }
        //             else if (nums1[i - 2] == nums2[j - 2]){
        //                 dp[i][j] = Math.max(tmp, dp[i - 1][j - 1] + 1);
        //             }else{
        //                 dp[i][j] = tmp;
        //             }
        //         }
        //     }
        // }
        int max = 0;

        for(int i = 1; i < n ; i++ ){
            for(int j  = 1; j < m ; j++){

                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}
