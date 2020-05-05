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
            /*
            * 这里为什么是O(V + E):
            * step 1: remove any node(X) from the queue and append it to the topological order O(1)
            * step 2: decrement the number of incoming edges of each node Y that X points to(O(Out(i)))
            * step 3: if Y has no incoming edges after the decrement, add Y to the queue O(1)
            *
            * 总的时间 = (1 + out1 + 1 )+ (1 + out2 + 1) + (1 + out3 + 1) + ...+ (1 + outn + 1)
            * 一共有点的个数V项的（1 + 1） +  总边数E = out1 + out2 + out3 + ... +outn这么多个边
            * 所以adjancency list一共的时间复杂度是O(V + E)
            * */
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
