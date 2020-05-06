import java.util.HashMap;
import java.util.Map;
/*
*Lintcode 911. Maximum Size Subarray Sum Equals k
*Description
中文
English
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Have you met this question in a real interview?
Example
Example1

Input:  nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation:
because the subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example2

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation:
because the subarray [-1, 2] sums to 1 and is the longest.
Challenge
Can you do it in O(n) time?
* */
public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] sum = new int[nums.length + 1];

        Map<Integer, int[]> map = new HashMap<>();
        map.put(0, new int[]{0, 0});
        //build pre-sum array and <sum, [minIndex][maxIndex]> map
        for(int i = 0; i < nums.length; i++){
            sum[i + 1] = sum[i] + nums[i];
            int[] value = map.putIfAbsent(sum[i + 1], new int[]{i + 1, i + 1});
            if(value != null){
                //compare i + 1 with int[0] and int[1]
                if(i + 1 < value[0]){
                    value[0] = i + 1;
                }else if(i + 1 > value[1]){
                    value[1] = i + 1;
                }
            }

        }
        /* loop through map find longest maxIndex - minIndex for presums where postSum - preSum = k */
        int globalMax = 0;
        for(Map.Entry<Integer, int[]> entry : map.entrySet()){
            //if contains s[i]
            int postNum = entry.getKey();
            int preNum = postNum - k;
            int[] post = map.get(postNum);
            if(map.containsKey(preNum)){
                int[] pre = map.get(preNum);
                globalMax = Math.max(globalMax, post[1] - pre[0]);
            }
        }

        return globalMax;





    }
}
