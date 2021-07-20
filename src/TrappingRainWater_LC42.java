public class TrappingRainWater_LC42 {
    //sol1, dp, O(n),O(n)
        /*
                    [4,2,0,3,2,5]
        maxLeft      0 4 4 4 4 4 5
        maxRight     5 5 5 5 5 5 0

        dp[i] = Min(maxLeft[i], maxRight[i]) - height[i]

        */
//     public int trap(int[] height) {

//         int[] maxLeft = new int[height.length + 1];
//         int[] maxRight = new int[height.length + 1];
//         int res = 0;

//         for(int i = 0; i < height.length; i++){
//             if(height[i] > maxLeft[i]){
//                 maxLeft[i + 1] = height[i];
//             }else{
//                 maxLeft[i + 1] = maxLeft[i];
//             }
//         }

//         for(int i = height.length - 1; i >= 0; i--){
//             if(height[i] > maxRight[i + 1]){
//                 maxRight[i] = height[i];
//             }else{
//                 maxRight[i] = maxRight[i + 1];
//             }
//         }

//         for(int i = 0; i < height.length; i++){
//             int minMax = Math.min(maxLeft[i + 1], maxRight[i]);
//             if(minMax > height[i]) res+=minMax - height[i];
//         }

//         return res;
//     }

    //sol2, 区间挡板法, O(n), O(1)
    /*
      [4,2,0,3,2,5]
         i       j

      init: i = 0,j = length -1
      maxLeft: [0, i] max value
      maxRight: [j, length - 1] max value
      e.g. when i = 2, maxLeft = 4, maxRight = 5

      rule:
      if(min(maxLeft, maxRight) > height[i])
            water trapped = min(maxLeft, maxRight) - height[i]
      else  water trapped = 0

      proof:
      if there's a number in between [i, j]
         1. which is large, say 6, then 4(min of maxleft and maxRight) will be holding accountable for water trapped
         2. which is very small, then 4 will still be holding accoutable for water trapped since there's 6(max of maxLeft maxright) which will keep the water for at least 4


    */
    public int trap(int[] height) {
        if(height == null || height.length == 0){
            return 0;
        }
        int i = 0, j = height.length - 1;
        int maxLeft = height[i], maxRight = height[j];
        int res = 0;
        while(i <= j){
            if(maxLeft < maxRight){
                res += Math.max(0, maxLeft - height[i]);
                maxLeft = maxLeft > height[i] ? maxLeft : height[i];
                i++;
            }else{
                res += Math.max(0, maxRight - height[j]);
                maxRight = maxRight > height[j] ? maxRight : height[j];
                j--;
            }

        }

        return res;

    }
}
