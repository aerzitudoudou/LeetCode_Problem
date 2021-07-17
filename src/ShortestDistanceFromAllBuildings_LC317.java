import java.util.Deque;
import java.util.LinkedList;

public class ShortestDistanceFromAllBuildings_LC317 {
    //sol1, O(m^2*n^2), O(m * n)from https://www.youtube.com/watch?v=F7AM7AGJZYE
    public int shortestDistance(int[][] grid) {
        int res = Integer.MAX_VALUE, count = 0;
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n];
        int[][] countBuilding = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    count++;
                    bfs(distance, countBuilding, grid, i, j);
                }

            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(countBuilding[i][j] == count){
                    res = Math.min(distance[i][j], res);
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void bfs(int[][] distance, int[][] countBuilding, int[][] grid, int i, int j){
        int[] dirX = {0, 1, 0, -1};
        int[] dirY = {1, 0, -1, 0};
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new LinkedList<>();
        queue.offerFirst(new int[]{i, j});
        visited[i][j] = true;
        int dis = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            dis++; //add 1 distance per level!!!
            for(int a = 0; a < size; a++){ //for nodes on each level
                int[] cur = queue.pollLast();
                for(int b = 0; b < 4; b++){
                    int x = cur[0] + dirX[b];
                    int y = cur[1] + dirY[b];
                    if(isValid(x, y, grid, visited)){
                        countBuilding[x][y]++;
                        distance[x][y] += dis;
                        visited[x][y] = true;
                        queue.offerFirst(new int[]{x, y});
                    }
                }
            }
        }

    }

    private boolean isValid(int x, int y, int[][] grid, boolean[][] visited){
        int m = grid.length, n = grid[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == 0;
    }
}
