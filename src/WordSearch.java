// class WordSearch {
//     int m, n;
//     public boolean exist(char[][] board, String word) {
//         m = board.length;
//         n = board[0].length;

//         char[] chars = word.toCharArray();
//         boolean[][] visited = new boolean[m][n];
//         for(int i = 0; i < m; i++){
//             for(int j = 0; j < n; j++){
//                 boolean[] flag = new boolean[1];
//                 dfs(i, j, board, visited, 0, chars, flag);
//                 if(flag[0]){
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }


//     int[] xAry = new int[]{0, 1, 0, -1};
//     int[] yAry = new int[]{1, 0, -1, 0};

//     private void dfs(int i, int j, char[][] board, boolean[][] visited, int index, char[] chars, boolean[] ary){
//         //sol1:
//         // if(index == chars.length - 1 && board[i][j] == chars[index]) {
//         //     ary[0] = true;
//         //     return;
//         // };
//         // if(board[i][j] != chars[index]){
//         //     return;
//         // }
//         // //index < chars.length && board[i][j] == chars[index]
//         // visited[i][j] = true;
//         // for(int a = 0; a < 4; a++){
//         //     int x = i + xAry[a];
//         //     int y = j + yAry[a];
//         //     if(isValid(x, y, board, visited)){
//         //         dfs(x, y, board, visited, index + 1, chars, ary);
//         //     }
//         // }
//         // visited[i][j] = false;




//         //sol2:
//         // char cur = board[i][j];
//         // if(index == chars.length - 1 && cur == chars[index]){
//         //     ary[0] = true;
//         // }
//         // if(cur != chars[index]){
//         //     return;
//         // }
//         // for(int a = 0; a < 4; a++){
//         //     int x = i + xAry[a];
//         //     int y = j + yAry[a];
//         //     if(isValid(x, y, board, visited) && index < chars.length - 1){
//         //         visited[x][y] = true;
//         //         dfs(x, y, board, visited, index + 1, chars, ary);
//         //         visited[x][y] = false;
//         //     }
//         // }



//     }

//     private boolean isValid(int i, int j, char[][] board, boolean[][] visited){
//         return i >= 0 && i < m && j >= 0 && j < n && !visited[i][j];
//     }





// }

/*
best solution
T: m * n * 4 ^ l
l is the length of word
S: o(l)
*/

class WordSearch {
    //check at current level. return early if either branch is true to prune.
    int m, n;
    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        char[] chars = word.toCharArray();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(dfs(i, j, board, 0, chars)){
                    return true;
                }
            }
        }
        return false;
    }
    int[] xAry = new int[]{0, 1, 0, -1};
    int[] yAry = new int[]{1, 0, -1, 0};

    private boolean dfs(int i, int j, char[][] board, int index, char[] chars){
        if(index == chars.length) return true;
        if(!isValid(i, j, board, chars, index)) return false;

        board[i][j] = '#';  //reuse board to check visited
        for(int a = 0; a < 4; a++){
            if(dfs(i + xAry[a], j + yAry[a], board, index + 1, chars)){
                //need return
                return true;
            }
        }
        board[i][j] = chars[index];
        return false;

    }

    private boolean isValid(int i, int j, char[][] board, char[] chars, int index){
        return i >= 0 && i < m && j >= 0 && j < n && board[i][j] == chars[index];

    }

//    class WordSearch {
//     int m, n;
//     public boolean exist(char[][] board, String word) {
//         m = board.length;
//         n = board[0].length;

//         char[] chars = word.toCharArray();

//         for(int i = 0; i < m; i++){
//             for(int j = 0; j < n; j++){
//                 boolean[] flag = new boolean[1];
//                 dfs(i, j, board, 0, chars, flag);
//                 if(flag[0]){
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
//     int[] xAry = new int[]{0, 1, 0, -1};
//     int[] yAry = new int[]{1, 0, -1, 0};

//     private void dfs(int i, int j, char[][] board, int index, char[] chars, boolean[] flag){
//         if(index == chars.length) {
//             flag[0] = true;
//             return;
//         }
//         if(!isValid(i, j, board, chars, index)){
//            return;
//         }
//         board[i][j] = '#';
//         for(int a = 0; a < 4; a++){
//            dfs(i + xAry[a], j + yAry[a], board, index + 1, chars, flag);
//            if(flag[0]) return;
//         }
//         board[i][j] = chars[index];
//     }

//     private boolean isValid(int i, int j, char[][] board, char[] chars, int index){
//         return i >= 0 && i < m && j >= 0 && j < n && board[i][j] == chars[index];

//     }
}