import java.util.HashMap;
import java.util.Map;

/*
*
*Lintcode 313
*
*Description
There are two arrays aa and bb, they are both made of same integer set.
The integers in an array are pairwise distinct.
In every operation, you can move the first or the last integer of aa, and insert it into aa.
Please calculate the minimum number of operations you need to take.

Clarification
In the example, the operations on aa is described below:

Insert 22 between 1, 31,3, we'll get [5,4,1,2,3][5,4,1,2,3].
Put 55 to the last of array, we'll get [4,1,2,3,5][4,1,2,3,5].
Insert 44 between 3, 53,5, we'll get [1,2,3,4,5][1,2,3,4,5].
Example
Input：
a = [5,4,1,3,2]
b = [1,2,3,4,5]
Output：
3
*
* */
public class MinimumInsertion {
    public int minimumInsertion(int[] a, int[] b) {
        /*
        转化为在a 里找到一个substring, 使得该substring 在b中的subsequence 最长。
        e.g. 下面例子里13（length = 2）是满足条件的a的substring在b中是一个subsequence 的最长的值. 所以需要动的是13 这部分的左右两边 i.e. 54 和2 ， 所以需要挪动的值是3

        a = [5,4,1,3,2]    54 | 13 | 2
        b = [1,2,3,4,5]



        dp[i]物理意义： 表示以index = i结尾的a的substring同时又是b的subsequence的最长的长度
        base case:
        dp[0] = 1
        induction rule:

        if (index of a[i] in b > index of a[i - 1] in b): dp[i] = dp[i - 1] + 1
        otherwise dp[i] = 1

        结果为b.length - dp[i]的最大值


        */

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < b.length; i++){
            map.put(b[i], i);
        }


        int[] dp = new int[a.length];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < a.length; i++){
            if(map.get(a[i]) > map.get(a[i - 1])){
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }else{
                dp[i] = 1;
            }
        }
        return b.length - max;



    }
}
