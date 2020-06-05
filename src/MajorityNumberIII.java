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
*--Integer class cache the Integer object with value from -128 to 127, so every time an Integer object within this range is needed,
* it will always return the corresponding object
*-amortized 时间复杂度分析：
*
*
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//思路和之前的majority number 一样都是打擂台
//

public class MajorityNumberIII {
    public List<Integer> majority(int[] array, int k) {
        //a是 > 1/k * L 的任意一个数的个数  a > L/k
        //                                a - 1 > (L - K) / k
        //擂台上站k - 1个人，上来一个，不一样，这个和擂台上的k - 1的各自团灭（count--）
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> numberToRemove = new ArrayList<>();
        //amortized 时间复杂度分析：看瓶颈，看哪些行运行的次数最多
        //# of step 1 || step 2 <= # of step2 & step 3(把每个扔上去打擂台，最多运行n 次即O(n))
        //bottleneck 时间复杂度 <= O(n) 所以时间复杂度是O(n) 空间复杂度是O(k)
        for(int i = 0; i < array.length; i++){
            //把所有count = 0 的数去掉

            //map里数字个数 < k - 1, 1. 进来的数和某key一致， 此key counter++ 2. 不一致，put in map, counter = 1
            if(map.containsKey(array[i])){//T: O(1)
                int count = map.get(array[i]);
                count++; //嗷嗷嗷愚蠢的错误！！！！！ 这里不能把count++直接放到map去！！！！！如果放进去，就会把count的值先付给map, 然后再count++
                map.put(array[i], count); //step 2
            }else{
                if(map.size() < k - 1){
                    map.put(array[i], 1); //step 3
                }else{
                    for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                        int count = entry.getValue();
                        count--;//此处不能直接删除!!!!会有Exception 一边loop的时候不能一边改变这个map的size
                        if(count == 0){
                            numberToRemove.add(entry.getKey());
                        }
                        map.put(entry.getKey(), count); //运行bottleneck： step 4
                    }
                    for(int j = 0; j < numberToRemove.size(); j++){
                        map.remove(numberToRemove.get(j)); //运行bottleneck： step 1
                    }
                    numberToRemove.clear(); //clear 的用法要清楚

                }
            }

            //map 里数字个数 == k - 1, 1. 进来的数和某key 一致， 此key counter++ 2.不一致，团灭，台上每个数counter--,如果count减到0了，直接删掉这个entry

        }
        /*
        *
        *
        *O(1000) + O(0)
        *O(700) + O(< 700)
        *
        * [O(1000) + O(0) + O(700) + O(< 700)] / 2 = 2 * O(1000) + 2 * O(1000) / 2 = 2 O(1000) = O(n)
        *
        * */


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
    /*
    * abcdefg push
    * stack 1
    * stack 2 edcba
    *
    * 1000
    *
    * 1000 +  O(999) / 1000 <= O(1)
    * 1000 + 2 * O(499)/ 1000 <= O(1)

     *
    *
    * */


    //lint code 的signature, 给的是一个list而非数组

    public int majorityNumber(List<Integer> nums, int k) {
        //a是 > 1/k * L 的任意一个数的个数  a > L/k
        //                                a - 1 > (L - K) / k
        //擂台上站k - 1个人，上来一个，不一样，这个和擂台上的k - 1的各自团灭（count--）
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> numberToRemove = new ArrayList<>();
        for(int i = 0; i < nums.size(); i++){
            //把所有count = 0 的数去掉
            for(int j = 0; j < numberToRemove.size(); j++){
                map.remove(numberToRemove.get(j));
            }
            numberToRemove.clear(); //clear 的用法要清楚
            //map里数字个数 < k - 1, 1. 进来的数和某key一致， 此key counter++ 2. 不一致，put in map, counter = 1
            if(map.containsKey(nums.get(i))){
                int count = map.get(nums.get(i));
                count++; //嗷嗷嗷愚蠢的错误！！！！！ 这里不能把count++直接放到map去！！！！！如果放进去，就会把count的值先付给map, 然后再count++
                map.put(nums.get(i), count);
            }else{
                if(map.size() < k - 1){
                    map.put(nums.get(i), 1);
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
            for(int i = 0; i < nums.size(); i++){
                if(nums.get(i).equals(entry.getKey())){ //第一次这里做错，object比较应该用equals
                    count++;
                }
            }
            if(count > nums.size() / k){
                res.add(entry.getKey());
            }
        }
        return res.get(0);

    }
}

