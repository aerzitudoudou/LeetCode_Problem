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
