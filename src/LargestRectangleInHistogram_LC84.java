import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleInHistogram_LC84 {
    //!!!!sol1, from acwing, O(n) since every elem push once and pop once, O(n) https://www.acwing.com/video/1424/
    //单调栈： 找跟这个数最近的，且比它小的数（monotonic increasing stack） || 比它大的数（monotonic decreasing stack）
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < n; i++){
            //remember stack.peekFirst is the index!!!!! not the value!!!!!
            while(!stack.isEmpty() && heights[stack.peekFirst()] >= heights[i]) stack.pollFirst();
            if(stack.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = stack.peekFirst();
            }

            stack.offerFirst(i);
        }

        stack.clear();
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && heights[stack.peekFirst()] >= heights[i]) stack.pollFirst();
            if(stack.isEmpty()) {
                right[i] = n;
            }else{
                right[i] = stack.peekFirst();
            }
            stack.offerFirst(i);

        }

        int res = 0;
        for(int i = 0; i < n; i++){
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;

    }


    //sol2 from laioffer
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


}
