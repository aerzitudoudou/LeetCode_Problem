/*
*
* 143. Minimum Cuts For Palindromes
Medium
Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.

Assumptions

The given string is not null
Examples

“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

The minimum number of cuts needed is 3.
*
*
*
*
* */

public class MinimumCutsForPalindromes {
    //way 1: my way
    //T: O(n^2)
    //S: O(n) 实际上每一次都要create 一个string 太贵了
    public int minCuts(String input) {
        int[] m = new int[input.length() + 1];
        m[0] = 0;

        for(int i = 1; i <= input.length(); i++){
            int currentMin = i - 1;
            for(int j = i; j >= 1; j--){
                if(isPalindrome(input.substring(j - 1, i))){ //每一次create一个string在lintcode过不去，space complexity 太高了。
                    if(j == 1){
                        currentMin = 0;
                        break;
                    }
                    currentMin = Math.min(currentMin, m[j - 1] + 1);
                }
            }
            m[i] = currentMin;
        }
        return m[input.length()];
    }

    private boolean isPalindrome(String str){
        int i = 0, j = str.length() - 1;
        while(i <= j){
            if(str.charAt(i) != str.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    //way 2
    //优化ispalindrome实际空间复杂度
    public int minCuts1(String input) {
        char[] ary = input.toCharArray();
        int[] m = new int[input.length() + 1];
        m[0] = 0;

        for(int i = 1; i <= input.length(); i++){
            int currentMin = i - 1;
            for(int j = i; j >= 1; j--){
                if(isPalindrome1(ary, j - 1, i - 1)){ //优化这里的空间复杂度
                    if(j == 1){
                        currentMin = 0;
                        break;
                    }
                    currentMin = Math.min(currentMin, m[j - 1] + 1);
                }
            }
            m[i] = currentMin;
        }
        return m[input.length()];
    }


    private boolean isPalindrome1(char[] ary, int start, int end){

        while(start <= end){
            if(ary[start] != ary[end]){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    //way 3
    //优化isPalindrome时间复杂度
    public int minCuts2(String input) {
        if(input.length() == 0){ //注意corner case
            return 0;
        }
        char[] ary = input.toCharArray();
        //m[i] represents minimum cuts for substring[0, i - 1]
        int[] m = new int[input.length() + 1];
        //p[i][j] 表示input [i - 1, j - 1]区间的substring是不是palindrome
        boolean[][] isP = new boolean[ary.length + 1][ary.length + 1];

        for(int end = 1; end <= input.length(); end++){
            m[end] = end;
            for(int start = end; start >= 1; start--){

                isP[start][end] =(end - start < 2 || isP[start + 1][end - 1]) && ary[start - 1] == ary[end - 1];
                //[start - 1, end - 1] 是palindrome
                //要看[0, start - 2] index 区间mini cut
                if(isP[start][end]){ //进一步优化时间复杂度
                    m[end] = Math.min(m[end], m[start - 1] + 1);
                }
            }
        }
        return m[input.length()] - 1;
    }



}
