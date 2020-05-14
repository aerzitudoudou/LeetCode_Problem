/*
*laicode
* 263. Two Subsets With Min Difference
Medium
Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.

Return the minimum difference(absolute value).

Assumptions:

The given integer array is not null and it has length of >= 2.
Examples:

{1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0

*
*
*-注意题目要求是需要子集长度为n/2. 奇数e.g. 只能是{1,2,3}（4,5） 不能{1,2,3,4}{5} 偶数最后结果只能是对半分
*-
*
*
*
* */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//recursion tree 等同于all subset
//array length = N
//T: 2^N * N
//S: N
public class TwoSubsetsWithMinDifference {

    public int minDifference(int[] array) {
        Set<Integer> res = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, array, 0, list);
        int sum = 0;
        for(int i = 0; i < array.length; i++){
            sum += array[i];
        }
        int globalMin = Integer.MAX_VALUE;
        for(int a : res){
            globalMin = Math.min(globalMin, Math.abs(a - (sum - a)));
        }
        return globalMin;

    }

    private void dfs(Set<Integer> res, int[] array, int index, List<Integer> list){
        if(index == array.length){
            return;
        }
        if(list.size() == array.length / 2){
            int sum = 0;
            for(int i = 0; i < list.size(); i++){
                sum += list.get(i);
            }
            res.add(sum);
            return;
        }
        //第一次做错了：注意不能直接把list.add(array[index])放在62行内部，原因是Collection interface的remove 和add返回boolean,而非list, 不同于stringbuilder append之后还会返回stringbuilder
        list.add(array[index]);
        dfs(res, array, index + 1, list);
        list.remove(list.size() - 1);

        dfs(res, array, index + 1, list);
    }
}
