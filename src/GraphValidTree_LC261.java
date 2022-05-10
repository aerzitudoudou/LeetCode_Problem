import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphValidTree_LC261 {
// graph theory
// a tree has the following three properties:
// 1. connected 2. no cycle 3. n-1 edges
// any of the above two conditions could lead to a tree!!!


 //sol1, from jh, adjancency list + dfs, O(V + E) -> E = V - 1 worse case = O(V), O(V + E) = O(V)
 public boolean validTree(int n, int[][] edges) {
        if(edges.length > n - 1) return false;
        //build up adjancency list
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<Integer>());
        }
        //O(E)
        for(int i = 0; i < edges.length; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        //helper, use dfs, check if has seperate island, no need to check cycle due to graph theory
        Set<Integer> visited = new HashSet<>();

        //O(V)
        dfs(graph, 0, -1, visited);
        return visited.size() == n;

    }


    private void dfs(List<List<Integer>> graph, int cur, int pre, Set<Integer> visited){
        // //has cycle
        // if(visited.contains(cur)){
        //    return;
        // }
        visited.add(cur);
        List<Integer> neiList = graph.get(cur);
        for(int i = 0; i < neiList.size(); i++){
            int nei = neiList.get(i);
            if(!visited.contains(nei) && nei != pre){//not going back to the parent
                dfs(graph, nei, cur, visited);
            }
        }
    }

}
