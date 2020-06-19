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

    //way2: TODO: 滚动数组把空间复杂度降到O(min(m, n))

}
