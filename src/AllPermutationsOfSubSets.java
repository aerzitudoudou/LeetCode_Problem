/**
 *
 * Laicode
 *643. All Permutations of Subsets
 * Medium
 * Given a string with no duplicate characters, return a list with all permutations of the string and all its subsets.
 *
 *
 *
 * Examples
 *
 * Set = “abc”, all permutations are [“”, “a”, “ab”, “abc”, “ac”, “acb”, “b”, “ba”, “bac”, “bc”, “bca”, “c”, “cb”, “cba”, “ca”, “cab”].
 *
 * Set = “”, all permutations are [“”].
 *
 * Set = null, all permutations are [].
 *
 *
 *
 *
 *
 *
 *
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//T: N！ * N
//S: O(N)

public class AllPermutationsOfSubSets {
    public List<String> allPermutationsOfSubsets(String set) {
        List<String> res = new ArrayList<>();
        if(set == null){
            return res;
        }
        res.add("");
        char[] ary = set.toCharArray();
        dfs(res, ary, 0);
        return res;

    }


    private void dfs(List<String> res, char[] ary, int index){
        //就像是树的base case是null
        //dfs graph 的base case 的最后一层也是什么都不做的， 也就是index = ary.length 时候，假设length = 3， recursion tree事情已经在第0 层， 第一层，还有第二层做完了。 第三层什么都不干，就是触底返回的作用

        if(index == ary.length){
            return;
        }
        for(int i = index; i < ary.length; i++){
            swap(ary, index, i);
            //char[] 转化成string 用String.valueOf(charAry[])
            //char array 变成String的3种写法
            /*
            * String.valueOf(char[] value, int offset, int count)
            * String.valueOf(char[] value)
            * new String(char[] value, int offset, int count);
            * new String(char[] value)
            *
            *
            * */
            res.add(String.valueOf(Arrays.copyOfRange(ary, 0, index +1)));
            dfs(res, ary, index + 1);
            swap(ary, index, i);
        }
    }

    private void swap(char[] ary, int i, int j){
        char tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }



}
