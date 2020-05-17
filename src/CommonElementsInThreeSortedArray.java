/*

Laicode
* 171. Common Elements In Three Sorted Array
Medium
Find all common elements in 3 sorted arrays.

Assumptions

The 3 given sorted arrays are not null
There could be duplicate elements in each of the arrays
Examples

A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]


*
*
*
*
*
* */

import java.util.*;

public class CommonElementsInThreeSortedArray {
    //iterative
    //T: O(m + n + min(m,n)log(min(m,n))) * 2
    //S: O(min(m, n))
    public List<Integer> common(int[] a, int[] b, int[] c) {
        Integer[] res = findCommons(toIntegerArray(a), toIntegerArray(b));
        res = findCommons(res, toIntegerArray(c));
        return Arrays.asList(res);
    }



    //T: O(m + n + min(m,n)log(min(m,n)))
    //S: O(min(m, n))
    private Integer[] findCommons(Integer[] A, Integer[] B){
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
        Integer[] array = new Integer[]{};
        return res.toArray(array);
    }

    //int[] 到Integer转化： 写个helper
    private Integer[] toIntegerArray(int[] a){
        Integer[] res = new Integer[a.length];
        for(int i = 0; i < a.length; i++){
            res[i] = Integer.valueOf(a[i]);
        }
        return res;
    }
}
