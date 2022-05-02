import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal_LC103 {

    //!!sol1, dfs, O(n), O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if(root == null) return res;
        dfs(res, root, 0);

        return res;

    }


    private void dfs(List<List<Integer>> res, TreeNode root, int level){
        if(root == null) return;

        if(res.size() == level){
            List<Integer> list = new ArrayList<>();
            res.add(list);
        }

        if(level % 2 == 0){
            res.get(level).add(root.key);
        }else{
            res.get(level).add(0, root.key);
        }

        dfs(res, root.left, level + 1);
        dfs(res, root.right, level + 1);
    }



    //!!sol2, bfs, O(n), O(n)
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offerFirst(root);
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode tmp = queue.pollLast();
                if(level % 2 == 0){
                    list.add(tmp.key);
                }else{
                    list.add(0, tmp.key);
                }

                if(tmp.left != null) queue.offerFirst(tmp.left);
                if(tmp.right != null) queue.offerFirst(tmp.right);

            }
            res.add(list);
            level++;

        }

        return res;

    }

}
