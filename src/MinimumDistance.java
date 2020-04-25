/*
*Description
You are now given a two-dimensional tabular graph , in which each grid contains a integer num.

If num is - 2, it means this grid is the starting grid. If num is - 3, it means this grid is the ending grid. If num is - 1, it means this grid has an obstacle on it and you can't move to it. If num is a positive number or 0，you can walk normally on it.

In each move you can travel from one grid to another if and only if they're next to each other or they contain the same positive number num. The cost of each move is 1.

Now you are asked to find the lowest cost of travelling from the starting grid to the ending grid. If the ending grid could not be reached, print -1.

It is guaranteed that the maxium number of rows and columns is 400, and the number in each grid will not exceed 50.

Have you met this question in a real interview?
Example
Example
Input:[[1,0,-1,1],[-2,0,1,-3],[2,2,0,0]]
Output:3
In this example,you can reach the ending grid through these moves:
First, move up from the starting grid to the grid that contains the number 1. Second, move to the grid with the same number at the top right.
Finally, move down to the ending grid. There are three moves in total, so the minimun cost will be 3.
*
*
* */

import java.util.*;

public class MinimumDistance {
    public int getMinDistance(int[][] mazeMap) {
        if (mazeMap == null || mazeMap.length == 0 || mazeMap[0] == null) {
            return -1;
        }
        int m = mazeMap.length;
        int n = mazeMap[0].length;
        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mazeMap[i][j] == -2) {
                    queue.offerFirst(new int[]{i, j});
                    visited[i][j] = true;
                }
                if (mazeMap[i][j] > 0) {
                    if (!map.containsKey(mazeMap[i][j])) {
                        List<int[]> list = new ArrayList<>();
                        list.add(new int[]{i, j});
                        map.put(mazeMap[i][j], list);
                    } else {
                        List<int[]> list = map.get(mazeMap[i][j]);
                        list.add(new int[]{i, j});
                        map.put(mazeMap[i][j], list);
                    }
                }

            }
        }

        return bfs(mazeMap, queue, visited, map);
    }


    private int bfs(int[][] mazeMap, Deque<int[]> queue, boolean[][] visited, Map<Integer, List<int[]>> map) {
        Set<Integer> set = new HashSet<>();

        int[] dirX = {-1, 0, 1, 0};
        int[] dirY = {0, 1, 0, -1};
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int[] tmp = queue.pollLast();
                for (int a = 0; a < 4; a++) {
                    int x = tmp[0] + dirX[a];
                    int y = tmp[1] + dirY[a];
                    if (isValid(x, y, visited, mazeMap) && mazeMap[x][y] == -3) {
                        return level;
                    }
                    if (isValid(x, y, visited, mazeMap)) {
                        queue.offerFirst(new int[]{x, y});
                        visited[x][y] = true;
                    }

                }
                //传送门: 传送门只要走过一次就不需要再走了。
                if (mazeMap[tmp[0]][tmp[1]] > 0) {
                    if (!set.contains(mazeMap[tmp[0]][tmp[1]])) {
                        List<int[]> list = map.get(mazeMap[tmp[0]][tmp[1]]);

                        for (int[] cur : list) {
                            if (!(cur[0] == tmp[0] && cur[1] == tmp[1]) && !visited[cur[0]][cur[1]]) {
                                queue.offerFirst(new int[]{cur[0], cur[1]});
                                visited[cur[0]][cur[1]] = true;
                            }
                        }
                        set.add(mazeMap[tmp[0]][tmp[1]]);
                    }
                }

            }
        }
        return -1;
    }

    private boolean isValid(int x, int y, boolean[][] visited, int[][] mazeMap) {
        int m = visited.length;
        int n = visited[0].length;
        return x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && mazeMap[x][y] != -1;
    }

}
