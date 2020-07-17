/*
*laicode
*69. Missing Number II
Easy
Given an integer array of size N - 1 sorted by ascending order, containing all the numbers from 1 to N except one, find the missing number.

Assumptions

The given array is not null, and N >= 1
Examples

A = {1, 2, 4}, the missing number is 3
A = {1, 2, 3}, the missing number is 4
A = {}, the missing number is 1
*
*
*
* */


public class MissingNumberII {
    //my way:

    public int missing(int[] array) {
        int left = 0, right = array.length - 1;
        //binary search
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            int midValue = (array[left] + array[right]) / 2;
            if(array[mid] == midValue){
                if(array[mid] - 2 == array[mid - 1]){
                    return array[mid] - 1;
                }else if(array[mid] + 2 == array[mid + 1]){
                    return array[mid] + 1;
                }else{
                    left = right - 1;
                }
            }else if(array[mid] < midValue){
                left = mid;
            }else{
                right = mid;
            }
        }
        //post processing
        if(left + 1 > right){
            return 1;
        }else if(array[left] + 2 == array[right]){
            return array[left] + 1;
        }else if(array[0] != 1){
            return 1;
        }else {
            return array[right] + 1;
        }
    }

    //TODO: 总结更好的写法？
}
