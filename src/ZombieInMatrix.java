import java.util.Deque;
import java.util.LinkedList;

public class ZombieInMatrix {

    public int zombie(int[][] grid) {
        //corner case
        if(grid == null || grid.length == 0 || grid[0] == null){
            return -1;
        }
        int[] count1 = new int[1];
        int count2 = 0;
        //step 1: find all 1's
        Deque<int[]> queue = new LinkedList<>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    queue.offerFirst(new int[]{i, j});
                }
                if(grid[i][j] == 2){
                    count2++;
                }
            }
        }
        int day = bfs(queue, grid, count1);
        return grid.length * grid[0].length - count2 == count1[0] ? day : -1;

        //step 2:run bfs by level, where level is the days

    }

    private int bfs(Deque<int[]> queue, int[][] grid, int[] count1){
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        int day = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            day++;
            for(int i = 0; i < size; i++){
                int[] tmp = queue.pollLast();
                count1[0]++;
                //四个方向找邻居
                for(int a = 0; a < 4; a++){
                    int x = tmp[0] + dirX[a];
                    int y = tmp[1] + dirY[a];
                    if(isInBound(grid, x, y) && grid[x][y] == 0){
                        queue.offerFirst(new int[]{x, y});
                        grid[x][y] = 1;
                    }

                }
            }

        }
        return day - 1;//第一遍做错了！ -1 因为最后一轮的时候queue里的值已经都是1了，pop出去bfs就结束了。但是图都变成1是在最后一轮expand发生之前就已经是了。所以day 要减一。
    }

    private boolean isInBound(int[][] grid, int x, int y){
        int m = grid.length;
        int n = grid[0].length;
        return x >= 0 && x < m && y >= 0 && y < n;

    }
}
