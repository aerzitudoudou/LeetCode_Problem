import java.util.Deque;
import java.util.LinkedList;


/*
*
* 1888. Shortest Path in Matrix

Given the matrix of M rows and N columns, 0 represents empty space,- 1 represents obstacle, and 1 represents target points (there are multiple target points).

For each empty space, if you want to reach a target point at the shortest distance, please mark the direction of the first step.

If starting up, you should mark the point as 2. if starting down , you should mark the point as 3. if starting left , you should mark the point as 4. if starting right , you should mark the point as 5.

The priority of direction is up, down, left and right from big to small, that is, if you can reach the target point with the shortest distance from a point up or down, you are supposed to start up.

Returns the matrix after marking.

Example
input:[[1,0,1],[0,0,0],[1,0,0]]
output:[[1,4,1],[2,2,2],[1,4,2]]

Notice
0<m,n<1000
*
*
* */
public class ShortestPathInMatrix {
    public int[][] shortestPath(int[][] grid) {
        //corner case check
        if(grid == null || grid.length == 0 || grid[0] == null){
            return grid;
        }

        //initialization: find all 1's
        Deque<int[]> queue = new LinkedList();
        int[][] level = new int[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    queue.offerFirst(new int[]{i, j});
                }
            }
        }
        bfs(queue, grid, level);
        return grid;
    }

    private void bfs(Deque<int[]> queue, int[][] grid, int[][] level){
        int[] dirX = {-1, 1, 0, 0};
        int[] dirY = {0, 0, -1, 1};
        int[] dirNum = {3, 2, 5, 4}; //因为是反方向 从左边过来的indegree对于0出发是去到右边的
        int round = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            round++;

            for(int i = 0; i < size; i++){
                //expand
                int[] tmp = queue.pollLast();
                //generate
                for(int a = 0; a < 4; a++){
                    int x = tmp[0] + dirX[a];
                    int y = tmp[1] + dirY[a];
                    //入队condition:值为0 且是合法的cell
                    if(isValid(x, y, grid)){
                        if(grid[x][y] == 0){
                            queue.offerFirst(new int[]{x, y});
                            //mark 轮数 i.e. distance
                            level[x][y] = round;
                            grid[x][y] = dirNum[a];
                        }else if(grid[x][y] >= 2 && level[x][y] == round){//值为0但之前被别的indegree的edges改过
                            if(grid[x][y] > dirNum[a]){
                                grid[x][y] = dirNum[a];
                            }
                        }

                    }
                }
            }
        }
    }

    private boolean isValid(int x, int y, int[][] grid){
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
    }

}
