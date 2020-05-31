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
            if(array[mid] <= target){
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

    //way3： 类比laicode 202, 确定比target 小于等于的最大值以后，脑子里可以将原数组一分为二，他们和target的差的绝对值就是一个kth smallest in two sorted arrays
    //T: O(logn) + O(logk)
    //S: O(logk)
    //坐标的物理意义！！
    public int[] kClosest3(int[] array, int target, int k) {
        if(array == null || array.length == 0 || k == 0){
            return new int[0];
        }
        int[] res = new int[k];
        int left = findTargetIndex(array, target, 0, array.length - 1);
        int right = left + 1;
        helper(array, left, right, k, target, res, 0);
        return res;

    }
    //run logk 的binary search 确定每一步有哪 k/2可以被放入结果
    private void helper(int[] array, int left, int right, int k, int target, int[] res, int current){
        if(left < 0){
            for(int i = 0; i < k; i++){
                res[current++] = array[right++];
            }
            return;
        }
        if(right >= array.length){
            for(int i = 0; i < k; i++){
                res[current++] = array[left--];
            }
            return;
        }
        if(k == 1){
            res[current] = Math.abs(target - array[left]) <= Math.abs(target - array[right]) ? array[left] : array[right];
            return;
        }

        int index = k / 2 - 1;
        int leftIndex = left - index;
        int rightIndex = right + index;
        int val1 = leftIndex < 0 ? Integer.MAX_VALUE : Math.abs(target - array[leftIndex]);
        int val2 = rightIndex > array.length - 1 ? Integer.MAX_VALUE : Math.abs(target - array[rightIndex]);
        if(val1 <= val2){ //坑1！！！！： |array的值 - target|相等的情况下左边的值要先入res
            for(int i = 0; i < k / 2; i++){
                res[current++] = array[left--];
            }
            //坑2！！！！: 和Kth smallest in two sorted array 不同，这里的left index 就是for loop run 完之后的index, 不需要 leftIndex - k / 2. 另一个题需要减，因为不存在数组值copy，需要一次性定位到下一个left 的 index
            helper(array, left, right, k - k / 2, target, res, current);
        }else{
            for(int i = 0; i < k / 2; i++){
                res[current++] = array[right++];
            }
            helper(array, left, right, k - k / 2, target, res, current);
        }
    }
    //find largest number smaller or equal to target
    private int findTargetIndex(int[] array, int target, int left, int right){
        while(left + 1 < right){
            int mid = left + (right - left) / 2;
            //坑3！！！： 这里不能遇到target就直接返回。 return 值的物理意义是比target小于等于的所有值得最大值
            if(array[mid] <= target){
                left = mid;
            }else{
                right = mid;
            }
        }
        if(target >= array[right]){
            return right;
        }
        return left;
    }

}
