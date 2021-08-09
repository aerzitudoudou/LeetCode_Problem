public class CountBinarySubstrings_LC696 {
    //!!!sol1.1: from lee, my implementaion, O(n), O(1)
    //                                          0011110011
    //group each num consecutive time            2  4 2 2
    //[2, 4] --> 01, 0011    [4, 2]:10, 1100    [2,2]: 01, 0011
    public int countBinarySubstrings(String s) {
        int pre = 0, cur = 1, res = 0;
        //cur:  # duplicate chars in [starting index of this num, i]
        for(int i = 0; i < s.length(); i++){
            while(i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)){
                i++;
                cur++;
            }

            res += Math.min(pre, cur);
            pre = cur;
            cur = 1;
        }
        return res;
    }

    //!!!sol1.2, from Lee, implementation with different semantic meaning
     public int countBinarySubstrings1(String s) {
         int pre = 0, cur = 1, res = 0;
         //cur:  # duplicate chars in [starting index of this num, i)
         for(int i = 1; i < s.length(); i++){
             if(s.charAt(i) == s.charAt(i - 1)){
                 cur++;
             }else{
                 res += Math.min(pre, cur);
                 pre = cur;
                 cur = 1;
             }


         }
         return res + Math.min(pre, cur);
     }


    //sol1, my, TLE
     public int countBinarySubstrings2(String s) {
         if(s == null || s.length() == 1){
             return 0;
         }
         int count = 0, zero = 0, one = 0, i = 0, j = 1;
         while(i < s.length() - 1){
             zero += s.charAt(i) == '0' ? 1 : 0;
             one += s.charAt(i) == '1' ? 1 : 0;
             while(j < s.length()){
                zero += s.charAt(j) == '0' ? 1 : 0;
                one += s.charAt(j) == '1' ? 1 : 0;
                if(zero == one && cons(s.substring(i, j + 1))) count++;
                j++;
             }
             zero = 0;
             one = 0;
             i++;
             j = i + 1;
         }



         return count;

     }

     private boolean cons(String s){
         int flip = 0;
         for(int i = 0; i < s.length() - 1; i++){
             if(s.charAt(i) != s.charAt(i + 1)){
                 flip++;
             }
         }
         return flip == 1;
     }
}
