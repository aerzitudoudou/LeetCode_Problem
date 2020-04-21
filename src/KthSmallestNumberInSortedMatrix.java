/*

laicode
* 26. Kth Smallest Number In Sorted Matrix
Medium
Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

Assumptions

the matrix is not null, N > 0 and M > 0
K > 0 and K <= N * M
Examples

{ {1,  3,   5,  7},

  {2,  4,   8,   9},

  {3,  5, 11, 15},

  {6,  8, 13, 18} }

the 5th smallest number is 4
the 8th smallest number is 6
*
*
*
*
* */


import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestNumberInSortedMatrix {
    //way 1
    public int kthSmallest(int[][] matrix, int k) {
        //row number
        int m = matrix.length;
        //column number
        int n = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
            @Override
            public int compare(Cell c1, Cell c2){
                return Integer.valueOf(c1.value).compareTo(c2.value);
            }
        });
        boolean[][] visited = new boolean[m][n];
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        //de-dup: which node has been generated(not expanded)
    /*
    0,  1b
    1a, 2b
    1a 1b can both be expanded, however, we only want 2b to be generated once to avoid duplicate.
    basically the element in pq need to be for only one time as compared to the original matrix
    */
        visited[0][0] = true;
        for(int i = 0; i < k - 1; i++){
            Cell tmp = minHeap.poll();
            visited[tmp.row][tmp.column] = true;
            if(tmp.row + 1 < m && !visited[tmp.row + 1][tmp.column]){
                minHeap.offer(new Cell(tmp.row + 1, tmp.column, matrix[tmp.row + 1][tmp.column]));
                visited[tmp.row + 1][tmp.column] = true;
            }
            if(tmp.column + 1 < n && !visited[tmp.row][tmp.column + 1]){
                minHeap.offer(new Cell(tmp.row, tmp.column + 1, matrix[tmp.row][tmp.column + 1]));
                visited[tmp.row][tmp.column + 1] = true;
            }
        }
        return minHeap.peek().value;
    }


    static class Cell{
        int row;
        int column;
        int value;
        Cell(int row, int column, int value){
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }



   //way 2:
    public int kthSmallest2(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new ArrayComparator());
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        pq.offer(new int[]{0, 0, matrix[0][0]});
        visited[0][0] =true;
        return bfs(pq, visited, matrix, k);
    }

    private int bfs(PriorityQueue<int[]> pq, boolean[][] visited, int[][] matrix, int k){
        int[] dirX = {0, 1};
        int[] dirY = {1, 0};
        for(int i = 0; i < k; i++){
            //expand
            int[] tmp = pq.poll();
            if(i == k - 1){
                return matrix[tmp[0]][tmp[1]];
            }
            //generate
            for(int a = 0; a < 2; a++){
                int x = tmp[0] + dirX[a];
                int y = tmp[1] + dirY[a];
                if(isValid(x, y, visited)){
                    pq.offer(new int[]{x, y, matrix[x][y]});
                    visited[x][y] = true;
                }
            }
        }

        return Integer.MAX_VALUE;
    }


    private boolean isValid(int i, int j, boolean[][] visited){
        int m = visited.length;
        int n = visited[0].length;
        return i >= 0 && i < m && j >= 0 && j < n && !visited[i][j];
    }

    //注意： 自己写comparator的时候 class name 是没有type 的！
    private class ArrayComparator implements Comparator<int[]>{
        @Override
        /*
        * an overridden method can have a different access modifier but it cannot lower the access scope. Methods declared public in a superclass also must be public in all subclasses.
        * */
        public int compare(int[] a, int[] b){
            return Integer.valueOf(a[2]).compareTo(b[2]);
        }
    }

}
