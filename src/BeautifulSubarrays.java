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

import java.util.ArrayList;
import java.util.List;

public class BeautifulSubarrays {
    //my way
    //T: (n^2) s:O(1)
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

    //other's way : 中心开花 T: O（）
    //抽象出奇数的index并加上边界用来计算n个奇数定义出的interval往左往右可形成数组的个数  最终的数组的个数 = total of (每一个interval向左可形成的数组的个数 * 这个interval向右可形成的数组的个数）
    //way2
    //                                       e.g. nums: [7, 8, 9, 10, 11]
    //                                     num's index: [0, 1, 2,  3,  4]
    //                                                   odd   odd    odd
    //抽象出奇数Index 数组：                              [0,    2,      4]
    //+左边界: -1 右边界 = nums.size = 5   tmp      [-1,  0,     2,     4,   5]
    //                                                   |inter 1|
    //                                             --a---         --b---
    //init                                               i = 1
    //计算a 段（左边可形成数组个数）*b 段             a =  tmp[i] - tmp[i-1]   b = tmp[i + k] - tmp[i]   k is the defined number of odds
    public int BeautifulSubarrays2(int[] nums, int numOdds) {
        int res = 0;
        //build tmp
        List<Integer> tmp = new ArrayList<>();
        tmp.add(-1);
        for(int i = 0; i < nums.length; i++){
            if(isOdd(nums[i])){
                tmp.add(i);
            }
        }
        tmp.add(nums.length);


        for(int i = 1; i + numOdds < tmp.size(); i++){
            //caculate a * b
            res += (tmp.get(i) - tmp.get(i - 1)) * (tmp.get(i + numOdds) - tmp.get(i + numOdds - 1));

        }
        return res;
    }


}
