/*
*641. All Subsets II of Size K
Medium
Given a set of characters represented by a String, return a list containing all subsets of the characters whose size is K. Notice that each subset returned will be sorted for deduplication.



Assumptions

There could be duplicate characters in the original set.

​

Examples

Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].

Set = "abb", K = 2, all the subsets are [“ab”, “bb”].

Set = "abab", K = 2, all the subsets are [“aa”, “ab”, “bb”].

Set = "", K = 0, all the subsets are [""].

Set = "", K = 1, all the subsets are [].

Set = null, K = 0, all the subsets are [].
*
*
* -就是all subsetII 去重版的变体，base case 1. index == length 处停， 2.遇到当前结果= k也停
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//T:2^(set.length) * k
//O(set.length)
public class AllSubsetsIIOfSizeK_Lai640 {
    public List<String> subSetsIIOfSizeK(String set, int k) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] array = set.toCharArray();
        Arrays.sort(array);
        dfs(sb, res, 0, array, k);
        return res;
    }

    private void dfs(StringBuilder sb, List<String> res, int index, char[] array, int k){
        if(sb.length() == k){
            res.add(sb.toString());
            return;
        }
        if(index == array.length){
            return;
        }
        //加
        char c = array[index];
        dfs(sb.append(c), res, index + 1, array, k);
        sb.deleteCharAt(sb.length() - 1);

        //不加： 当前层的char和下一层的char一样，什么都不做，知道找到一个index 使得当前层 ！= 下一层，再recursively 调用dfs
        while((index + 1 < array.length) && array[index] == array[index + 1]){
            index++;
        }
        //出来时候index 已经是最后一位(length - 1)或者是下一个不重复的字母
        dfs(sb, res, index + 1, array, k);
    }

}
