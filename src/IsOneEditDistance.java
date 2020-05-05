/*

Lintcode
*640. One Edit Distance
中文English
Given two strings S and T, determine if they are both one edit distance apart.
One ediit distance means doing one of these operation:

insert one character in any position of S
delete one character in S
change one character in S to other character
Example
Example 1:

Input: s = "aDb", t = "adb"
Output: true
Example 2:

Input: s = "ab", t = "ab"
Output: false
Explanation:
s=t ,so they aren't one edit distance apart
*
*
* */

public class IsOneEditDistance {

    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    //way 1: my solution from https://github.com/grandyang/leetcode/issues/161
    public boolean isOneEditDistance(String s, String t) {
        if(s == null || t == null || s.length() == 0 || t.length() == 0){
            return false;
        }
        int minLength = Math.min(s.length(), t.length());
        boolean flag = false;
        int i, j;
        for(i = 0, j = 0; i < minLength && j < minLength; i++, j++){
            if(!flag && s.charAt(i) != t.charAt(j)){
                flag = !flag;
                if(s.length() > t.length()){
                    i++;
                }else if(s.length() < t.length()){
                    j++;
                }
                continue;
            }
            if(flag && s.charAt(i) != t.charAt(j)){
                return false;
            }
        }
        return (flag && s.length() == t.length()) || Math.abs(s.length() - t.length()) == 1;
    }

    //way2: 优化
    public boolean isOneEditDistance2(String s, String t) {
        if(s == null || t == null || s.length() == 0 || t.length() == 0){
            return false;
        }
        int minLength = Math.min(s.length(), t.length());
        int i, j;
        for(i = 0, j = 0; i < minLength && j < minLength; i++, j++){
            if(s.charAt(i) != t.charAt(j)){
                if(s.length() > t.length()){
                    return s.substring(i + 1).equals(t.substring(j));
                }else if(s.length() < t.length()){
                    return s.substring(i).equals(t.substring(j + 1));
                }
                return s.substring(i + 1).equals(t.substring(j + 1));
            }

        }
        //还能出来的条件: index 在[0, minlength - 1] 区间s and t equals. 只需判断剩下的是不是差一：（e.g. SAAR AND SAARRRRRR）就不满足
        return Math.abs(s.length() - t.length()) == 1;
    }

}
