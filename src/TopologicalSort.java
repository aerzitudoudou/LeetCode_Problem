import java.util.*;

public class TopologicalSort {
    //T: graph  O(V + E)
    //S: O(V)
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if(graph == null){
            return graph;
        }
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        Map<DirectedGraphNode, Integer> map = new HashMap<>();
        //判断入度degree 所有有前序的node的indegree找出来
        for(DirectedGraphNode node : graph){  //O(V + E)
            for(DirectedGraphNode nei : node.neighbors){
                if(map.containsKey(nei)){
                    map.put(nei, map.get(nei) + 1);
                }else{
                    map.put(nei, 1);
                }
            }
        }



        //init queue with nodes whose indegree is 0
        Deque<DirectedGraphNode> q = new LinkedList<>();
        for(DirectedGraphNode node : graph){ //O(V)
            if(!map.containsKey(node)){
                q.offerFirst(node);
            }
        }
        //run bfs

        while(!q.isEmpty()){ //O(V + E)
            DirectedGraphNode tmp = q.pollFirst();
            res.add(tmp);
            for(DirectedGraphNode nei : tmp.neighbors){
                map.put(nei, map.get(nei) - 1);
                //只有indegree = 0 的时候入队
                if(map.get(nei) == 0){
                    q.offerFirst(nei);
                }
            }
        }

        return res;
    }
}
