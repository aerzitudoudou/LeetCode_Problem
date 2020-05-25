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
//太巧秒了，需要再做一遍 用到了单调栈算法
/*
* 单调栈性质总结：https://blog.csdn.net/liujian20150808/article/details/50752861
*
*
* 单调栈的性质：

1.单调栈里的元素具有单调性

2.元素加入栈前，会在栈顶端把破坏栈单调性的元素都删除

3.使用单调栈可以找到元素向左遍历第一个比他小的元素，也可以找到元素向左遍历第一个比他大的元素。

对于第三条性质的解释（最常用的性质）：

对于单调栈的第三条性质，你可能会产生疑问，为什么使用单调栈可以找到元素向左遍历第一个比他大的元素，

而不是最后一个比他大的元素呢？我们可以从单调栈中元素的单调性来解释这个问题，由于单调栈中的元素只能是单调递增或者是单调

递减的，所以我们可以分别讨论这两种情况（假设不存在两个相同的元素）：

1.当单调栈中的元素是单调递增的时候，根据上面我们从数组的角度阐述单调栈的性质的叙述，可以得出：

（1）.当a > b 时，则将元素a插入栈顶，新的栈顶则为a

（2）.当a < b 时，则将从当前栈顶位置向前查找（边查找，栈顶元素边出栈），直到找到第一个比a小的数，停止查找，将元素a

插入栈顶（在当前找到的数之后，即此时元素a找到了自己的“位置”）

2.当单调栈中的元素是单调递减的时候，则有：


（1）.当a < b 时，则将元素a插入栈顶，新的栈顶则为a

（2）.当a > b 时，则将从当前栈顶位置向前查找（边查找，栈顶元素边出栈），直到找到第一个比a大的数，停止查找，将元素a

插入栈顶（在当前找到的数之后，即此时元素a找到了自己的“位置”）


————————————————
版权声明：本文为CSDN博主「Adherer」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/liujian20150808/java/article/details/50752861
*
*
*
* */
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
