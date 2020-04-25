/*

lintcode
*420. Count and Say

The count-and-say sequence is the sequence of integers beginning as follows:

1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.

11 is read off as "two 1s" or 21.

21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth sequence.

Example
Example 1:

Input: 1
Output: "1"
Example 2:

Input: 5
Output: "111221"
Notice
The sequence of integers will be represented as a string.
*
* */

public class CountAndSay {
   //my way
    //T: O(n * m) m is the longest length of res
    //S: O(m)
   public String countAndSay(int n) {
       String res = "1";
       for(int i = 0; i < n - 1; i++){
           res = count(res);
       }
       return res;
   }


    private String count(String s){
        int slow = 0;
        int fast = 0;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(slow < s.length()){
            if(fast >= s.length() || s.charAt(slow) != s.charAt(fast)){
                sb.append(String.valueOf(count));
                sb.append(String.valueOf(s.charAt(slow)));
                count = 0;
                slow = fast;
            }

            fast++;
            count++;

        }
        return sb.toString();
    }


    //way 2:一个指针，linear scan 回头看，只要是和上一个不一样了就计入最后string 结果，并且重置计数器
    //i 从1 到s.length()
    //注意一种写法while(n-- >0) 表明做n次， 所以此处的while(n-- > 1)表示做了n - 1次 起始状态是1 做n-1次到达n的状态
    //T: O(mn)
    //S:O(m)
    public String countAndSay2(int n){
       String res = "1";
       while(n-- > 1){
           StringBuilder sb = new StringBuilder();
           //count 的物理意义是i 之前有多少个string.charAt(i -1)
           for(int i = 1, count = 1; i <= res.length(); i++, count++){ //这种写法值得借鉴 同时初始化两个变量， logic 结束后，同时更新两个变量
               if(i == res.length() || res.charAt(i) != res.charAt(i - 1)){
                   sb.append(count);
                   sb.append(res.charAt(i - 1));
                   count = 0;
               }
           }
           res = sb.toString();
       }
       return res;
    }


}
