package amazonOA;

import java.util.Deque;
import java.util.LinkedList;



//dfs
public class NumberOfIslands {
    //dfs
    //O(m * n) O(Max{m,n})
    public int numIslandsDfs(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    count++;

                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j){
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        return;
    }





    //bfs
    //T: O(M * N) S: average: O(min(M,N)) worst case: O(M * N) if grid row size is M  and column size is N

    public int numIslands(char[][] grid) {
        int res = 0;
        //corner case: 二维数组 1. null 2. []有一行但是没有内容: 对应grid.length == 0  3. [0, 1] 但是0 指向一个null value, 对应grid[0] == null
        //查grid[0] == null之前一定要查grid.length == 0 确保没有null pointer
        if(grid == null || grid.length == 0 || grid[0] == null){
            return 0;
        }

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    bfs(i, j, grid);
                    res++;
                }
            }
        }
        return res;
    }




    //way 2：不单独定义一个数据结构，直接用数组

    private void bfs(int i, int j, char[][] grid){
        int[] dirX = {0, 1, -1, 0};
        int[] dirY = {1, 0, 0, -1};

        //initialization
        //这里point 也可以换成一个长度为2的数组
        Deque<int[]> queue = new LinkedList<>();
        int[] tmp = new int[]{i, j};
        queue.offerFirst(tmp);
        grid[i][j] = '0';

        while(!queue.isEmpty()){
            //expand
            int[] point = queue.pollFirst();
            //generate 4个方向
            for(int a = 0; a < 4; a++){
                int x = point[0] + dirX[a];
                int y = point[1] + dirY[a];
                if(isValidCor(x, y, grid) && grid[x][y] == '1'){
                    queue.offerFirst(new int[]{x, y});
                    grid[x][y] = '0';
                }
            }

        }
    }

    private boolean isValidCor(int x, int y, char[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
