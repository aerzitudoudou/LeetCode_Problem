/*
*
*
*laicode
* 208. Majority Number III
Hard
Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

Assumptions

The given array is not null or empty
K >= 2
Examples

A = {1, 2, 1, 2, 1}, K = 3, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, K = 4, return [1, 2, 3]
A = {2, 1}, K = 2, return []

*
*
*
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//思路和之前的majority number 一样都是打擂台
//TODO: 时间复杂度

public class MajorityNumberIII {
    public List<Integer> majority(int[] array, int k) {
        //a是 > 1/k * L 的任意一个数的个数  a > L/k
        //                                a - 1 > (L - K) / k
        //擂台上站k - 1个人，上来一个，不一样，这个和擂台上的k - 1的各自团灭（count--）
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> numberToRemove = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            //把所有count = 0 的数去掉
            for(int j = 0; j < numberToRemove.size(); j++){
                map.remove(numberToRemove.get(j));
            }
            numberToRemove.clear(); //clear 的用法要清楚
            //map里数字个数 < k - 1, 1. 进来的数和某key一致， 此key counter++ 2. 不一致，put in map, counter = 1
            if(map.containsKey(array[i])){
                int count = map.get(array[i]);
                count++; //嗷嗷嗷愚蠢的错误！！！！！ 这里不能把count++直接放到map去！！！！！如果放进去，就会把count的值先付给map, 然后再count++
                map.put(array[i], count);
            }else{
                if(map.size() < k - 1){
                    map.put(array[i], 1);
                }else{
                    for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                        int count = entry.getValue();
                        count--;//此处不能直接删除!!!!会有Exception 一边loop的时候不能一边改变这个map的size
                        if(count == 0){
                            numberToRemove.add(entry.getKey());
                        }
                        map.put(entry.getKey(), count);
                    }
                }
            }

            //map 里数字个数 == k - 1, 1. 进来的数和某key 一致， 此key counter++ 2.不一致，团灭，台上每个数counter--,如果count减到0了，直接删掉这个entry

        }

        //post-processing: map里的每一个数在array里过一遍，check出现个数是否 < L/K

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int count = 0;
            for(int i = 0; i < array.length; i++){
                if(array[i] == entry.getKey()){
                    count++;
                }
            }
            if(count > array.length / k){
                res.add(entry.getKey());
            }
        }
        return res;

    }
}

