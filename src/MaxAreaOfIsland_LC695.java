public class MaxAreaOfIsland_LC695 {
    //sol1, O(m * n), O(m * n): worst case, all 1, one stack will loop through all the nodes
    //dfs through each 1 cell, change to 0 after search. find the max size
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    int[] size = {0};
                    dfs(i, j, grid, size);
                    res = Math.max(res, size[0]);
                }
            }
        }

        return res;


    }

    private void dfs(int i, int j, int[][] grid, int[] size){
        //base case
        if(grid[i][j] == 0) return;

        grid[i][j] = 0;
        size[0]++;
        int[] dirX = {0,1,0,-1};
        int[] dirY = {1,0,-1,0};

        //4 directions dfs
        for(int a = 0; a < 4; a++){
            int x = i + dirX[a];
            int y = j + dirY[a];
            if(isValid(x, y, grid)){
                dfs(x, y,grid,size);

            }

        }
    }


    private boolean isValid(int x, int y, int[][] grid){
        int m = grid.length, n = grid[0].length;
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
