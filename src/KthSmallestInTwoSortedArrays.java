/*
* Laicode
* 202. Kth Smallest In Two Sorted Arrays
Hard
Given two sorted arrays of integers, find the Kth smallest number.

Assumptions

The two given arrays are not null and at least one of them is not empty

K >= 1, K <= total lengths of the two sorted arrays

Examples

A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.

A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
*
*
* */

public class KthSmallestInTwoSortedArrays {
    public int kth(int[] a, int[] b, int k) {
        if(a.length == 0){
            return b[k - 1];
        }
        if(b.length == 0){
            return a[k - 1];
        }
        //init: 尚且没有任何数可以被safely deleted, 有k个数等待被删除
        return kth(a, b, 0, 0, k);
    }

    //本轮i 与 j 的位置, where i 左边的数 和 j 左边的数都是被safely deleted 的数

    private int kth(int[] a, int[] b, int i, int j, int k){
        //base case: a数组的所有值都被删光了，第一次做错：起点并不一定是0
        if(i >= a.length){
            return b[j + k - 1];
        }
        //同理： b数组的所有值都被删光了
        if(j >= b.length){
            return a[i + k - 1];
        }
        //k无论偶数奇数，k / 2 , 剩下的接着除以2， 这样删下去，最终只会剩一个数的
        if(k == 1){
            return Math.min(a[i], b[j]);
        }

        //recursion rule: 谁在 k / 2 - 1 的index上value小，删谁的前k/2 个元素. 注意这里第一次做错： 起点并不是零而是从i ，或j ，开始
        int leftIndex = i + k / 2 - 1;
        int rightIndex = j + k / 2 - 1;
        //不够的数，Integer.MAX_VALUE来凑，因为找第k小结果不影响结果
        int valA = leftIndex >= a.length ? Integer.MAX_VALUE : a[leftIndex];
        int valB = rightIndex >= b.length ? Integer.MAX_VALUE : b[rightIndex];
        //binary search 符合valA < valB 条件（谁小）, A的前k/2删除（删谁）
        if(valA < valB){
            return kth(a, b, i + k / 2, j, k - k / 2);
        }else{
            return kth(a, b, i, j + k / 2, k - k / 2);
        }


    }
}
