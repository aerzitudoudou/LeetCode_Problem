/*

from lintcode FB ladder:
* 64. Merge Sorted Array

Given two sorted integer arrays A and B, merge B into A as one sorted array.

Example
Example 1:

Input：[1, 2, 3] 3  [4,5]  2
Output：[1,2,3,4,5]
Explanation:
After merge, A will be filled as [1, 2, 3, 4, 5]
Example 2:

Input：[1,2,5] 3 [3,4] 2
Output：[1,2,3,4,5]
Explanation:
After merge, A will be filled as [1, 2, 3, 4, 5]
Notice
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
*
*
* */


public class MergeTwoSortedArrayFb {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        //一开始没有想出来。数组A后面是空的，所以可以谁大移谁，从后往前做
        int p1 = m - 1;
        int p2 = n - 1;
        int cur = m + n - 1;
        while(p1 >= 0 && p2 >= 0){
            A[cur--] = A[p1] > B[p2] ? A[p1--] : B[p2--];
        }
        while(p2 >= 0){
            A[cur--] = B[p2--];
        }
    }
}
