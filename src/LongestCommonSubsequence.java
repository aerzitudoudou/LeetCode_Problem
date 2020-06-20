/*
*
*
*
* 177. Longest Common Subsequence
Medium
Find the length of longest common subsequence of two given strings.

Assumptions

The two given strings are not null
Examples

S = “abcde”, T = “cbabdfe”, the longest common subsequence of s and t is {‘a’, ‘b’, ‘d’, ‘e’}, the length is 4.


*
* -- char一样的, m[i - 1][j - 1] + 1
* -- char不一样的， 取m[i - 1][j] 和m[i][j - 1]的最大值继承过来
*
* 这篇解答很好：
* --https://leetcode.com/problems/longest-common-subsequence/solution/
*
*
*
*
* */

public class LongestCommonSubsequence {
    //way1: T: O(m * n) S:O(m * n)
    public int longest(String source, String target) {

        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return 0;
        }

        int[][] m = new int[source.length() + 1][target.length() + 1];
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[0].length; j++){
                if(i == 0){
                    m[i][j] = 0;
                }else if(j == 0){
                    m[i][j] = 0;
                }else if(source.charAt(i - 1) == target.charAt(j - 1)){
                    //注意： 第一次做错了 相等的时候这里不是max(m[i - 1][j], m[i][j - 1]) + 1
                    m[i][j] = m[i - 1][j - 1] + 1;
                }else{
                    m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]);
                }
            }
        }
        return m[source.length()][target.length()];
    }

    //way2: 滚动数组把空间复杂度降到O(min(m, n)): https://leetcode.com/problems/longest-common-subsequence/solution/
    public int longest2(String source, String target) {
        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return 0;
        }
        //let source always be the shorter string
        if(source.length() > target.length()){
            String tmp = source;
            source = target;
            target = tmp;
        }

        int[] previous = new int[source.length() + 1];
        int[] cur = null;
        for(int i = 1; i <= target.length(); i++){
            //每次create 一个新的cur
            cur = new int[source.length() + 1];
            for(int j = 1; j <= source.length(); j++){
                if(source.charAt(j - 1) == target.charAt(i - 1)){
                    cur[j] = previous[j - 1] + 1;
                }else{
                    cur[j] = Math.max(previous[j], cur[j - 1]);
                }
            }
            previous = cur;
        }
        return cur[cur.length - 1];


    }


    //way3: 另一种方法实现滚动数组 - 滚动数组把空间复杂度降到O(min(m, n))
    public int longest3(String source, String target) {
        if(source == null || source.length() == 0 || target == null || target.length() == 0){
            return 0;
        }
        //let source always be the shorter string
        if(source.length() > target.length()){
            String tmp = source;
            source = target;
            target = tmp;
        }
        //只create一次cur
        int[] prev = new int[source.length() + 1];
        int[] cur = new int[source.length() + 1];
        for(int i = 1; i <= target.length(); i++){
            for(int j = 1; j <= source.length(); j++){
                if(source.charAt(j - 1) == target.charAt(i - 1)){
                    cur[j] = prev[j - 1] + 1;
                }else{
                    cur[j] = Math.max(prev[j], cur[j - 1]);
                }
            }
            //prev cur 互换，下一轮cur 直接overwrite 原来的previous 值
            int[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        //出来的时候prev 指的最新的cur，cur指的是prev, 所以结果是prev的最后一个值，不是cur的
        return prev[prev.length - 1];


    }
}
