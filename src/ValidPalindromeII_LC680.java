public class ValidPalindromeII_LC680 {


    /*

   bcab
   */
    //!!!!!!!!sol1 https://www.youtube.com/watch?v=WQTZQ6LHm0M O(n) O(1)
    public boolean validPalindrome(String s) {
        //[0, start) is valid
        //(end, s.length() - 1] is valid

        int start = 0, end = s.length() - 1;
        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                //start and end disagree with each other: cut left and try || cur right and try
                return helper(s, start + 1, end) || helper(s, start, end - 1);
            }
            start++;
            end--;
        }
        return true;

    }

    public boolean helper(String s, int i, int j) {
        while(i < j){
            if(s.charAt(i++) != s.charAt(j--)){
                return false;
            }
        }
        return true;

    }
    //sol2: without user a helper method

    public boolean validPalindrome2(String s) {
        for(int start = 0, end = s.length() - 1; start < end; start++, end--){
            if(s.charAt(start) != s.charAt(end)){
                int i1 = start + 1, j1 = end, i2 = start, j2 = end - 1;
                while(i1 < j1 && s.charAt(i1) == s.charAt(j1)){
                    i1++;
                    j1--;
                }
                while(i2 < j2 && s.charAt(i2) == s.charAt(j2)){
                    i2++;
                    j2--;
                }
                return i1 >= j1 || i2 >= j2;

            }
        }
        return true;



    }


}
