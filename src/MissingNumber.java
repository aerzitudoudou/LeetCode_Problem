/*
* leetcode
* 268. Missing Number
Easy
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*
* */


import java.util.Arrays;

public class MissingNumber {
    //way1: 暴力
    //T: n(nlogn)
    public int missingNumber(int[] nums) {
        if(nums == null){
            return -1;
        }
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i + 1] != nums[i] + 1){
                return nums[i] + 1;
            }
        }
        if(nums[0] != 0){
            return 0;
        }
        return nums[nums.length - 1] + 1;

    }

    //way2: 等差数列和公式
    //T: O(n)
    public int missingNumber1(int[] nums) {
        //corner case:
        if(nums == null){
            return -1;
        }
        int len = nums.length;
        //calculate sum
        int sum = 0;
        for(int i = 0; i < len; i++){
            sum += nums[i];
        }
        return (len * (1 + len)) / 2 - sum;
    }


    /*way3: bit manipulation

    xor 满足
    1. 交换律： a ^ b = b ^ a
    2. 结合律： a ^ (b ^ c) = (a ^ b) ^ c
    3. 0 是单元： 0 ^ a = a

    正常序列: 0, 1, 2, 3, 4
    nums:    0, 1, 3, 4

    正常序列每一个数[0...4]xor ^ nums 每一个数[0...4 but without 2]
    = (0 ^ 1 ^ 2 ^ 3 ^ 4) ^ (0 ^ 1 ^ 3 ^ 4)
    = (0 ^ 0) ^ (1 ^ 1) ^ (2) ^ (3 ^ 3) ^ (4 ^ 4) --> 交换律， 结合律
    = 0 ^ 0 ^ 2 ^ 0 ^ 4 --> 0是单元
    = 2 --> missing number


    */
    public int missingNumber2(int[] nums) {
        if(nums == null){
            return -1;
        }
        int xor = 0;
        for(int i : nums){
            xor ^= i;
        }
        for(int i = 0; i <= nums.length; i++){
            xor ^= i;
        }
        return xor;
    }

    //way4
    //hashtable (array)
    public int missingNumber3(int[] nums) {
        if(nums == null){
            return -1;
        }
        boolean[] array = new boolean[nums.length + 1];
        for(int i = 0; i < nums.length; i++){
            array[nums[i]] = true;
        }
        for(int i = 0; i < array.length; i++){
            if(!array[i]){
                return i;
            }
        }
        return -1;
    }







}
