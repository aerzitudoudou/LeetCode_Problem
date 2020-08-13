import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*Leetcode 670

670. Maximum Swap
Medium

1038

71

Add to List

Share
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]

*/
public class MaximumSwap {

    //way1: my way: brute force
    public int maximumSwap(int num) {
        int max = num;
        //1.change int into int array
        List<Integer> list = toIntList(num);

        //2. do swap , updating max
        //O(m^3)
        for(int i = 0; i < list.size() - 1; i++){
            for(int j = i + 1; j < list.size(); j++){
                Collections.swap(list, i, j);
                int cur = toInt(list);
                max = Math.max(cur, max);
                Collections.swap(list, i, j);

            }
        }
        return max;
    }
    //O(m)
    private List<Integer> toIntList(int num){
        List<Integer> res = new ArrayList<>();
        while(num > 0){
            int cur = num % 10;
            res.add(cur);
            num /= 10;
        }
        Collections.reverse(res);
        return res;

    }

    //O(m)
    private int toInt(List<Integer> list){
        int res = 0;
        for(int i = 0; i < list.size(); i++){
            res = res * 10 + list.get(i);
        }
        return res;

    }

    //way1 - better coding style: 总结brute force更好的写法 todo

    //way2: greedy todo


}
