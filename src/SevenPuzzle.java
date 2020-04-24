import java.util.*;

public class SevenPuzzle {
    //T: O(8!) 第一个位置上8种可能*第二个位置上7种可能。。。= O(n! * n)
    //"思路上是对的。但是需要注意细节，比如每次你需要O（8）的复杂度去check是否走通了。虽然题目给了棋盘大小为8，所以说O（8！）也是对的。但是记得check这一块的时间付出。"
    //s: O(n!)
    public int numOfSteps(int[] values) {
        Deque<Board> queue = new LinkedList<>();
        Set<Board> set = new HashSet<>();
        Board init = new Board(values);
        queue.offerFirst(init);
        set.add(init);
        Board des = new Board(new int[]{0, 1, 2, 3, 4, 5, 6, 7});
        if(new Board(values).equals(des)){
            return 0;
        }
        return bfs(queue, set, des);

    }

    private int bfs(Deque<Board> queue, Set<Board> set, Board des){
        int count = 0;
        //方向转置矩阵
        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        while(!queue.isEmpty()){
            count++;
            //extend
            int size = queue.size(); //做第一遍的时候这里出了问题！计算走了多少次是level order traversal
            //走过的步数一定要加level的，也就是记录queue.size
            for(int a = 0; a < size; a++){
                Board tmp = queue.pollLast();
                int[] coordinate = tmp.findIndex(tmp, 0);

                //generate with 3 direction
                for(int i = 0; i < 4; i++){
                    int x = coordinate[0] + dirX[i];
                    int y = coordinate[1] + dirY[i];
                    if(tmp.isValid(x, y)){ //每次generate要在原来的tmp基础上
                        //public Board swap(Board b, int x1, int y1, int x2, int y2){ //static class为什么可以有non static method???
                        tmp.swap(coordinate[0], coordinate[1], x, y); //这步之后，tmp 里的0值已经和目标位置换过了
                        if(tmp.equals(des)){
                            return count;
                        }
                        if(!set.contains(tmp)){
                            queue.offerFirst(tmp.clone());
                            set.add(tmp.clone());
                        }
                        tmp.swap(coordinate[0], coordinate[1], x, y);//like dfs swap swap, 在下一次gnerate之前，需要把0值和目标位置的值复原回原来的状态


                    }
                }
            }

        }
        return -1;
    }

    /*
    * 如何自定义一个类是这道题的难点
    * 需要注意的点标记在代码里了
    *
    * */
    static class Board{//prefer static nested class than non-static nested class. 原因less memory
        private final int R = 2; //命名常数
        private final int C = 4;
        int[][] board = new int[2][4];

        public Board(){ //nested class需要给空constructor 否则没有参数的调用会报错

        }
        public Board(int[] values){
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    board[i][j] = values[i * C + j]; //把二维转化为一维 用i * C + j
                }
            }
        }
        //去重需要hashcode
        /*
        * 1 , 2 , 3,  4
        * 5,  6,  7,  0
        * 的hashcode就是12345670
        * 最大的int是2,147,483,647 = 2 billion
        *
        * */
        @Override
        public int hashCode(){
            int code = 0;
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    code = code * 10 + board[i][j];
                }
            }
            return code;
        }
        @Override
        public boolean equals(Object o){ //equals Object 类里定义的时候传参的Object类型的
            if(!(o instanceof Board)){ //instanceof 是一个operator o 小写
                return false;
            }
            Board b = (Board) o; //强制转换要有
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    if(this.board[i][j] != b.board[i][j]){ //这里的this 是指代内部class Board的
                        return false;
                    }
                }
            }
            return true;
        }
        public int[] findIndex(Board b, int target){
            int[][] board = b.board;
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    if(board[i][j] == target){
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }
        private void swap(int x1, int y1, int x2, int y2){

            int tmp = board[x1][y1];
            board[x1][y1] =board[x2][y2];
            board[x2][y2] = tmp;


        }
        private boolean isValid(int x, int y){
            return x >= 0 && x < R && y >= 0 && y < C;
        }

        @Override //clone也是Object自带函数，需要override
        public Board clone(){
            Board c = new Board();
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    c.board[i][j] = board[i][j];
                }
            }
            return c;
        }
    }
}
