import java.util.*;

/*
laicode
* 195. Place To Put The Chair I
Hard
Given a gym with k pieces of equipment and some obstacles.  We bought a chair and wanted to put this chair into the gym such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 'C' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not put the chair on a cell with equipment or obstacle.

Assumptions

There is at least one equipment in the gym
The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
It is guaranteed that each 'C' cell is reachable from all 'E' cells.
If there does not exist such place to put the chair, just return {-1, -1}
Examples

{ { 'E', 'O', 'C' },

  {  'C', 'E',  'C' },

  {  'C',  'C',  'C' } }

we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipment is 1 + 1 = 2, which is minimal.
*
*
*
* */
//k is the number of Equipments
//T: (number of K * M * N) = O(M * N)
//S: O(M * N)
public class PlaceToPutTheChairI {
    public List<Integer> putChair(char[][] gym) {
        //不能是int 否则就是List<int[]> 类型了返回的
        List<Integer> res = Arrays.asList(new Integer[]{-1, -1});
        //find E
        List<int[]> eList = new ArrayList<>();
        for(int i = 0; i < gym.length; i++){
            for(int j = 0; j < gym[0].length; j++){
                if(gym[i][j] == 'E'){
                    //en-queue
                    eList.add(new int[]{i, j});
                }
            }
        }
        //store/accumulate the shortest path from each E cell
        int[][] count = new int[gym.length][gym[0].length];
        int[] dirX = new int[]{-1, 0, 1, 0};
        int[] dirY = new int[]{0, 1, 0, -1};
        //for each E run bfs to find the shortest path from E to each C, add the shortest path on each cell
        for(int[] e : eList){
            Deque<int[]> queue = new LinkedList<>();
            Set<List<Integer>> set = new HashSet<>();
            //en-queue
            int level = 0;
            queue.offerFirst(e);
            set.add(Arrays.asList(new Integer[]{e[0], e[1]}));
            while(!queue.isEmpty()){
                int size = queue.size();
                level++;

                for(int j = 0; j < size; j++){
                    //expand
                    int[] tmp = queue.pollLast();
                    //generate
                    for(int i = 0; i < 4; i++){
                        int x = tmp[0] + dirX[i];
                        int y = tmp[1] + dirY[i];
                        if(isValid(x, y, gym, set)){
                            //'E' 'C'都可以入队
                            queue.offerFirst(new int[]{x, y});
                            set.add(Arrays.asList(new Integer[]{x, y}));
                            //C 可以accumulate 最短路径
                            if(gym[x][y] == 'C'){
                                count[x][y] += level;
                            }
                        }
                    }
                }
            }
        }
        //find global Min of the shortest path from E to each C
        int globalMin = Integer.MAX_VALUE;

        for(int i = 0; i < gym.length; i++){
            for(int j = 0; j < gym[0].length; j++){
                if(gym[i][j] == 'C' && count[i][j] < globalMin){
                    globalMin = count[i][j];
                    res = Arrays.asList(new Integer[]{i, j});
                }
            }
        }
        return res;
    }

    private boolean isValid(int x, int y, char[][] gym,  Set<List<Integer>> set){
        return x >= 0 && x < gym.length && y >= 0 && y < gym[0].length && gym[x][y] != 'O' && !set.contains(Arrays.asList(new Integer[]{x, y}));
    }
}
