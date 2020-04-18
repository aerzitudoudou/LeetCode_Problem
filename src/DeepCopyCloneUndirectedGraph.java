import java.util.*;

public class DeepCopyCloneUndirectedGraph {

//bfs
    //T: O(V + E) 即要copy node 又要copy edge
    //S: O(V )
 public List<GraphNode> copy1(List<GraphNode> graph) {
    //corner case check:
    if(graph == null){
      return graph;
    }
    List<GraphNode> res = new ArrayList<>();
    Deque<GraphNode> queue = new LinkedList<>();
    Map<GraphNode, GraphNode> map = new HashMap<>(); //空间 既有点又有边 所以V + E 但是因为是结果
    //initialization
    //这里要注意为什么每一个list 都要过一遍： 确定没有孤岛 e.g. 1-->, 2--->3 , 3---> 2
    //第二遍做的时候这里错了

    for(GraphNode node : graph){
        if(!map.containsKey(node)){
            queue.offerFirst(node);
            map.put(node, new GraphNode(node.key));
            bfs(map, graph, res, queue);
        }
    }
    return res;
   }


    private void bfs(Map<GraphNode, GraphNode> map, List<GraphNode> graph, List<GraphNode> res,  Deque<GraphNode> queue){
        while(!queue.isEmpty()){
            //去重： 每个数只往queue里放一次。 queue的状态和map始终保持一致。
            //expand
            GraphNode tmp = queue.pollFirst();
            res.add(map.get(tmp));
            //generate
            for(GraphNode nei : tmp.neighbors){
                if(!map.containsKey(nei)){
                    queue.offerFirst(nei);
                    map.put(nei, new GraphNode(nei.key));
                }
                map.get(tmp).neighbors.add(map.get(nei));
            }
        }
    }







    /*
    dfs
    */
    public List<GraphNode> copy2(List<GraphNode> graph) {
        if(graph == null){
            return graph;
        }

        List<GraphNode> res = new ArrayList<>();
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for(GraphNode node : graph){
            GraphNode tmp = dfs(node, map);
            res.add(tmp);
        }
        return res;


    }

    //DFS 物理意义：
 /*
 Recursive manner. Use map to store whether a node has been copied before
 clone all nodes and edges that are reachable from "input"
 after this function returns, lookup will contains all nodes that are reachable from this "input" and their copies
 return the copy of the input
 */
    private GraphNode dfs(GraphNode node, Map<GraphNode, GraphNode> map){
        if(node == null){
            return node;
        }
        if(map.containsKey(node)){
            return map.get(node);
        }
        GraphNode newNode = new GraphNode(node.key);
        map.put(node, newNode);
        for(GraphNode cur : node.neighbors){
            newNode.neighbors.add(dfs(cur, map));
        }
        return newNode;

    }
}
