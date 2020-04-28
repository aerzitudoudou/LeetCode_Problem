import java.util.*;

public class WordSearch {
    //my sol: TLE

    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    //my way: dfs 暴力
    //T: M * N *(4 ^ L)
    //S: O(M + N) + O(L)
    public boolean exist(char[][] board, String word) {
        //corner case:
        if(board == null || board.length == 0 || board[0] == null || word == null || word.length() == 0){
            return false;
        }

        boolean res = false;
        Deque<Integer[]> queue = new LinkedList<>();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    queue.offerFirst(new Integer[]{i, j});
                }
            }
        }
        while(!queue.isEmpty()){ //最多Loop M *N 次
            Integer[] tmp = queue.pollLast();
            Deque<Integer[]> stack = new LinkedList<>();
            Set<List<Integer>> set = new HashSet<>();
            stack.offerFirst(tmp);
            set.add(Arrays.asList(tmp));
            boolean[] flag = new boolean[]{false};
            dfs(stack, board, word, set, 0, flag);
            if(flag[0]){
                return true;
            }
        }
        return false;

    }

    //word length = L 每层叉出4个叉 T: O(4 ^ L)
    //S: 2D matrix M rows and N columns S: O(M + N) heap 上set 的空间 + O(L) stack 空间
    private void dfs(Deque<Integer[]> stack, char[][] board, String word, Set<List<Integer>> set, int index, boolean[] flag){
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        if(flag[0] == true){ //第一遍这里没有做： 一旦发现有符合的，立即退出 不加这个条件程序就会遍历完recursion tree上所有的节点
            return;
        }
        if(index == word.length() - 1){
            flag[0]  = true;
            return;
        }
        Integer[] tmp = stack.peekFirst();
        for(int i = 0; i < 4; i++){
            int x = tmp[0] + dirX[i];
            int y = tmp[1] + dirY[i];
            char c = word.charAt(index + 1);
            if(isValid(board, set, x, y, c)){
                Integer[] cur = new Integer[]{x, y};
                stack.offerFirst(cur);
                set.add(Arrays.asList(cur));
                dfs(stack, board, word, set, index + 1, flag);
                stack.pollFirst();
                set.remove(Arrays.asList(cur));
            }
        }
        return;

    }

    private boolean isValid(char[][] board, Set<List<Integer>> set, int x, int y, char c){
        int m = board.length;
        int n = board[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && !set.contains(Arrays.asList(new Integer[]{x, y})) && board[x][y] == c;
    }
}
