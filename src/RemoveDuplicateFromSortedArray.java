/*
* lintcode
* 100. Remove Duplicates from Sorted Array
Given a sorted array, 'remove' the duplicates in place such that each element appear only once and return the 'new' length.

Do not allocate extra space for another array, you must do this in place with constant memory.

Example
Example 1:

Input:  []
Output: 0
Example 2:

Input:  [1,1,2]
Output: 2
Explanation:  uniqued array: [1,2]
*
*
* */

public class RemoveDuplicateFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int s = 0;
        int f = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[f] != nums[s]){
                s++;
                swap(nums, f, s);
            }
            f++;
        }
        return s + 1;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
