package amazonOA;
//leetcode 547 https://leetcode.com/problems/friend-circles/

/*dfs: visited[i] represents whether the i'th element has been visited
start from each element that hasn't been visited so far for finding all the friends, and marked them as visisted along the dfs process
increment result counter whenever one time of dfs finishes
*/
//T: O(N^2)  S:O(N) where N is the length of 2-dimension array M
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for(int i = 0;i < M.length;i++){
            if(visited[i] == 0){
                dfs(M,visited,i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M,int[] visited,int i){
        for(int j = 0; j < M.length;j++){
            if(M[i][j] == 1 && visited[j] == 0){
                visited[j] = 1;
                dfs(M,visited,j);
            }
        }
    }
}
