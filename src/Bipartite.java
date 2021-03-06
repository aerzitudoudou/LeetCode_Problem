import java.util.*;

public class Bipartite {
    /**
     * public class GraphNode {
     *   public int key;
     *   public List<GraphNode> neighbors;
     *   public GraphNode(int key) {
     *     this.key = key;
     *     this.neighbors = new ArrayList<GraphNode>();
     *   }
     * }
     */
        public boolean isBipartite(List<GraphNode> graph) {
            Map<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
            for (GraphNode n : graph) {
                if (!bfs(n, visited)) {
                    return false;
                }
            }
            return true;

        }

        private boolean bfs(GraphNode n, Map<GraphNode, Integer> visited){
            if(visited.containsKey(n)){
                return true;
            }
            Queue<GraphNode> queue = new ArrayDeque<GraphNode>();
            queue.offer(n);
            visited.put(n, 0);
            while(!queue.isEmpty()){
                //expand
                GraphNode cur = queue.poll();
                int curValue = visited.get(cur);
                //assign an opposite number to its neighbors
                int neiValue = curValue == 0 ? 1 : 0;
                for(GraphNode nei : cur.neighbors){
                    if(!visited.containsKey(nei)){
                        visited.put(nei, neiValue);
                        queue.offer(nei);
                    }else if(visited.get(nei) == curValue){
                        return false;
                    }
                }
            }
            return true;
        }

}
