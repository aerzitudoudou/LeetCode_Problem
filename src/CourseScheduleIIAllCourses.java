import java.util.*;

public class CourseScheduleIIAllCourses {
    //T: O(N * N!)

    //S: O(V + E) V is number of vertex which is N
    public List<List<Integer>> findCourses(int numCourses, int[][] prerequisites){
        /*
        *
        *
        *   1. find all nodes with indegree = 0 and their neis
        *   2. do dfs with each 0 indegree node as root
        *
        *               0
        *             /   \
        *            1    2
        *              \ /
        *               3
        *
        *   indegree array :
        *   index     0  1  2  3
        *   value     0  1  1  2
        *
        *
        *  set(): current course with indegree = 0
        *
        *   recursion tree
        *
        *
        * set(0) --> (1, 2)       {0}
        *                        /    \
        *               (2)   {0, 1}  {0, 2}(1)
        *                      /           \
        *             (3)   {0,1 ,2 }     {0, 2, 1} (3)
        *                      |            |
        *                  {0,1,2,3}      {0, 2, 1, 3}
        *
        *
        *
        *
        *
        * */

        List<List<Integer>> res = new ArrayList<>();
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> neis = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        //init indegree and neis
        for(int i = 0; i < numCourses; i++){
            neis.put(i, new ArrayList<>());
        }
        //build graph as an adjancency list, build indegree map
        for(int[] dependency : prerequisites){
            int pre = dependency[1];
            int post = dependency[0];
            List<Integer> curList = neis.get(pre);
            curList.add(post);
            inDegree[post]++;
        }
        //find all indegree that has indegree = 0
        for(int i = 0; i < numCourses; i++){
            if(inDegree[i] == 0){
                set.add(i);
            }
        }
        List<Integer> cur = new ArrayList<>();
        dfs(res, set, cur, numCourses, neis, inDegree);
        return res;
    }

    private void dfs(List<List<Integer>> res, Set<Integer> set, List<Integer> cur, int numCourses, Map<Integer, List<Integer>> neis, int[] inDegree){
        if(set.isEmpty()){
            if(cur.size() == numCourses){
                res.add(new ArrayList<>(cur));
            }
            return;
        }

        Set<Integer> curSet = new HashSet<>(set);
        for(Integer i : curSet){
            cur.add(i);
            set.remove(i);
            List<Integer> neiList = neis.get(i);
            for(Integer nei : neiList){
                inDegree[nei]--;
                if(inDegree[nei] == 0){
                    set.add(nei);
                }
            }
            dfs(res, set, cur, numCourses, neis, inDegree);

            for(Integer nei : neiList){
                if(inDegree[nei] == 0){
                    set.remove(nei);
                }
                inDegree[nei]++;
            }
            set.add(i);
            cur.remove(cur.size() - 1);

        }

    }
}
