import java.util.*;

/*

Leetcode
*863. All Nodes Distance K in Binary Tree
Medium

We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation:
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
*
* */
public class AllNodesDistanceKInBinaryTree {



    //2021/5/17
    //sol 1 T: O(N) S: O(N) bfs to find the parent, then bfs to do level order
    public List<Integer> distanceK1(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();

        Map<TreeNode, TreeNode> map = new HashMap<>();
        //bfs to build the graph
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        while(!queue.isEmpty()){
            TreeNode tmp = queue.pollLast();
            if(tmp.left != null){
                queue.offerFirst(tmp.left);
                map.put(tmp.left, tmp);
            }

            if(tmp.right != null){
                queue.offerFirst(tmp.right);
                map.put(tmp.right, tmp);
            }
        }


        //bfs: level order
        queue.offerFirst(target);
        Set<TreeNode> visited = new HashSet<>();
        int counter = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            if(counter == k) break; //k == 0
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.pollLast();
                visited.add(tmp);
                TreeNode parent = map.get(tmp);
                // if(list == null) return res; //corner case: e.g. root = 1, target = 1, k = 3  no map is there since no neighbors

                List<TreeNode> neis = Arrays.asList(new TreeNode[]{tmp.left, tmp.right, parent});
                for(TreeNode nei : neis){
                    if(nei != null && !visited.contains(nei)){
                        queue.offerFirst(nei);
                    }
                }
            }

            counter++;
        }

        while(!queue.isEmpty()){
            res.add(queue.pollLast().key);
        }
        return res;




    }





    //sol 2 T: O(N) S: O(N) dfs to find the parent, then bfs to do level order
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();

        Map<TreeNode, TreeNode> map = new HashMap<>();


        //dfs to build the parent map
        dfs(root, null, map);


        //bfs: level order
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(target);
        Set<TreeNode> visited = new HashSet<>();
        int counter = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            if(counter == k) break; //k == 0
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.pollLast();
                visited.add(tmp);
                TreeNode parent = map.get(tmp);
                // if(list == null) return res; //corner case: e.g. root = 1, target = 1, k = 3  no map is there since no neighbors

                List<TreeNode> neis = Arrays.asList(new TreeNode[]{tmp.left, tmp.right, parent});
                for(TreeNode nei : neis){
                    if(nei != null && !visited.contains(nei)){
                        queue.offerFirst(nei);
                    }
                }
            }

            counter++;
        }

        while(!queue.isEmpty()){
            res.add(queue.pollLast().key);
        }
        return res;




    }

    private void dfs(TreeNode cur, TreeNode parent, Map<TreeNode, TreeNode> map){
        if(cur == null) return;

        dfs(cur.left, cur, map);
        dfs(cur.right, cur, map);
        map.put(cur, parent);



    }




//     //mine. build the entire graph which is not needed.T: O(N) S: O(V + E) neighbor map = O(v + v - 1) = 0(v) = O(n) v is number of vertex, E is number of edges
//     public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
//         List<Integer> res = new ArrayList<>();

//         Map<TreeNode, List<TreeNode>> map = new HashMap<>();
//         //bfs to build the graph
//         Deque<TreeNode> queue = new LinkedList<>();
//         queue.offerFirst(root);
//         while(!queue.isEmpty()){
//             TreeNode tmp = queue.pollLast();
//             if(tmp.left != null){
//                 queue.offerFirst(tmp.left);
//                 //tmp = 3  tmp.left = 5
//                 List<TreeNode> list = map.getOrDefault(tmp, new ArrayList<TreeNode>());
//                 list.add(tmp.left);
//                 map.put(tmp, list);

//                 list = map.getOrDefault(tmp.left, new ArrayList<TreeNode>());
//                 list.add(tmp);
//                 map.put(tmp.left, list);
//             }

//              if(tmp.right != null){
//                 queue.offerFirst(tmp.right);
//                 List<TreeNode> list = map.getOrDefault(tmp, new ArrayList<TreeNode>());
//                 list.add(tmp.right);
//                 map.put(tmp, list);

//                 list = map.getOrDefault(tmp.right, new ArrayList<TreeNode>());
//                 list.add(tmp);
//                 map.put(tmp.right, list);
//             }
//         }

//         queue.offerFirst(target);
//         Set<TreeNode> set = new HashSet<>();
//         int counter = 0;
//         while(!queue.isEmpty()){
//             int size = queue.size();
//             if(counter == k) break; //k == 0
//             for(int i = 0; i < size; i++){
//                 TreeNode tmp = queue.pollLast();
//                 set.add(tmp);
//                 List<TreeNode> list = map.get(tmp);
//                 if(list == null) return res; //corner case: e.g. root = 1, target = 1, k = 3  no map is there since no neighbors
//                 for(TreeNode nei : list){
//                     if(!set.contains(nei)){
//                         queue.offerFirst(nei);
//                     }
//                 }
//             }

//             counter++;



//         }

//         while(!queue.isEmpty()){
//             res.add(queue.pollLast().val);
//         }
//         return res;




//     }


//2020.8
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        //Build parent
        Map<TreeNode, TreeNode> map = new HashMap<>();
        buildParent(root, null, map);

        //bfs T: O(n) S: O(n)
        Deque<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        queue.offerFirst(target);
        set.add(target);
        //注意！ 第一次做错！set去重还要查null 如果不查null,树的null孩子也会被push进队列
        set.add(null);
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            if(level == K){
                for(int i = 0; i < size; i++){
                    res.add(queue.pollLast().key);
                }
                return res;
            }

            for(int i = 0; i < size; i++){
                TreeNode cur = queue.pollLast();
                //find 3 neighbors, i.e. left right and parent
                if(!set.contains(cur.left)){ //这里有null 孩子被放进queue 的可能性，所以要在查重的同时去除null子节点
                    queue.offerFirst(cur.left);
                    set.add(cur.left);
                }
                if(!set.contains(cur.right)){
                    queue.offerFirst(cur.right);
                    set.add(cur.right);
                }
                if(!set.contains(map.get(cur))){
                    queue.offerFirst(map.get(cur));
                    set.add(map.get(cur));
                }
            }
            level++;

        }
        return res;


    }

    //T: O(n) S:O(h) h 是树的高度
    private void buildParent(TreeNode root, TreeNode parent, Map<TreeNode, TreeNode> map){
        if(root == null){
            return;
        }
        map.put(root, parent);
        buildParent(root.left, root, map);
        buildParent(root.right, root, map);
    }

}
