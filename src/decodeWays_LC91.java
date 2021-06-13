import java.util.Arrays;

public class decodeWays_LC91 {
    //Sol 1 dp
    //O(n) O(n)
     public int numDecodings1(String s) {
         int n = s.length();
         int[][] dp = new int[n][2];
         if(s.charAt(0) != '0'){
             dp[0][0] = 1;
             dp[0][1] = 0;
         }else{
             dp[0][0] = 0;
             dp[0][1] = 0;
         }

         for(int i = 1; i < n; i++){
             //dp[i][0] # of decode ways within range [0,i], and s{i} as a separate element
             if(s.charAt(i) != '0'){
                 dp[i][0] = dp[i - 1][1] + dp[i - 1][0];
             }else{
                 dp[i][0] = 0;
             }

             //dp[i][1] # of decode ways within range [0,i], and s{i} combines with s{i - 1}
             if(s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')){
                 dp[i][1] = dp[i - 1][0];
             }else{
                 dp[i][1] = 0;
             }
         }

         return dp[n - 1][0] + dp[n - 1][1];

         }

    //     //！！！！sol 1.1 from jh
    //   O(n) O(1)
       public int numDecodings2(String s) {
         int n = s.length();
         int[] dp = new int[2];
         if(s.charAt(0) != '0'){
             dp[0] = 1;
             dp[1] = 0;
         }else{
             dp[0] = 0;
             dp[1] = 0;
         }


         for(int i = 1; i < n; i++){
             int[] dpNew = new int[2]; //降维写法。把第一维[0, i] # of decode ways 用长度为 2的数组代替
             //dp[i][0] decode ways from index within range [0,i], and s{i} as a separate element
             if(s.charAt(i) != '0'){
                 dpNew[0] = dp[1] + dp[0];
             }else{
                 dpNew[0] = 0;
             }

             //dp[i][1] decode ways from index within range [0,i], and s{i} combines with s{i - 1}
             if(s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')){
                 dpNew[1] = dp[0];
             }else{
                 dpNew[1] = 0;
             }
             dp = dpNew;
         }

         return dp[0] + dp[1];

     }

    //sol 2. dfs O(n) O(n) dfs + mem
    public int numDecodings(String s) {
        char[] ary = s.toCharArray();
        int[] mem = new int[s.length()];
        Arrays.fill(mem, -1);
        return dfs(0, ary, mem);
    }
    /*
    0 1 2  size = 3
    1 2 3
    */
    private int dfs(int index, char[] ary, int[] mem){
        int n = ary.length;
        //base case
        if(index == n){
            return 1;
        }
        //mem only
        if(mem[index] != -1){
            return mem[index];
        }
        int res = 0;
        //one digit
        if(ary[index] != '0'){
            res += dfs(index + 1, ary, mem);
        }
        //two digits
        if(index < n - 1 && (ary[index] == '1' || (ary[index] == '2' && ary[index + 1] <= '6'))){
            res += dfs(index + 2, ary, mem);
        }

        return mem[index] = res;
    }


    /*
    s = 203
            2  0  3
     index  0  1  2
    dp[]    1  1  1
            2 20  20, 3


    s =       2     3       1               2                    0                      7         9 2
      index   0     1       2               3                    4                      5         6 7
    dp[] =    1     2       2               3                    2                      0         0
              2    2,3|23  2,3,1|23,1  2,3,1,2|23,1,2|23,12   2,3,1,20|23,1,20          /         / 2



    s =       2     3       1               6                    0         7         9 2
      index   0     1       2               3                    4         5         6 7
    dp[] =    1     2       2               3                    0         0         0
              2    2,3|23  2,3,1|23,1  2,3,1,6|23,1,6|23,16      /         /         / 2

     dp[][0]  当前位与之前位不连
     dp[][1]  当前位与之前位连


    dp[i][0]
        nums[i] != 0
            =  dp[i - 1][1] + dp[i - 1][0]
        nums[i] = 0
            = 0
    dp[i][1]
         nums[i - 1, i] <= 26
            = dp[i - 1][0]
        else
            = 0


    */

}
