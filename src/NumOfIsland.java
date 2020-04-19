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

public class NumOfIsland {
    public int numIslands(boolean[][] grid) {
        int res = 0;
        //corner case: 二维数组 1. null 2. []有一行但是没有内容: 对应grid.length == 0  3. [0, 1] 但是0 指向一个null value, 对应grid[0] == null
        //查grid[0] == null之前一定要查grid.length == 0 确保没有null pointer
        if(grid == null || grid.length == 0 || grid[0] == null){
            return res;
        }

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j]){
                    bfs(i, j, grid);
                    res++;
                }
            }
        }
        return res;
    }

    private void bfs(int i, int j, boolean[][] grid){
        int[] dirX = {0, 1, -1, 0};
        int[] dirY = {1, 0, 0, -1};

        //initialization
        //这里point 也可以换成一个长度为2的数组
        Deque<Point> queue = new LinkedList<>();
        Point tmp = new Point(i, j);
        queue.offerFirst(tmp);
        grid[i][j] = false;

        while(!queue.isEmpty()){
            //expand
            Point point = queue.pollLast();
            //generate 4个方向
            for(int a = 0; a < 4; a++){
                int x = point.x + dirX[a];
                int y = point.y + dirY[a];
                if(isValidCor(x, y, grid) && grid[x][y]){
                    queue.offerFirst(new Point(x, y));
                    grid[x][y] = false;
                }
            }

        }
    }

    private boolean isValidCor(int x, int y, boolean[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        return x >= 0 && x < m && y >= 0 && y < n;
    }



    //way 2：不单独定义一个数据结构，直接用数组
    private void bfs2(int i, int j, boolean[][] grid){
        int[] dirX = {0, 1, -1, 0};
        int[] dirY = {1, 0, 0, -1};

        //initialization
        //这里point 也可以换成一个长度为2的数组
        Deque<int[]> queue = new LinkedList<>();
        int[] tmp = {i, j};
        queue.offerFirst(tmp);
        grid[i][j] = false;

        while(!queue.isEmpty()){
            //expand
            int[] point = queue.pollFirst();
            //generate 4个方向
            for(int a = 0; a < 4; a++){
                int x = point[0] + dirX[a];
                int y = point[1] + dirY[a];
                if(isValidCor(x, y, grid) && grid[x][y]){
                    queue.offerFirst(new int[]{x, y});
                    grid[x][y] = false;
                }
            }

        }
    }
}
