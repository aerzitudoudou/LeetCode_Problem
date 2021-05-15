public class JumpGame {
    public boolean canJump(int[] nums) {
        //sol 1 --> 从右往左  T： O(N * max(num[i])) == O(n^2) S: O(n)
        //错的原因，第11行写成nums[i] 原数组的index 要比dp[] 小1
        //dp[4] = true
        //dp[i] represents whether last index can be reached from index i
        //dp[i] = true if there exsit an j where 0<= j <= nums[i - 1] such that dp[i + j] = true
        //         otherwise is false
        // boolean[] dp = new boolean[nums.length + 1];
        // dp[nums.length] = true;
        // for(int i = nums.length - 1; i > 0; i--){
        //     for(int j = 1; j <= nums[i - 1]; j++){
        //         if(dp[i]) break;
        //         dp[i] = dp[i + j];
        //     }
        // }
        // return dp[1];



        //sol 2: T:O(n^2) S:O(n)
        //从左往右
        //dp[i] represents whether i can be reached from index 0
        //dp[i] = if there exsits a j, within range[0, i - 1] such that dp[j] == true && j + nums[j] >= i
//         boolean[] dp = new boolean[nums.length];
//         dp[0] = true;
//         for(int i = 1; i < nums.length; i++){
//             for(int j = 0; j <= i - 1; j++){
//                    if(dp[i]) break;
//                 dp[i] = dp[i] || dp[j] && nums[j] + j >= i;
//             }
//         }

//         return dp[nums.length - 1];

        //sol3: greedy
        //dp[i] represents the max pos it can jump to within range [0, i]
        //indcution rule: dp[i + 1] = max(dp[i], i + nums[i])
        // int[] dp = new int[nums.length];
        // dp[0] = nums[0];
        // if(nums[0] == 0 && nums.length > 1){
        //     return false;
        // }
        // for(int i = 1; i < nums.length; i++){
        //     dp[i] = Math.max(dp[i - 1], i + nums[i]);
        //     if(dp[i] == i) return false;
        //     if(dp[i] >= nums.length - 1) break;
        // }
        // return true;


        //sol 4: greedy, from Jianhang T:O(n) S:O(1)
        int cur = 0; //current index
        int maxReach = 0;//represents max index from position [0, cur - 1]
        while(cur < nums.length){
            if(maxReach  < cur) return false;
            maxReach = Math.max(maxReach, cur + nums[cur]);
            cur++;
        }
        return true;




    }

}
