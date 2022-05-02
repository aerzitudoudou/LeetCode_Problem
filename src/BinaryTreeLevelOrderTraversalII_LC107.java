import java.util.*;

public class BinaryTreeLevelOrderTraversalII_LC107 {
    //sol1, my, bfs, O(n), O(n)
     public List<List<Integer>> levelOrderBottom(TreeNode root) {

         List<List<Integer>> res = new ArrayList<>();
         if(root == null) return res;

         Deque<TreeNode> queue = new LinkedList<>();
         queue.offerFirst(root);

         while(!queue.isEmpty()){
             int size = queue.size();
             List<Integer> list = new ArrayList<>();

             for(int i = 0; i < size; i++){
                 TreeNode tmp = queue.pollLast();
                 list.add(tmp.key);

                 if(tmp.left != null) queue.offerFirst(tmp.left);
                 if(tmp.right != null) queue.offerFirst(tmp.right);
             }

             res.add(0, list);

         }

         return res;
     }


    //sol2, dfs, O(n), O(n + logn) = O(n)
    public List<List<Integer>> levelOrderBottom1(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;

        dfs(res, root, 0);

        Collections.reverse(res);

        return res;
    }


    private void dfs(List<List<Integer>> res, TreeNode root, int level){
        if(root == null) return;

        if(level == res.size()){
            List<Integer> list = new ArrayList<>();
            res.add(list);
        }

        res.get(level).add(root.key);

        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }
}
