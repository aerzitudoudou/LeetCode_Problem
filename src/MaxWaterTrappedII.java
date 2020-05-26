/*
*
*200. Max Water Trapped II
Hard
Given a non-negative integer 2D array representing the heights of bars in a matrix. Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. Each bar has 4 neighboring bars to the left, right, up and down side.

Assumptions

The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.
Examples

{ { 2, 3, 4, 2 },

  { 3, 1, 2, 3 },

  { 4, 3, 5, 4 } }

the amount of water can be trapped is 3,

at position (1, 1) there is 2 units of water trapped,

at position (1, 2) there is 1 unit of water trapped.
*
*
*
* -和max water trap water I 对比着来做
*
*                            一维                                                                                                                        二维
*
*
*
*
*                          数组的收尾边界                                                                                                             矩阵的四条边框
*init:              leftMax = array[0]                                                                                                           i = 0, i = row - 1
*                  rightMax = array[length - 1]                                                                                                  j = 0, j = col - 1
*
*properties:
*                  1. leftMax, rightMax谁小(i, or j)移谁(i + 1, or j + 1)                                                      1. minHeap expand 出来的元素对应"谁小", best first search generate 没有被visited 过的neighbors 对应"移谁"
*                  2. 维护leftMax 和 rightMax两个pre-max(单调递增)变量表示到目前为止最高的水位                                     2. 维护level变量(pre-max, 单调递增) 表示到目前为止最高的水位
*
*algo:
*                  水位线level = Math.min(LeftMax, rightMax)                                                                   水位线level = minHeap.poll
*                  对于下一个值（either i + 1 or j - 1） 每个点只遍历一遍                                                         对于所有的neighbor值(每个点只遍历一遍，要visited[][] 查重)
*                  res += Math.max(0, level - array[i + 1] or level - array[j - 1])                                           res += Math.max(0, level - neighbor)
*                  如果下一个值>当前level 说明对res没有贡献所以取0                                                                如果neighbor > 当前Level 说明对res没有贡献所以取0
*                  遍历完数组所有的点即得res                                                                                    best first search 遍历完数组所有的点即得res
* */

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxWaterTrappedII {
    static class Cell{
        int x;
        int y;
        int h;
        public Cell(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
    public int maxTrapped(int[][] matrix) {
        int res = 0;
        if(matrix == null || matrix.length < 3 || matrix[0] == null || matrix[0].length < 3){
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(m * n, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                if(c1.h == c2.h){
                    return 0;
                }
                return c1.h < c2.h ? -1 : 1;
            }
        });

        boolean[][] visited = new boolean[m][n];
        //initialize the minHeap with boarder cells
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1){
                    visited[i][j] = true;
                    minHeap.offer(new Cell(i, j, matrix[i][j]));
                }
            }
        }

        return bfs(matrix, visited, minHeap, res);

    }

    private int bfs(int[][] matrix, boolean[][] visited, PriorityQueue<Cell> minHeap, int res){
        int[] dirX = new int[]{-1, 0, 1, 0};
        int[] dirY = new int[]{0, 1, 0, -1};
        int level = Integer.MIN_VALUE;
        while(!minHeap.isEmpty()){
            Cell tmp = minHeap.poll();
            level = Math.max(level, tmp.h);
            for(int i = 0; i < 4; i++){
                int x = tmp.x + dirX[i];
                int y = tmp.y + dirY[i];
                if(isValid(x, y, visited)){
                    visited[x][y] = true;
                    res += Math.max(0, level - matrix[x][y]);
                    minHeap.offer(new Cell(x, y, matrix[x][y]));
                }
            }

        }
        return res;


    }

    private boolean isValid(int x, int y, boolean[][] visited){
        return x >= 0 && x < visited.length && y >= 0 && y < visited[0].length && !visited[x][y];
    }
}
