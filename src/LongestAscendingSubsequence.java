/*
*Laicode
*1. Longest Ascending Subsequence
Medium
Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence.

Assumptions

A is not null
Examples
Input: A = {5, 2, 6, 3, 4, 7, 5}
Output: 4
Because [2, 3, 4, 5] is the longest ascending subsequence.
*
* */



public class LongestAscendingSubsequence {

    //!!!sol1, 2021.10.6, from acwing, O(n^2), O(n)
    public int lengthOfLIS(int[] nums) {
        int res = 0;
        //dp[i]: longest increasing subsequence length when the last item is the ith item in the array
        int[] dp = new int[nums.length + 1];
        for(int i = 1; i < nums.length + 1; i++){
            for(int j = 0; j < i; j++){
                if(j == 0){
                    dp[i] = 1;
                }else if(nums[j - 1] < nums[i - 1]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }

            }

            res = Math.max(res, dp[i]);
        }

        //!!! 不一定最长序列的结尾是最后一位，可以合并到上一个loop同时更新最大值
        // for(int i = 1; i < nums.length  + 1; i++){
        //     res = Math.max(res, dp[i]);
        // }
        return res;
    }


    //way2 TODO: T(nlogn) 的做法。不是很懂



    //sol3: from lai, T:O(n ^2) S: O(n)
    public int longest(int[] array) {
        //dp[i] 表示从[0, i], 包括i, 最长ascending subsequence 的长度
        //maintain 一个globalmax 记录Max(dp[i])
        //base case: dp[0] = 1
        //induction rule: for all array[j] where 0 <= j < i and array[j] < array[i] , m[i] = max(array[j]) + 1
        //linear scan 回头看， 需要看所有的
        if(array == null || array.length == 0){
            return 0;
        }
        //注意初始值的init, 一开始设定的是Integer.MIN_VALUE 这里注意初始要narrow到不能narrow为止。最差情况是1，就要set成1.
        //如果set 成min_value， 会出现如果linear scan 回头看过程如果没有更新max,就直接返回min_value, 在这里显然不对，最小的长度至少也是1
        //即数组不存在ascending 的情况     e.g. 54321
        int max = 1;
        int[] dp = new int[array.length];
        dp[0] = 1;
        for(int i = 1; i < array.length; i++){
            //同样的情况，回头看的时候初始值也是要make sense的最坏情况即 = 1
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                if(array[j] < array[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;

    }
}
