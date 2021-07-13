import java.util.Deque;
import java.util.LinkedList;

public class MiniMumMovesToMoveABoxToTheirTargetLocation_LC1263 {
    class Node{
        int bx;
        int by;
        int px;
        int py;
        public Node(int bx, int by, int px, int py){
            this.bx = bx;
            this.by = by;
            this.px = px;
            this.py = py;
        }
    }
    /*
    sol1, from hf: https://www.youtube.com/watch?v=RwmNMRdnLl0
    one state is represented by 4 variables: bx, by, px , py
    level 0: bx, by, px, py
    level 0: only person move but no move on box, still same state, push to the queue beginning, no change on state
    level 1: both person and box moved, push to queue end, state + 1
    bi-directional bfs:
       de-dup using memo[][][][][]
    T: O(m * n)
    S: O(m ^ 2 * n ^ 2)
    */
    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int bx = 0, by = 0, px = 0, py = 0, tx = 0, ty = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 'S'){
                    px = i;
                    py = j;
                    grid[i][j] = '.';
                }
                if(grid[i][j] == 'B'){
                    bx = i;
                    by = j;
                    grid[i][j] = '.';

                }
                if(grid[i][j] == 'T'){
                    tx = i;
                    ty = j;
                    grid[i][j] = '.';

                }
            }
        }


        Deque<Node> deque = new LinkedList<>();
        Integer[][][][] memo = new Integer[m][n][m][n];

        memo[bx][by][px][py] = 0;
        deque.offerFirst(new Node(bx, by, px, py));
        return bfs(deque, memo, grid, tx, ty);
    }


    private int bfs(Deque<Node> deque, Integer[][][][] memo, char[][] grid, int tx, int ty){
        int m = grid.length, n = grid[0].length;
        int[] dirX = {1, 0, -1, 0};
        int[] dirY = {0, 1, 0, -1};
        while(!deque.isEmpty()){
            Node cur = deque.pollLast();
            int bx = cur.bx, by = cur.by, px = cur.px, py = cur.py;
            if(bx == tx && by == ty){
                return memo[bx][by][px][py];
            }
            //add same level node to the beginning of the deque
            for(int a = 0; a < 4; a++){
                int x = px + dirX[a];
                int y = py + dirY[a];
                if(!(x >= 0 && x < m && y >= 0 && y < n) || grid[x][y] != '.'  || (x == bx && y == by) || memo[bx][by][x][y] != null) continue;
                memo[bx][by][x][y] = memo[bx][by][px][py];
                deque.offerLast(new Node(bx, by, x, y));
            }

            if(Math.abs(px - bx) + Math.abs(py - by) == 1){
                for(int a = 0; a < 4; a++){
                    //find the dir where box and person can move together
                    if(px + dirX[a] == bx && py + dirY[a] == by){
                        int bx2 = bx + dirX[a];
                        int by2 = by + dirY[a];
                        if(!(bx2 >= 0 && bx2 < m && by2 >= 0 && by2 < n) || grid[bx2][by2] != '.'  || memo[bx2][by2][bx][by] != null) continue;
                        memo[bx2][by2][bx][by] = memo[bx][by][px][py] + 1;
                        deque.offerFirst(new Node(bx2, by2, bx, by));
                    }
                }
            }
        }
        return -1;
    }
}
