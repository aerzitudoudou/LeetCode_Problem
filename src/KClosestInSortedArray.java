/*

Laicode
*19. K Closest In Sorted Array
Medium
Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A. If there is a tie, the smaller elements are always preferred.

Assumptions

A is not null
K is guranteed to be >= 0 and K is guranteed to be <= A.length
Return

A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T.
Examples

A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}
*    2  1  3  5
*
*/


public class KClosestInSortedArray {
    //way 0: original my way on Sep 2019
    public int[] kClosest(int[] array, int target, int k) {
        if(array == null || array.length == 0){
            return array;
        }
        if(k == 0){
            return new int[0];
        }
        int right = findSmallestLargerEqualIndex(array, target), left = right - 1;
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            if(left < 0 || right < array.length && target - array[left] >= array[right] - target){
                res[i] = array[right++];
            }else{
                res[i] = array[left--];
            }
        }

        return res;

    }

    //比target大的数里最小的数
    private int findSmallestLargerEqualIndex(int[] array, int target){
        int l = 0, r = array.length - 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2;
            if(array[mid] == target){
                r = mid;
            }else if(array[mid] > target){
                r = mid;
            }else{
                l = mid;
            }
        }
        if(array[l] >= target){
            return l;
        }
        if(array[r] >= target){
            return r;
        }
        return array.length;
    }

    //way 1: find closer element(smaller if tie), then extend to left and right to find the left k-1 smallest(中心开花) my way on May 29th 2020
    //T: logn + k  n is length of array
    //空间O(1)
    public int[] kClosest1(int[] array, int target, int k) {
        int[] res = new int[k];
        if(array.length == 0 || k == 0){
            return res;
        }
        //index 是小于或等于target 的最大值
        int left = helper(array, target, 0, array.length - 1), right = left + 1;

        //中心开花
        for(int i = 0; i < k; i++){
            //什么时候在res加左边的值，且移动左指针
            if(right == array.length || (left >= 0 && target - array[left] <= array[right] - target)){
                res[i] = array[left];
                left--;
            }else{
                res[i] = array[right];
                right++;
            }
        }
        return res;


    }

    //比target小的数里最大的数
    private int helper(int[] array, int target, int left, int right){
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            if(array[mid] == target) {
                return mid;
            }else if(array[mid] < target){
                //第二次做错了，这个不能是mid - 1, e.g. array 里面只有3个值 1， 2， 3  target = 10, mid = 1, A[1] = 2 < 10, left = mid - 1 = 0, 就会无限循环下去
                left = mid;
            }else{
                right = mid;
            }
        }

        if(target - array[left] <= array[right] - target){
            return left;
        }
        return right;

    }

    //way3
    //T: O(logk)
    //S: O(logk)
    //TODO: 把logk在k范围内Run Binary search 的方法写一下

}
