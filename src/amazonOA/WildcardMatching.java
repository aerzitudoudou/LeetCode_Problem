package amazonOA;
//leetcode 44



/*

dp[i][j] represents whether the first i character in p and first j character in s is matched

induction rule:

if(p[i - 1] == s[j - 1] or p[i - 1] == '?') , then currrent strings are matched. dp[i][j] can be transformed from dp[i - 1][j - 1]
if p[i - 1] == '*' then this position can match 0 to several characters,
therefore dp[i][j] can be transformed from dp[i - 1][j](current * doesn't match any character),
or transformed from dp[i][j - 1](current * matched the character on the current position).

initialization:
dp[0][0] = true represents that empty strings are matched.

* */
//T:O(M * N) S:O(M * N) where M is the length of s and N is the length of p
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int len1 = p.length(), len2 = s.length();
        boolean[][] dp = new boolean[len1 + 1] [len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }


}
