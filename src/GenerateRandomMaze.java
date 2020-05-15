import java.util.Random;
/*
* laicode
*
*218. Generate Random Maze
Hard
Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is denoted by 1 in the matrix and corridor is denoted by 0.

Assumptions

N = 2K + 1 and K >= 0
the top left corner must be corridor
there should be as many corridor cells as possible
for each pair of cells on the corridor, there must exist one and only one path between them
Examples

N = 5, one possible maze generated is

        0  0  0  1  0

        1  1  0  1  0

        0  1  0  0  0

        0  1  1  1  0

        0  0  0  0  0




*
*
*
*
* */
//N^2 个点，每次走2个，最多走 N^2 / 2次， 也就是N^2 的数量级 ---> recursion tree 的层数
//T:4 ^(N^2)
//S: N^2 = 层数  每一层多开的int[] cur 和int[] rand数组都是常数级别可以忽略不计
public class GenerateRandomMaze {
    public int[][] maze(int n) {
        //走的时候类似bfs,点到面，4个方向都走，但是走的顺序随机(perfect shuffle on the direction). dfs走到不能走就是结果
        //每次走两个可以保证 For each pair of cells on the corridor, there must exist one and only one path between them. 墙和corridor都是一行的不存在2*2相同矩阵
        //或者可以理解成dfs recursion tree上的每一个带来的变化都要记录到，所以没有吃，吐的过程
        //走两格和迷宫的长度为奇数这两个条件是同时存在的。这个解法组合可以保证路不被堵死，并且墙的宽度长度为1，因为每次走两步即使着U型走法也能够在中间留有间隙，不会使墙叠加。
        //generate maze
        int[][] maze = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0){
                    maze[i][j] = 0;
                }else{
                    maze[i][j] = 1;
                }
            }
        }
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        int[] cur = {0, 0}; //i, j coordinate
        randomDfs(maze, dirX, dirY, cur);
        return maze;

    }

    private void randomDfs(int[][] maze, int[] dirX, int[] dirY, int[] cur){
        int[] rand = shuffle();
        for(int i = 0; i < rand.length; i++){
            //注意！！！！第一次做错了，直接dirX[i]了，应该是dirX take 一个random的index
            int randIndex = rand[i];
            //跳一步坐标
            int x1 = cur[0] + dirX[randIndex];
            int y1 = cur[1] + dirY[randIndex];
            //跳两步坐标
            int x2 = cur[0] + 2 * dirX[randIndex];
            int y2 = cur[1] + 2 * dirY[randIndex];

            if(isValidCell(x1, y1, maze) && isValidCell(x2, y2, maze)){
                maze[x1][y1] = 0;
                maze[x2][y2] = 0;
                randomDfs(maze, dirX, dirY, new int[]{x2, y2});
            }
        }


    }


    private boolean isValidCell(int x, int y, int[][] maze){
        int n = maze.length;
        int m = maze[0].length;
        return x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 1;
    }

//T: O(1) 常数级别，一共做4次交换
    private int[] shuffle(){
        int[] res = {0, 1, 2, 3};
        Random rand = new Random();
        for(int i = 0; i < 4; i++){
            int index = i + rand.nextInt(4 - i);
            swap(res, index, i);
        }
        return res;
    }

    private void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
