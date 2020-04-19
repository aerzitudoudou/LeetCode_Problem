import java.util.Deque;
import java.util.LinkedList;

/*
* lintcode
* 611. Knight Shortest Path

Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if destination cannot be reached.

Example
Example 1:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2]
Output: 2
Explanation:
[2,0]->[0,1]->[2,2]
Example 2:

Input:
[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2]
Output:-1
Clarification
If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)
Notice
source and destination must be empty.
Knight can not enter the barrier.
Path length refers to the number of steps the knight takes.
*
*
*
*
* */
public class KnightShortestPath {

    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        int level = -1;
        //corner case check
        if(grid == null || grid.length == 0 || grid[0] == null){
            return level;
        }
        //corner case check2: : 第一次做的时候忘了查这个
        if(source.x == destination.x && source.y == destination.y){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Deque<Point> queue = new LinkedList<>();


        //step 1: initialization: put source into the queue, switched visit flag for source to true
        if(isValid(source, grid, visited)){
            queue.offerFirst(source);
            visited[source.x][source.y] = true;
            level = bfs(queue, grid, visited, destination);
        }

        return level;
    }


    private int bfs(Deque<Point> queue, boolean[][] grid, boolean[][] visited, Point destination){
        int level = 0;
        int[] dirX = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dirY = {2, -2, 2, -2, 1, -1, 1, -1};
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++){
                //expand node from same level
                Point tmp = queue.pollLast();
                //generate 8 neighbors
                for(int a = 0; a < 8; a++){
                    Point point = new Point(tmp.x + dirX[a], tmp.y + dirY[a]);
                    if(isValid(point, grid, visited)){
                        if(point.x == destination.x && point.y == destination.y){
                            return level;
                        }
                        queue.offerFirst(point);
                        visited[tmp.x + dirX[a]][tmp.y + dirY[a]] = true;
                    }
                }
            }

        }
        return -1;


    }

    private boolean isValid(Point p, boolean[][] grid, boolean[][] visited){
        int x = p.x;
        int y = p.y;
        int m = grid.length;
        int n = grid[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && !grid[x][y] && !visited[x][y];
    }
}
