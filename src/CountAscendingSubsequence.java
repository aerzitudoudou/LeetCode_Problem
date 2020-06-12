/*
*
*Laicode
*683. Count Ascending Subsequence
Medium
Given an array A[0]...A[n-1] of integers, count the number of ascending subsequences.

In case that the result is larger than 32-bit integer, return the result in 10^9+7 modulo.

Assumptions

A is not null
Examples
Input: A = {1,2,3}
Output: 7
Explanation: [1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]
*
*
* */







public class CountAscendingSubsequence {
    /*
    * T: O(n ^ 2)  S: O(n)
    *
    * */
    public int numIncreasingSubsequences(int[] a) {
        //m[i] represends the number of ascending subsequence between [0, i], and ending with i
        //base case: m[0] = 1
        //induction rule:m[i] = Sum(m[j]) + 1 where 0 <= j < i and a[j] < a[i]
        //res: sum(m[i]) for 0 <= i <= a.length
        if(a == null || a.length == 0){
            return 0;
        }
        int[] m = new int[a.length];
        m[0] = 1;
        int res = m[0];

        for(int i = 1; i < a.length; i++){
            for(int j = i - 1; j >= 0; j--){
                if(a[j] < a[i]){
                    m[i] += m[j];
                }
            }
            m[i]++;
            res += m[i];
        }

        return res;
    }
}
