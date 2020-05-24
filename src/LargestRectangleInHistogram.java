/*
*
*Laicode
*198. Largest Rectangle In Histogram
Hard
Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.

Assumptions

The given array is not null or empty
Examples

{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)


*
*
* */

import java.util.Deque;
import java.util.LinkedList;
//太巧秒了，需要再做一遍
//T: O(n)
//S: O(n)
public class LargestRectangleInHistogram {
    public int largest(int[] array) {
    /*
    linear scan each index. and add it to the stack. right boarder is when the current value is equal to or less than the previous index's value
    once see a right board occurs, looking back check if previous value >= current value.
    if is, calculate area and update globle max.
    if not, stop and continue linear scan from current index
    */

        if(array == null || array.length == 0){
            return 0;
        }

        int area = 0;
        //stack store index of left bound
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i <= array.length; i++){
            int curValue = i == array.length ? 0 : array[i];
            //只要有下滑或者是相等就回头看，消灭可以灭掉的左边界，留下来的就是消灭不掉的左边界
            while(!stack.isEmpty() && curValue <= array[stack.peekFirst()]){
                int value = array[stack.pollFirst()];
                int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
                area = Math.max(area, (i - left) * value);
            }
            stack.offerFirst(i);
        }

        return area;
    }
}
