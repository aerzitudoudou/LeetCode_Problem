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
    //way1: T:O(n ^2) S: O(n)
    public int longest(int[] array) {
        //dp[i] 表示从[0, i], 包括i, 最长ascending subsequence 的长度
        //maintain 一个globalmax 记录Max(dp[i])
        //base case: dp[0] = 1
        //induction rule: for all array[j] where 0 <= j < i and array[j] < array[i] , m[i] = max(array[j]) + 1
        //linear scan 回头看， 需要看所有的
        if(array == null || array.length == 0){
            return 0;
        }
        int max = 1;
        int[] dp = new int[array.length];
        dp[0] = 1;
        for(int i = 1; i < array.length; i++){
            int currentMax = 1;
            for(int j = i - 1; j >= 0; j--){
                if(array[j] < array[i]){
                    currentMax = Math.max(currentMax, dp[j] + 1);
                }
            }
            dp[i] = currentMax;
            max = Math.max(max, dp[i]);
        }
        return max;

    }

    //way2 TODO: T(nlogn) 的做法。不是很懂
}
