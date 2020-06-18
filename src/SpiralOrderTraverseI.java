/*
*
*LaiCode
* 121. Spiral Order Traverse I
Medium
Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

The 2D array is not null and has size of N * N where N >= 0
Examples

{ {1,  2,  3},

  {4,  5,  6},

  {7,  8,  9} }

the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]


*--几何问题
*
*
* */

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseI {
    //way1:recursion: assume matrix is M * M 做法剥洋葱。 用offset代表在对角线上移动的距离
    //T: O(M * M)  S:O(diagonal)

    public List<Integer> spiral(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        //(matrix, offsite, size, res)
        helper(matrix, 0, matrix.length, res);
        return res;
    }

    private void helper(int[][] matrix, int offsite, int size, List<Integer> res){
        if(size == 0){
            return;
        }
        if(size == 1){
            res.add(matrix[offsite][offsite]);
            return;
        }
        for(int i = 0; i < size - 1; i++){
            res.add(matrix[offsite][offsite + i]);
        }
        for(int i = 0; i < size - 1; i++){
            res.add(matrix[offsite + i][offsite + size - 1]);
        }
        for(int i = size - 1; i >= 1; i--){
            res.add(matrix[offsite + size - 1][offsite + i]);
        }
        for(int i = size - 1; i >= 1; i--){
            res.add(matrix[offsite + i][offsite]);
        }
        helper(matrix, offsite + 1, size - 2, res);
    }


    //way2: iterative： 定下左右上下边界loop就行了。这里因为是m * m的矩阵，所以左右上下可以只用两个variable代表
    public List<Integer> spiral2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        if(matrix == null || matrix.length == 0 || matrix[0] == null){
            return res;
        }
        //row and column number
        int m = matrix.length;

        int start = 0, end = m - 1;
        while(start < end){
            for(int i = start; i <= end; i++){
                res.add(matrix[start][i]);
            }
            for(int i = start + 1; i <= end - 1; i++){
                res.add(matrix[i][end]);
            }
            for(int i = end; i >= start; i--){
                res.add(matrix[end][i]);
            }
            for(int i = end - 1; i >= start + 1; i--){
                res.add(matrix[i][start]);
            }
            start++;
            end--;
        }
        if(start == end){
            res.add(matrix[start][start]);
        }
        return res;
    }






}
