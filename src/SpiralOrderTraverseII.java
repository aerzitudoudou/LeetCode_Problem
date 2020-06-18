/*
*Laicode
*122. Spiral Order Traverse II
Medium
Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of M * N where M, N >= 0
Examples

{ {1,  2,  3,  4},

  {5,  6,  7,  8},

  {9, 10, 11, 12} }

the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
*
*--矩阵的几何题。上下左右边界定出来绕着圈走就行了 举几个例子
*
*
*
* */

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseII {
    public List<Integer> spiral(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0] == null){
            return res;
        }
        int m = matrix.length; //row number
        int n = matrix[0].length; //column number

        int left = 0, right = n - 1, up = 0, down = m - 1;

        while(left < right && up < down){
            for(int i = left; i <= right; i++){
                res.add(matrix[up][i]);
            }
            for(int i = up + 1; i <= down - 1; i++){
                res.add(matrix[i][right]);
            }
            for(int i = right; i >= left; i--){
                res.add(matrix[down][i]);
            }
            for(int i = down - 1; i >= up + 1; i--){
                res.add(matrix[i][left]);
            }
            //第一次做错！！这里记得加减
            left++;
            right--;
            up++;
            down--;
        }

        //只要left,right 有交叉，或者up, down 有交叉 那么矩阵一定是遍历完了的
        if(left > right || up > down){
            return res;
        }

        //还有的情况就是left == right && up < down , 或者up == down && left < right
        if(left == right){//一个纵列
            for(int i = up; i <= down; i++){
                res.add(matrix[i][left]);
            }
        }else{//一个横列
            for(int i = left; i <= right; i++){
                res.add(matrix[up][i]);
            }
        }
        return res;


    }

}
