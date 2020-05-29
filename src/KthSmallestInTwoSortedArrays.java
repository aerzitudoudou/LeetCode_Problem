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
*
*需要注意的点：
*
* 1. 比较a和b数组index 为k/2 - 1的大小，谁小删除谁(用反证法):
*
* 证明condtion: A[k /2 - 1] <= B[k / 2 - 1], 搜索范围减少:A[0, k / 2 - 1] 闭区间内所有数可以被删除,
* i.e 证明A 的搜索范围可以由[0, A.length] 缩小至[k / 2, A.length] i.e. i > 3  ===> Binary search!!!
*
* i: [0, i - 1] belongs to kth smallest
* j: [0, j - 1] belongs to kth smallest
* index   0   1   2   3   4   5   6   7   8
* can be deleted:
*        |------------|
*                 i1  i
* A       x   x   x   X   x   x   x   x   x
*                     j           j1
* B       y   y   y   Y   y   y   y   y   y
*
* if k is 8 then i and j can be one of the following:
*
*    i     j
*    0     8
*    1     7
*    2     6
*    3     5
*    4     4
*    5     3
*    6     2
*    7     1
*    8     0
*
* proof by contradiction:
* if k = 8 , k/2 - 1 = 3

* Assume there is an i such that i <= 3 ==> if i <= 3 then j >= 5
* then any element before j1 should belong to the kth smallest, same for any element before i1
* since sorted array for both A and B, A[i1] > A[j], as i1 is not belonging to the kth smallest, also A[i1] <= A[i] as A is sorted array
* then A[i] >= A[i1] > A[j] ==> A[i] > A[j] which contradict with the condition A[i] <= A[j]
*
* therefore i[0, k /2 - 1] can be safely deleted ==> number of the first k / 2 elements get deleted in each round
*

* 2.
* A: 12345
* B: 45678 的前k小可以看做等价于
*
* A: 12345MaxInt MaxInt MaxInt MaxInt.....
* B: 45678MaxInt MaxInt MaxInt MaxInt.....
*
* 这样A[k / 2 - 1]出界时候可以用MaxInt代替用来做判断的A[k / 2 - 1]值，继续删除另一个数组的前k / 2 个值
*
*
* 3. recursion时候注意k是相对值，需要在两个数组起始值基础上 删掉k / 2
*/

//T: logk
//S: logk
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
            //k - k / 2 不能用k/2直接代替   e.g k = 3 k - k/2 = 2   but k/2 = 1
            return kth(a, b, i + k / 2, j, k - k / 2);
        }else{
            return kth(a, b, i, j + k / 2, k - k / 2);
        }


    }
}
