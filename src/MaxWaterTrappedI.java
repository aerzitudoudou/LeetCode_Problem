/*
*Laicode
*199. Max Water Trapped I
Medium
Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.

Assumptions

The given array is not null
Examples

{ 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index 1, 1 unit of water can be trapped and index 3, 1 unit of water can be trapped)
*
*
*
*
* */

public class MaxWaterTrappedI {
    //way 1: pre sum
    //T: O(n)
    //S: O(n)
    //presum: pre left max and pre right max
    public int maxTrapped(int[] array) {
        int res = 0;
        if(array == null || array.length == 0){
            return res;
        }
        int[] left = new int[array.length], right = new int[array.length];
        left[0] = array[0];
        right[array.length - 1] = array[array.length - 1];
        for(int i = 1; i < array.length; i++){
            left[i] = Math.max(array[i], left[i - 1]);
        }
        for(int i = array.length - 2; i >= 0; i--){
            right[i] = Math.max(array[i], right[i + 1]);
        }
        for(int i = 0; i < array.length; i++){
            int tmp = Math.min(left[i], right[i]);
            if(array[i] < tmp){
                res += (tmp - array[i]);
            }
        }
        return res;
    }
}
