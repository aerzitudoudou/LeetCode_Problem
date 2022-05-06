import java.util.Deque;
import java.util.LinkedList;

class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class NumOfIsland_Lint433 {
    //sol1, my, O(m * n), O(min(m,n))
    public int numIslands(boolean[][] grid) {

        //corner case: 二维数组 1. null 2. []有一行但是没有内容: 对应grid.length == 0  3. [0, 1] 但是0 指向一个null value, 对应grid[0] == null
        //查grid[0] == null之前一定要查grid.length == 0 确保没有null pointer
        int res = 0;
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0){
            return res;
        }

        int n = grid.length, m = grid[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j]){
                    bfs(grid, i, j);
                    res++;
                }
            }
        }

        return res;


    }


    private void bfs(boolean[][] grid, int row, int col){
        Deque<int[]> queue = new LinkedList<>();
        grid[row][col] = false;
        queue.offerFirst(new int[]{row, col});
        int[] x = {0, 1, 0, -1};
        int[] y = {-1, 0, 1, 0};
        while(!queue.isEmpty()){
            int[] cur = queue.pollLast();
            int curX = cur[0];
            int curY = cur[1];
            for(int a = 0; a < 4; a++){
                int neiX = curX + x[a];
                int neiY = curY + y[a];
                if(isValid(grid, neiX, neiY)){
                    grid[neiX][neiY] = false;//de-dup!!! before enqueue
                    queue.offerFirst(new int[]{neiX, neiY});
                }
            }
        }

    }

    private boolean isValid(boolean[][] grid, int x, int y){
        int n = grid.length, m = grid[0].length;
        return x >= 0 && x < n && y >= 0 && y < m && grid[x][y];
    }


}
