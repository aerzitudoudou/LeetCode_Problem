import java.util.Deque;
import java.util.LinkedList;

public class MaximalRectangle_LC85 {
    //sol1, from acwing, https://www.acwing.com/video/1424/ O(m * n), O(m * n)
    //是唯一底边求最大矩形面积lc 84 的变种，这次的底边不确定，需要先把不同row 上的柱子最高长度先求出来，有dp的思想在里面，从row num = 0开始， height[][]: 当前值位0，h = 0, 不为0， h = h[i - 1][j] + 1
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        int[][] height = new int[m][n]; //what is the building height with bottom = each row
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0){
                    height[i][j] = matrix[i][j] == '0' ? 0 : height[i - 1][j] + 1;
                }else{
                    height[i][j] = matrix[i][j] - '0';
                }
            }
        }

        for(int i = 0; i < m; i++){
            res = Math.max(res, largestRectangleArea(height[i]));
        }
        return res;

    }


    //lc84
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
}
