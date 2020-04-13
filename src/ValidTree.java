import java.util.*;

public class ValidTree {
    //2. bfs can traverse every node
    public boolean validTree(int n, int[][] edges) {
        if(edges == null && n != 1){
            return false;
        }
        int edgeNum = edges.length;
        if(n - edgeNum != 1){
            return false;
        }

        Map<Integer, Set<Integer>> graph = generateGraph(n, edges);
        Deque<Integer> q = new LinkedList<>(); //在bfs里Queue 和hashmap是一对好基友，总是一起出现的，加的时候，总是一起加的
        Set<Integer> set = new HashSet<>();
        q.offerFirst(0);
        set.add(0); //第一次写得时候忘了给set加元素 这个set的元素也要加进来

        while(!q.isEmpty()){
            //expand
            int tmp = q.pollLast();
            //generate all the neighbors. dedup when generate. otherwise will be infinite loop while having circles in the graph
            for(Integer a : graph.get(tmp)){
                if(!set.contains(tmp)){
                    q.offerFirst(a); //set和queue 的元素相加总是一起出现
                    set.add(a);
                }
            }
        }
        return set.size() == n;

    }

    private Map<Integer, Set<Integer>> generateGraph(int n, int[][] edges){
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            map.put(i, new HashSet<Integer>());
        }
        //edges is a matrix of n * 2
        for(int i = 0; i < edges.length; i++){
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }

        return map;
    }
}
