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

    //way2:
    /*
    T: O(n)
    S: O(1)
    盯住两边到目前为止的最短板，谁小就移动谁：
    if(leftMax 小就移动i : i++)
    else j--

    init:
    leftMax = array[0]
    rightMax = array[length - 1]

    证明为什么leftMax < rightMax 小就可以safely 的移动i, 并且water = leftMax - array[i + 1]

    proof by induction: from i to i + 1
    case 1: if A[i + 1] <= leftMax leftMax remain un-updated. left boarder is still valid and does not change
            so water at i + 1 = leftMax - A[i + 1]
    case 2: if A[i + 1] > leftMax leftMax is updated to A[i + 1]
            so water at i + 1 = newLeftMax - A[i + 1] = 0

    同理可证rightMax >= leftMax j can safely be moved from j to j - 1, 并且 water = rightMax - array[j - 1]
    case 1: if A[j - 1] <= rightMax rightMax remain un-updated. right boarder is still valid and does not change
            so water at j - 1 = rightMax - A[j - 1]
    case 2: if A[j - 1] > rightMax rightMax is updated to A[j - 1].
            so water at j - 1 = newRightMax - A[j - 1] = A[j - 1] - A[j - 1] = 0

    * */
    public int maxTrapped2(int[] array) {
        int res = 0;
        if(array == null || array.length == 0){
            return res;
        }
        int i = 0, j = array.length - 1, leftMax = array[i], rightMax = array[j];
        while(i <= j){
            //can safely move i
            if(leftMax < rightMax){
                //update leftMax
                leftMax = Math.max(array[i], leftMax);
                res += leftMax - array[i];
                i++;
                //calculate water
            }else{//can safely move j
                rightMax = Math.max(array[j], rightMax);
                res += rightMax - array[j];
                j--;
            }
        }
        return res;

    }

}
