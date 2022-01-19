public class LongestPalindromicSubstring_LC5 {
    /*
     i
    cbcbbcbd
          j
    */
    //sol1, from huahua, https://www.youtube.com/watch?v=g3R-pjUNa3k O(n^2), O(1)
    public String longestPalindrome(String s) {
        int start = 0, len = 0;
        for(int i = 0; i < s.length(); i++){
            //odd
            int len1 = getMaxLength(s, i, i);
            //even
            int len2 = getMaxLength(s, i, i + 1);
            if(len < Math.max(len1, len2)){
                len = Math.max(len1, len2);
                start = i - (len - 1) / 2;

            }

        }
        return s.substring(start, start + len);

    }

    private int getMaxLength(String s, int start, int end){
        while(start >= 0 && end <s.length() && s.charAt(start) == s.charAt(end)){
            start--;
            end++;
        }
        //end-start + 1 - 2 since the left and right boundary are not valid chars
        return end - start - 1;
    }


    //!!!sol2, my, 中心开花， 分奇数偶数讨论， O(n^2), O(1)
    public String longestPalindrome2(String s) {
        String res = "";
        int len = s.length();
        //baab  baacaab
        //odd (l, r)
        for(int i = 0; i < len; i++){
            res = calculateLen2(i, i, res, s);
        }

        //even (l, r)
        for(int i = 1; i < len; i++){
            res = calculateLen2(i - 1, i, res, s);
        }

        return res;
    }

    private String calculateLen2(int l, int r, String res, String s){
        int len = s.length();
        while(l >= 0 && r < len && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        if(r - l - 1 > res.length()){
            res = s.substring(l + 1, r);
        }
        return res;
    }
}
