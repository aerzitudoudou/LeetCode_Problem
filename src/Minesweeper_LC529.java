public class Minesweeper_LC529 {
    //sol1, dfs, acwing, O(m * n), O(Max(m, n))
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if(board[x][y] == 'M'){
            board[x][y] = 'X';
            return board;
        }

        dfs(board, x, y);
        return board;
    }

    private void dfs(char[][] board, int x, int y){
        //base case
        if(board[x][y] != 'E') return;

        int m = board.length, n = board[0].length;
        // mine number
        int count = 0;

        //loop through the 8 neighbors
        //if there're mines replace the E with the mine number, return the board

        for(int i = Math.max(x - 1, 0); i <= Math.min(m - 1, x + 1); i++){
            for(int j = Math.max(y - 1, 0); j <= Math.min(n - 1, y + 1); j++){
                if(i != x || j != y){
                    if(board[i][j] == 'M') count++;
                }
            }
        }

        if(count > 0){
            board[x][y] = (char) (count + '0');
            return;
        }

        //otherwise, if all neighbors are empty, set the cur coordinator to 'B', and dfs on the neighbors

        board[x][y] = 'B';




        for(int i = Math.max(x - 1, 0); i <= Math.min(m - 1, x + 1); i++){
            for(int j = Math.max(y - 1, 0); j <= Math.min(n - 1, y + 1); j++){
                if(i != x || j != y){
                    dfs(board, i, j);
                }

            }
        }
    }
}
