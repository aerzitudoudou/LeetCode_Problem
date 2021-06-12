public class RegularExpressionMatching_LC10 {
    //from https://www.bilibili.com/s/video/BV1Tt4y1U7QP
    /*

    aab
     0 2
    c*a*b

    0 <= i <= s.length
    0 <= j <= p.length
    dp[i][j]  s 0th to ith  matched
              p 0th to jth


    result: dp[s.length][p.length]

    aab
    c*a*b

    1. dp里的第i位 对应s[i - 1]
    2. 查相同元素： s[index] = p[index] || p[index]='.'
    3. 等于*时，
       往上看两位（*取0个preceding element, 或*取1个preceding element） || 往左看一位(*取两个preceding element)
       !!!!*取三个以及更多的不用看。超过当前位了。dp 只做linear scan 回头看。


    if   p[j - 1] != '*'
          dp[i][j] = dp[i - 1][j - 1] && (s的第i个值s[i - 1] = p的第j个值p[j - 1] || p[j - 1] = .)

    else

         dp[i][j - 1] = true(* match 1 个前面的值) || dp[i][j - 2] = true(* match 0 个前面的值)
           || (dp[i - 1][j] = true && p的第j - 1个值 p[j - 2] == （. || s的第i个值s[i - 1]))  (match 2 个前面的值)

       a
    c*a*
    dp：
         \   a  a  b
             0  1  2
    \  0 t   f  f  f
    c  1 f   f  f  f
    *  2 t   f  f  f
    a  3 f   t  f  f
    *  4 t   t  t  f
    b  5 f   f  f  t

    abbba
    ab*

        \ a b b b a
    \   t f f f f f
    a   f t f f f f
    b   f f t f f f
    *   f t t t t f
    a   f t f f f t



    */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int j = 2; j < n + 1; j++){
            if(p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1] || dp[0][j - 2];
        }
        for(int i = 1; i < m + 1; i++){
            for(int j = 1; j < n + 1; j++){
                if(p.charAt(j - 1) != '*'){
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i - 1][j - 1];
                }else{
                    dp[i][j] = dp[i][j - 1] || (j >= 2 && dp[i][j - 2]) || (j >= 2 && dp[i - 1][j] && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)));
                }
            }
        }

        return dp[m][n];

    }
}
