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
    //my way
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

    //way 2: https://www.cnblogs.com/grandyang/p/5336668.html
    /*
    for loop 一步到位，Map只记录第一个presum 出现的index, 因为指针一直往后走，当前指针位置一定是目前为止能成为此presum 的最大指针。 中间不断更新globalmax
    * */

    public int maxSubArrayLen2(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int res = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            map.putIfAbsent(sum, i);
            int preSum = sum - k;
            if(sum == k){
                res = i + 1;
            } else if(map.containsKey(preSum)){
                res = Math.max(res, i - map.get(preSum));
            }
        }
        return res;
    }
}
