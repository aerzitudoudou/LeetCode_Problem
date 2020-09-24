import java.util.Deque;
import java.util.LinkedList;

public class ShortestPathInAGridWithObstaclesElimination {
    public int shortestPath(int[][] grid, int k) {
        /*
        * 3 dimension visited matrix.  [x, y, z]
        * z used for storing how many obstacles so far encountered, [xyz] combination can't be revisited thus needs 去重.
        * if x, y are the same, only z is different, 不能去重，屬於需要分開討論的情況。
        *
        *bfs. valid condition: 1. valid cell coordination 2.cur steps <= k 3. not visited（xyz combination）
        * */
        // TODO: 時間複雜度更新

        Deque<int[]> queue = new LinkedList<>();
        queue.offerFirst(new int[]{0,0,0});
        return bfs(grid, k, queue);
    }


    private int bfs(int[][] grid, int k, Deque<int[]> queue){
        int m = grid.length, n = grid[0].length, res = 0;
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        boolean[][][] visited = new boolean[m][n][k + 1]; //z最大值是k, 所以需要多開一個z的長度

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int j = 0; j < size; j++){
                int[] cur = queue.pollLast();
                if(cur[0] == m - 1 && cur[1] == n - 1){
                    return res;
                }
                for(int i = 0; i < 4; i++){
                    int x = cur[0] + dirX[i];
                    int y = cur[1] + dirY[i];
                    if(!(x >= 0 && x < m && y >= 0 && y < n)){
                        continue;
                    }
                    int z = cur[2] + grid[x][y];
                    if(z <= k && !visited[x][y][z]){ //先後順序第一次做錯 顛倒過來會有arrayIndexOutOfBound exception
                        visited[x][y][z] = true;
                        queue.offerFirst(new int[]{x, y, z});
                    }
                }
            }
            res++;

        }
        return -1;
    }
}
