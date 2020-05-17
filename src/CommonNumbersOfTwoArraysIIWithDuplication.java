/*
*
*
* laicode
* 651. Common Numbers Of Two Arrays II(Array version)
Easy
Find all numbers that appear in both of two unsorted arrays.

Assumptions

Both of the two arrays are not null.
In any of the two arrays, there could be duplicate numbers.
Examples

A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B)

*
*
*
*
* */

import java.util.*;

public class CommonNumbersOfTwoArraysIIWithDuplication {
    //sort then find
    public List<Integer> common0(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        List<Integer> res = new ArrayList<>();
        //remove pointer whose value is smaller
        int i = 0, j = 0;
        while (i < A.length && j < B.length){
            if(A[i] == B[j]){
                res.add(A[i]);
                i++;
                j++;
            }else if(A[i] < B[j]){
                i++;
            }else{
                j++;
            }
        }
        return res;
    }


    public List<Integer> common(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < A.length; i++){
            //注意这里初始化是1，只要存在，起始个数就是1
            Integer value = map.putIfAbsent(A[i], 1);
            if(value != null){
                map.put(A[i], value + 1);
            }
        }
        for(int i = 0; i < B.length; i++){
            if(map.containsKey(B[i]) && map.get(B[i]) > 0){
                res.add(B[i]);
                map.put(B[i], map.get(B[i]) - 1);
            }
        }

        Collections.sort(res);
        return res;
    }
}
