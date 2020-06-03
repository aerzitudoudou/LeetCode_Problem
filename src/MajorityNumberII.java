/*
*
* laicode
* 207. Majority Number II
Medium
Given an integer array of length L, find all numbers that occur more than 1/3 * L times if any exist.

Assumptions

The given array is not null
Examples

A = {1, 2, 1, 2, 1}, return [1, 2]
A = {1, 2, 1, 2, 3, 3, 1}, return [1]
A = {1, 2, 2, 3, 1, 3}, return []

*--打擂台， a > n / 3 ===> a - 1 > (n - 3) / 3
*
* 台上站两个，第三个如果和台上两个不一样，一灭二， 一样的话更新对应的counter
*
* */

import java.util.ArrayList;
import java.util.List;

public class MajorityNumberII {
    //may way
    //T: O(n)
    //S: O(1)
    public List<Integer> majority(int[] array) {
        List<Integer> res = new ArrayList<>();
        Integer can1 = null, can2 = null, count1 = 0, count2 = 0;
        for(int i = 0; i < array.length; i++){
            if(count1 == 0){
                can1 = array[i];
                count1 = 1;
            }else if(count2 == 0 && array[i] != can1){ //第一次做错，擂台上要保证不能出现相同的数
                can2 = array[i];
                count2 = 1;
            }else if(array[i] != can1 && array[i] != can2){//火并
                count1--;
                count2--;
            }else{
                if(can1 != null && array[i] == can1){
                    count1++;
                }

                if(can2 != null && array[i] == can2){ //这里需要check candidate 是不是null e.g. 111433 如果不check则判断==时， 会de-reference null value
                    count2++;
                }
            }
        }

        //需要做post processing e.g. 111433 最后会留 1 和3 在擂台上。 3不符合>k/3  需要Post processing 去掉
        if(count1 != 0){
            int check = 0;
            for(int i = 0; i <array.length; i++){
                if(array[i] == can1){
                    check++;
                }
            }
            if(check > array.length / 3){
                res.add(can1);
            }
        }
        if(count2 != 0){
            int check = 0;
            for(int i = 0; i <array.length; i++){
                if(array[i] == can2){
                    check++;
                }
            }
            if(check > array.length / 3){
                res.add(can2);
            }
        }

        return res;
    }
}
