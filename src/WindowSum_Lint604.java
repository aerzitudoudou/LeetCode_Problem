public class WindowSum_Lint604 {
    /**
     * @param nums: a list of integers.
     * @param k: length of window.
     * @return: the sum of the element inside the window at each moving.
     */
    //sol1, my, presum, O(n), O(n)
     public int[] winSum(int[] nums, int k) {
         if(nums == null || nums.length == 0 || nums.length < k) return new int[0];
         int len = nums.length;
         int[] res = new int[len - k + 1];
         int[] preSum = new int[len + 1];
         for(int i = 0; i < len; i++){
             preSum[i + 1] = preSum[i] + nums[i];
         }

         for(int i = k; i < preSum.length; i++){
             res[i - k] = preSum[i] - preSum[i - k];
         }

         return res;
     }


    //sol2, sliding window, O(n), O(n)
    public int[] winSum2(int[] nums, int k) {
        if(nums == null || nums.length == 0 || nums.length < k) return new int[0];
        int len = nums.length;
        int[] res = new int[len - k + 1];
        //calculate first sum
        for(int i = 0; i < k; i++){
            res[0] += nums[i];
        }

        for(int i = k; i < len; i++){
            res[i - k + 1] = res[i - k] - nums[i - k] + nums[i];
        }
        return res;
    }
}
