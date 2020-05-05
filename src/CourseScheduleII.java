import java.util.*;

public class CourseScheduleII {
    //T: O(V + E)
    //S: O(V + E)
    //对于adjacency list 的空间复杂度的分析：
    /*
    * For a completely connected graph the number of edges E is O(V^2) itself, so the notation O(V+E) for the space complexity is still correct too.

    * However, the real advantage of adjacency lists is that they allow to save space for the graphs that are not really densely connected.
    * If the number of edges is much smaller than V^2, then adjacency lists will take O(V+E), and not O(V^2) space.
    *
    *
    *
    * */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //find indegree of all the courses, put ones that are 0 into queue
        //  <num, indegree>

        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0] == null){
            //空数组可以表示成new int[0] 或者new int[]{}
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>(); //indegree map //O(V) V is the number of courses, i.e. the number of nodes in the adjacency matrix
        Map<Integer, List<Integer>> neis = new HashMap<>(); //neighbors map S: O(V + E) adjacency list 的空间复杂度是O(V + E)  最坏是O(V^2)
        for(int i = 0; i < prerequisites.length; i++){ //T: O(E)
            //build indegree map
            Integer post = prerequisites[i][0];
            Integer pre = prerequisites[i][1];

            map.putIfAbsent(pre, 0); //appeared number if not there added to the map with indegree count as 0
            if(map.putIfAbsent(post, 1) != null){
                map.put(post, map.get(post) + 1);
            }
            //build neis map
            neis.putIfAbsent(pre, new ArrayList<Integer>());
            neis.putIfAbsent(post, new ArrayList<Integer>());
            neis.get(pre).add(post);
        }

        //run bfs, expand X, decrease the indegree by one for it's descendants, enqueue if descendant's indegree is 0

        Deque<Integer> queue = new LinkedList<>(); //S: O(V)
        int[] res = new int[numCourses];
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() == 0){
                queue.offerFirst(entry.getKey());
            }
        }
        int count = 0;
        while(!queue.isEmpty()){ //BFS: T: O(V + E)   expand: (1) + generate (out1) + map operation and en-queue(1) + (1) + generate (out2) + map operation and en-queue(1) + (1) + generate (out3) + map operation and en-queue(1) ...
            // = O(V * (1 + 1)) + O(out1 + out2 + ... + outn) = O(V + E)
            //expand
            Integer tmp = queue.pollLast();
            res[count++] = tmp;
            //generate
            for(Integer nei : neis.get(tmp)){
                map.put(nei, map.get(nei) - 1);
                if(map.get(nei) == 0){
                    queue.offerFirst(nei);
                }
            }

        }
        //check to see if there are still node that have indegree, if yes then there're cicle in the graph and therefore invalid

        if(count != res.length){
            return new int[]{};
        }
        return res;



    }
}
