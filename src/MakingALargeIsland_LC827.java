import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland_LC827 {
    //O(n^2), O(n^2) from https://www.youtube.com/watch?v=KqkXZpRB1x8&ab_channel=HuaHua
    int[] dirX = {0, 1, 0, -1};
    int[] dirY = {1, 0, -1, 0};
    public int largestIsland(int[][] grid) {
        int n = grid.length, color = 1, res = 0;
        Map<Integer, Integer> colorToArea = new HashMap<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    color++;
                    int[] curArea = new int[1];
                    int area = dfs(i, j, grid, curArea, color);
                    colorToArea.put(color, area);
                    res = Math.max(res, area);
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    res = Math.max(getMergedArea(i, j, colorToArea, grid), res);
                }
            }
        }
        return res;
    }


    private int getMergedArea(int i , int j, Map<Integer, Integer> colorToArea, int[][] grid){
        int area = 1;

        Set<Integer> colorSet = new HashSet<>();
        for(int a = 0; a < 4; a++){
            int x = i + dirX[a];
            int y = j + dirY[a];
            if(isValid(x, y, grid) && grid[x][y] != 0 && !colorSet.contains(grid[x][y])){
                colorSet.add(grid[x][y]);
                area += colorToArea.get(grid[x][y]);
            }
        }
        return area;
    }

    private boolean isValid(int i, int j, int[][] grid){
        int n = grid.length;
        return i >= 0 && i < n && j >= 0 && j < n;
    }

    private int dfs(int i, int j, int[][] grid, int[] area, int color){
        if(!isValid(i, j, grid) || grid[i][j] == 0){
            return 0;
        }
        if(grid[i][j] == 1){
            grid[i][j] = color;
            area[0]++;
            for(int a = 0; a < 4; a++){
                int x = i + dirX[a];
                int y = j + dirY[a];
                dfs(x, y, grid, area, color);
            }
        }

        return area[0];

    }

}
