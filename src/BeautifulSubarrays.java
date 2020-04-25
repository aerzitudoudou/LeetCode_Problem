/*

lintcode 1258
* Description

A beautiful subarray is defined as an array of any length having a specific number of odd elements. Given an array of integers and a number of odd elements that constitutes beauty, create as many distinct beautiful subarrays as possible. Distinct means the arrays do not share identical starting and ending indices, though they may share one of the two. Return the number of beautiful subarrays.

the length of nums is within range: [1, 100000]
numOdds is with range: [1, 100000]
Guarantee the type of result is int.
Have you met this question in a real interview?
Example
Example 1:
Input:
nums = [1, 2, 3, 4, 5]
numOdds = 2
Output: 4
Explanation: There are 4 subarrays only have two odds. such as: [1, 2, 3], [1, 2, 3, 4], [2, 3, 4, 5], [3, 4, 5].
Example 2:
Input:
nums = [2, 4, 6, 8]
numOdds = 1
Output: 0
Explanation: No odd number in array
*
* */

public class BeautifulSubarrays {
    //my way
    public int BeautifulSubarrays(int[] nums, int numOdds) {
        int res = 0;
        int count = 0;
        int j = 0;
        for(int i = 0; i < nums.length; i++){

            for(j = i ; j < nums.length; j++){

                if(count > numOdds){
                    break;
                }
                if(isOdd(nums[j])){
                    count++;
                }
                if(count == numOdds){
                    res++;
                }

            }
            count = 0;

        }
        return res;
    }

    private boolean isOdd(int t){
        return t % 2 == 1;
    }

    //other's way : 中心开花
    //TODO: 总结
}
